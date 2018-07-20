package mobile;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;

/**
 * Created by yubo.xuan on 17/5/12.
 */
public class ChinaMobileV3 {

	public static void chinaMobileV3(String city, String basicInfo, String accountPasswd) throws IOException {
		// String queryParam =
		// "?partner_code=yijiejr&partner_key=9857fbf742ac47ada03edee143959433&app_name=yijiejr_web";
		String queryParam = "?partner_code=yijiejr&partner_key=504ab0a4300642b8a96c91951a19e513";
		Map<String, String> headers;

		headers = null;

		// 定义接口返回码
		Integer code;
		// 返回数据
		String data = null;
		// 最终结果
		String lastQueryResult = null;
		// 请求body
		String body;
		// 初始化任务阶段
		String stage = "INIT";
		// 图片验证码
		String authCode;
		// 任务id
		String taskId;
		// 验证码存放路径
		String path = "/var/tmp";
		String url = "https://api.shujumohe.com/octopus";

		// 创建任务
		String createTaskAddr = url + "/task.unify.create/v3";
		// 登录爬取地址
		String crawlAddr = url + "/yys.unify.acquire/v3";
		// 查询任务结果
		String queryTaskaddr = url + "/task.unify.query/v3";

		System.out.println("##############创建任务##############");

		// 发送创建任务请求
		String queryResult = HttpUtils.executeHttpPost(createTaskAddr, queryParam, headers, basicInfo);
		// 获取任务id
		taskId = JSON.parseObject(queryResult).getString("task_id");

		System.out.println("#######################" + stage + "阶段#######################");
		// 接口body内容
		body = accountPasswd + "&task_id=" + taskId + "&task_stage=" + stage + "&request_type=submit";
		// 发送init阶段请求
		queryResult = HttpUtils.executeHttpPost(crawlAddr, queryParam, headers, body);
		// 获取init阶段返回码
		code = JSON.parseObject(queryResult).getInteger("code");

		String smsCode = null;// 手机验证码
		String mobile = basicInfo.substring(basicInfo.length() - 11, basicInfo.length());// 所在城市
		// 根据返回的code判断下一步操作，code参考api运营商返回码。
		while (true) {
			if (code == 0
					|| !(code == 100 || code == 105 || code == 101 || code == 123 || code == 137 || code == 2007)) {
				break;
			}

			sleep(5000);

			switch (code) {
			// code为100时重复调用当前阶段的query查询
			case 100:

				// 发送请求
				queryResult = HttpUtils.executeHttpPost(crawlAddr, queryParam, headers,
						accountPasswd + "&task_id=" + taskId + "&task_stage=INIT" + "&request_type=query");
				// 获取返回code
				code = JSON.parseObject(queryResult).getInteger("code");
				break;
			case 137:
			case 2007:
				// 发送请求
				lastQueryResult = HttpUtils.executeHttpPost(queryTaskaddr, queryParam, headers, "task_id=" + taskId);
				// 获取返回code
				code = JSON.parseObject(lastQueryResult).getInteger("code");
				data = JSON.parseObject(JSON.parseObject(lastQueryResult).getString("data")).getString("task_data");
				break;
			case 101:// 图片验证码

				// 获取下个阶段
				stage = JSON.parseObject(queryResult).getJSONObject("data").getString("next_stage");
				authCode = JSON.parseObject(queryResult).getJSONObject("data").getString("auth_code");
				System.out.println("#######################" + stage + "阶段#######################");

				// 获取图片验证码
				System.out.println("#######################请输入图片验证码#######################");
				body = "task_id=" + taskId + "&task_stage=" + stage + "&auth_code="
						+ OCRHelper.showPicture(path + taskId, authCode) + "&request_type=submit";

				// 发送请求
				queryResult = HttpUtils.executeHttpPost(crawlAddr, queryParam, headers, body);

				// 获取返回code
				code = JSON.parseObject(queryResult).getInteger("code");
				break;

			case 123:// 短信验证码和图片验证码

				stage = JSON.parseObject(queryResult).getJSONObject("data").getString("next_stage");
				System.out.println("#######################" + stage + "阶段#######################");
				authCode = JSON.parseObject(queryResult).getJSONObject("data").getString("auth_code");
				for (int i = 1; i < 4; i++) {
					System.out.println(city + "\t" + mobile + "\t" + "开始第" + i + "次尝试获取短信验证码......");
					Scanner scanner = new Scanner(System.in);
					System.out.println("请输入手机验证码，按回车继续");
					scanner = new Scanner(System.in);
					smsCode = scanner.nextLine();
					if (smsCode != null && smsCode.length() != 0 && !"".equals(smsCode)) {
						System.out.println(city + "\t" + mobile + "\t" + "第" + i
								+ "次尝试获取短信验证码成功，您的短信验证码是##############：" + smsCode);
						String authCodeString = OCRHelper.showPicture(path + taskId, authCode);
						body = "task_id=" + taskId + "&task_stage=" + stage + "&sms_code=" + smsCode
								+ "&request_type=submit" + "&auth_code=" + authCodeString;
						// 发送请求
						queryResult = HttpUtils.executeHttpPost(crawlAddr, queryParam, headers, body);
						smsCode = "";
						break;
					}
					// sleep(20000);
				}
				code = JSON.parseObject(queryResult).getInteger("code");
				break;
			case 105:// 短信验证码
				// 获取下个阶段
				stage = JSON.parseObject(queryResult).getJSONObject("data").getString("next_stage");
				System.out.println("#######################" + stage + "阶段#######################");
				for (int i = 1; i < 4; i++) {
					System.out.println(city + "\t" + mobile + "\t" + "开始第" + i + "次尝试获取短信验证码......");
					Scanner scanner = new Scanner(System.in);
					System.out.println("请输入手机验证码，按回车继续");
					scanner = new Scanner(System.in);
					smsCode = scanner.nextLine();
					if (smsCode != null && smsCode.length() != 0 && !"".equals(smsCode)) {
						body = "task_id=" + taskId + "&task_stage=" + stage + "&sms_code=" + smsCode
								+ "&request_type=submit";
						System.out.println(city + "\t" + mobile + "\t" + "第" + i
								+ "次尝试获取短信验证码成功，您的短信验证码是##############：" + smsCode);
						// 发送请求
						queryResult = HttpUtils.executeHttpPost(crawlAddr, queryParam, headers, body);
						smsCode = "";
						break;
					}
					sleep(20000);
				}
				// 获取返回code
				code = JSON.parseObject(queryResult).getInteger("code");
				break;
			default:
				break;
			}

		}
		System.out.println(
				"任务id=" + taskId + "状态码为" + code + "；message:" + JSON.parseObject(queryResult).getString("message"));
		if (code == 0) {
			System.out.println("获取的数据如下：");
			System.out.println(
					JSON.parseObject(JSON.parseObject(lastQueryResult).getString("data")).getString("task_data"));
		}
	}

	// 设置线程睡眠时间
	private static void sleep(int l) {
		try {
			Thread.sleep(l);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		String city = "四川移动";
		String basicInfo = "channel_code=100000&channel_type=YYS&real_name=刘春惠&identity_code=511028198702134825&user_mobile=13658032580";
		// String accountPwd =
		// "user_name=19588097@qq.com&user_pass=tongdun@123";
		// String accountPwd = "user_name=1756711@qq.com&user_pass=jczE99";
		String accountPwd = "";
		try {
			chinaMobileV3(city, basicInfo, accountPwd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
