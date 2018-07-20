package httpIvk;

import java.util.List;

import org.apache.http.NameValuePair;

import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.helper.HttpRequestGet;
import duomi.com.httpIvk.helper.HttpRequestUtil;
import duomi.com.httpIvk.helper.HttpUtil;
import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.antifraud.AntiFraudInput;
import duomi.com.httpIvk.param.bankCard.BankCardTradeResult;
import duomi.com.httpIvk.param.overdue.LoanOverdueResult;
import duomi.com.httpIvk.services.ZbaDataHttpServiceImpl;
import duomi.com.utils.FileUtil;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.JSONUtils;
import duomi.dispatch.request.BackCard3ERequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

public class JavaTest {
	public static void main(String[] args) {
		new JavaTest().testjson();

	}

	private void testjson() {
		String a = "{'success':true,'data':{'mobile':'13281110613','list':'','status':'NO_DATA','statusDesc':'\u67e5\u8be2\u65e0\u7ed3\u679c'}}";
		GsonUtils<LoanOverdueResult> gson = new GsonUtils<LoanOverdueResult>();
		BaseResponse<LoanOverdueResult> output = (BaseResponse<LoanOverdueResult>) gson.fromJson(a, BaseResponse.class,
				LoanOverdueResult.class);

		System.out.println(output);
	}

	public void getdata4local() {
		// String
		// param="name=张杰&idCard=110102199901019150&accountNo=12123123132";
		String param = "name=张杰&idCard=110102199901019150&accountNo=12123123132";

		String url = "http://localhost:8080/riskManage/bankCard3E2";
		String a = HttpRequestGet.sendGet(url, param);
		System.out.println(a);
	}

	public void postData4local() {
		BackCard3ERequest po = new BackCard3ERequest();
		po.setAppNo("123123");
		// po.setCustName("nihao");
		po.setIdCard("1232312");
		// po.setCertNo("sdf1123123");
		// po.setMobileNo("13567676666");
		po.setName("中文");
		po.setAccountNo("12312313");

		List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

		String url = "http://localhost:8080/riskManage/bankCard3E";
		String retstr = HttpUtil.post(url, list);
		/*
		 * System.out.println(retstr); String str=retstr.substring(1,
		 * retstr.length()-1);
		 */
		System.out.println("testjava:----" + retstr);
		JSONObject jsonObject = JSONObject.fromObject(retstr);
		ComResponse output = JSONUtils.toBean(jsonObject, ComResponse.class);

		System.out.println(output);
	}

	public void postdata5() {
		// HttpIvkHelper<AntiFraudInput,AntiFraudOutput> helper=new
		// HttpIvkHelper<AntiFraudInput,AntiFraudOutput>();
		ZbaDataHttpServiceImpl svc = new ZbaDataHttpServiceImpl();
		// svc.CheckBankCard3Element("贺元杰", "511028198705260034",
		// "6221532320013112551");
	}

	public void postdata3() {
		// HttpIvkHelper<AntiFraudInput,AntiFraudOutput> helper=new
		// HttpIvkHelper<AntiFraudInput,AntiFraudOutput>();
		// HttpIvkHelper<AntiFraudOutput> helper=new
		// HttpIvkHelper<AntiFraudOutput>();

		AntiFraudInput req = new AntiFraudInput();
		// req.setApp_id("283615341dd58a0a");
		req.setName("张杰");
		req.setIdCard("110102199901019150");

		String url = PubConstants.ZBADATA_URL + ZbaDataHttpServiceImpl.url_AntiFraud;
		// AntiFraudOutput out=(AntiFraudOutput) helper.postData(url,req,
		// AntiFraudOutput.class);
		// System.out.println(out.getData().getFinalDecision());
	}

	public void getdata() {
		String filename = "E:\\workspace\\HttpInvoke\\src\\resources\\error.json";
		try {
			String jsonstr = FileUtil.readFile(filename, null);
			System.out.println(jsonstr);
			JSONObject jsonObject = JSONObject.fromObject(jsonstr);
			// AntiFraudOutput output=JSONUtils.toBean(jsonObject,
			// AntiFraudOutput.class);
			// System.out.println(output);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getdata2() {
		String filename = "E:\\svn\\101.132.222.202\\code\\riskManage\\src\\main\\resources\\msg\\BankCardResult.json";
		try {
			String jsonstr = FileUtil.readFile(filename, null);
			System.out.println(jsonstr);
			JSONObject jsonObject = JSONObject.fromObject(jsonstr);
			BankCardTradeResult output = JSONUtils.toBean(jsonObject, BankCardTradeResult.class);
			System.out.println(output);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void postdata() {

		AntiFraudInput req = new AntiFraudInput();
		// req.setApp_id("283615341dd58a0a");
		req.setName("�Ž�");
		req.setIdCard("110102199901019150");

		try {
			req = (AntiFraudInput) req.init(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * List<NameValuePair> list=new ArrayList<NameValuePair>();
		 * NameValuePair nv1=new
		 * BasicNameValuePair("idCard","13030319990101393x"); NameValuePair
		 * nv2=new BasicNameValuePair("name","�Ž�"); NameValuePair nv3=new
		 * BasicNameValuePair("app_id","283615341dd58a0a"); NameValuePair
		 * nv4=new
		 * BasicNameValuePair("sign","79d9034fc0c6fe7f6856e3175527ba0085dd949b")
		 * ; list.add(nv1); list.add(nv2); list.add(nv3); list.add(nv4);
		 */
		List<NameValuePair> list = JSONUtils.toNameValuePairList(req);

		String url = "http://interface.zbadata.com/risk/decision/";
		String retstr = HttpUtil.post(url, list);
		System.out.println(retstr);

		JSONObject jsonObject = JSONObject.fromObject(retstr);
		// AntiFraudOutput output=JSONUtils.toBean(jsonObject,
		// AntiFraudOutput.class);

		// System.out.println(output);

		/*
		 * String param=JSONUtils.toJSONString(req); System.out.println(param);
		 * String param2=
		 * "{\"sign\":\"CBE9DDB2B6A37EDD800F075F3A6DC3F02F0A8F15\",\"idCard\":\"13030319990101393x\",\"name\":\"�Ž�\",\"app_id\":\"283615341dd58a0a\"}	"
		 * ;
		 * 
		 * String url="http://interface.zbadata.com/risk/decision/"; //String
		 * retstr=HttpRequestUtil.sendPost(url, param2, false);
		 * //System.out.println(retstr); HttpRequestUtil.doHttpPost(param, url);
		 */
	}

	public void postdata2() {
		AntiFraudInput req = new AntiFraudInput();
		// req.setApp_id("283615341dd58a0a");
		req.setName("�Ž�");
		req.setIdCard("13030319990101393x");

		try {
			req = (AntiFraudInput) req.init(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String param = JSONUtils.toJSONString(req);
		System.out.println(param);
		// String
		// param2="{\"sign\":\"CBE9DDB2B6A37EDD800F075F3A6DC3F02F0A8F15\",\"idCard\":\"13030319990101393x\",\"name\":\"�Ž�\",\"app_id\":\"283615341dd58a0a\"}
		// ";

		String url = "http://interface.zbadata.com/risk/decision/";
		// String retstr=HttpRequestUtil.sendPost(url, param2, false);
		// System.out.println(retstr);
		HttpRequestUtil.doHttpPost(param, url);
	}
}
