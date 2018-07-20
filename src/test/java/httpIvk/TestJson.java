package httpIvk;

import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.bankCard.BankCardTradeResult;
import duomi.com.httpIvk.param.mobiledetail.MBDetailResponse;
import duomi.com.httpIvk.param.overdue.LoanOverdueResult;
import duomi.com.utils.FileUtil;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.JSONUtils;
import net.sf.json.JSONObject;

public class TestJson {
	public static void main(String[] args) {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("aaa");
		// new TestJson().testapp();
	}

	private void testapp() {
		String filename = "E:\\eclipspaces\\kfspace\\riskManage\\src\\test\\java\\msg\\app.json";

		String jsonstr = null;
		try {
			jsonstr = FileUtil.readFile(filename, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonstr);

		GsonUtils<BankCardTradeResult> gson = new GsonUtils<BankCardTradeResult>();
		BaseResponse<BankCardTradeResult> output = (BaseResponse<BankCardTradeResult>) gson.fromJson(jsonstr,
				BaseResponse.class, BankCardTradeResult.class);

		String out_str = JSONUtils.toJSONString(output);
		System.out.println(out_str);
	}

	private void test() {
		String filename = "E:\\svn\\101.132.222.202\\code\\riskManage\\src\\test\\java\\msg\\mobiledetail.json";

		String jsonstr = null;
		try {
			jsonstr = FileUtil.readFile(filename, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonstr);
		JSONObject jsonObject = JSONObject.fromObject(jsonstr);
		MBDetailResponse appReq = JSONUtils.toBean(jsonObject, MBDetailResponse.class);
		System.out.println(appReq);
	}

	private void testjson() {
		String retstr = "{'success':true,'data':{'mobile':'13281110613','list':null,'status':'NO_DATA','statusDesc':'\u67e5\u8be2\u65e0\u7ed3\u679c'}}";
		GsonUtils<LoanOverdueResult> gson = new GsonUtils<LoanOverdueResult>();
		BaseResponse<LoanOverdueResult> output = (BaseResponse<LoanOverdueResult>) gson.fromJson(retstr,
				BaseResponse.class, LoanOverdueResult.class);
		System.out.println(output);

		BaseResponse<LoanOverdueResult> output2 = new BaseResponse<LoanOverdueResult>();
		LoanOverdueResult data = new LoanOverdueResult();
		output2.setData(data);
		String str = JSONUtils.toJSONString(output2);
		System.out.println(str);

	}

}
