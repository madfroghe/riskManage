package httpIvk;

import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.antifraud.AntiFraudOutput;
import duomi.com.httpIvk.param.bankCard.BankCardTradeResult;
import duomi.com.utils.FileUtil;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.JSONUtils;
import net.sf.json.JSONObject;

public class Test {
	public static void main(String[] args) {
		String filename = "E:\\svn\\101.132.222.202\\code\\riskManage\\src\\main\\resources\\msg\\BankCardResult.json";
		new Test().genDataByGson(filename);
	}

	public void genDataByGson(String filename) {
		try {
			String jsonstr = FileUtil.readFile(filename, null);
			System.out.println(jsonstr);
			JSONObject jsonObject = JSONObject.fromObject(jsonstr);
			BaseResponse<BankCardTradeResult> output = (BaseResponse<BankCardTradeResult>) new GsonUtils<BankCardTradeResult>()
					.fromJson(jsonstr, BaseResponse.class, BankCardTradeResult.class);

			// output=JSONUtils.toBean(jsonObject, new
			// BaseResponse<T>().getClass());
			System.out.println(output);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void genDataByJsonLib(String filename) {
		try {
			String jsonstr = FileUtil.readFile(filename, null);
			System.out.println(jsonstr);
			JSONObject jsonObject = JSONObject.fromObject(jsonstr);
			// output=(BaseResponse<T>) new GsonUtils<T>().fromJson(jsonstr,
			// BaseResponse.class, class1);

			AntiFraudOutput output = JSONUtils.toBean(jsonObject, AntiFraudOutput.class);
			System.out.println(output);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
