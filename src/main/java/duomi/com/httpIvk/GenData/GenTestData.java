package duomi.com.httpIvk.GenData;

import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.house.param.HouseBaseResponse;
import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.litigation.PersonLitigationResult;
import duomi.com.utils.FileUtil;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.JSONUtils;
import net.sf.json.JSONObject;

public class GenTestData<T> {

	public BaseResponse<T> genData4test(Class<T> class1) throws HttpBizException {

		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String filename = path;
		if (class1.getName().contains("MultipleLendResult")) {
			filename = filename + "msg//MultipleLend.json";
		} else if (class1.getName().contains("BackCardThreeELementResult")) {
			filename = filename + "msg//BankCard3E.json";
		} else if (class1.getName().contains("BackCardFourELementResult")) {
			filename = filename + "msg//BackCardFourELementOutput.json";
		} else if (class1.getName().contains("PhoneCheckResult")) {
			filename = filename + "msg//PhoneCheckOutput.json";
		} else if (class1.getName().contains("EducationCheckResult")) {
			filename = filename + "msg//EducationCheckResult.json";
		} else if (class1.getName().contains("BadrecordResult")) {
			filename = filename + "msg//BadrecordResult.json";
		} else if (class1.getName().contains("CreditScoreResult")) {
			filename = filename + "msg//CreditScoreResult.json";
		} else if (class1.getName().contains("PhoneOnlineDurationResult")) {
			filename = filename + "msg//PhoneOnlineDurationResult.json";
		} else if (class1.getName().contains("MobileStatusResult")) {
			filename = filename + "msg//MobileStatusResult.json";
		} else if (class1.getName().contains("AntiFraudResult")) {
			filename = filename + "msg//AntiFraud.json";
		} else if (class1.getName().contains("BlackListResult")) { // 个人黑名单
			filename = filename + "msg//BlackListResult.json";
		} else if (class1.getName().contains("MobileHaltResult")) { // 手机号码最近三月停机次数查询
			filename = filename + "msg//MobileHaltResult.json";
		} else if (class1.getName().contains("MobileMostCityResult")) { // 手机号指定月通话天数最多的城市
			filename = filename + "msg//MobileMostCityResult.json";
		} else if (class1.getName().contains("MobileAverageFeeResult")) { // 月平均消费水平
			filename = filename + "msg//MobileAverageFeeResult.json";
		} else if (class1.getName().contains("MobileAccessNumResult")) { // 手机号码自然人接入号码个数查询
			filename = filename + "msg//MobileAccessNumResult.json";
		} else if (class1.getName().contains("PersonEnterpriseResult")) { // 个人名下企业查询
			filename = filename + "msg//PersonEnterpriseResult.json";
		} else if (class1.getName().contains("PersonLitigationResult")) { // 个人涉诉全类查询
			filename = filename + "msg//PersonLitigationResult.json";
		} else if (class1.getName().contains("CensusRegisterResult")) { // 户籍查询
			filename = filename + "msg//CensusRegisterResult.json";
		} else if (class1.getName().contains("BankCardTradeResult")) { // 银行卡
			filename = filename + "msg//BankCardTradeResult.json";
		} else if (class1.getName().contains("MultipleCreditAppResult")) { // 个人借贷多次申请
			filename = filename + "msg//MultipleCreditAppResult.json";
		} else if (class1.getName().contains("ConsCreditOfflineResult")) { // 线下消费贷
			filename = filename + "msg//ConsCreditOfflineResult.json";
		} else if (class1.getName().contains("AirTravelScoreResult")) { // 航旅旅客价值等级查询
			filename = filename + "msg//AirTravelScoreResult.json";
		} else if (class1.getName().contains("FlightInfoResult")) { // 航旅信息查询
			filename = filename + "msg//FlightInfoResult.json";
		} else if (class1.getName().contains("MobileNumbersResult")) { // 手机号接入个数
			filename = filename + "msg//MobileNumbersResult.json";
		} else if (class1.getName().contains("LoanOverdueResult")) { // 个人贷款逾期
			filename = filename + "msg//LoanOverdueResult.json";
		}

		BaseResponse<T> output = this.genDataByGson(filename, class1);

		return output;
	}

	public HouseBaseResponse<T> genHouseData4test(Class<T> class1) throws HttpBizException {
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String filename = path;
		if (class1.getName().contains("HousePriceResult")) {
			filename = filename + "msg//HousePriceResult.json";
		}

		HouseBaseResponse<T> output = null;
		try {
			String jsonstr = FileUtil.readFile(filename, null);
			System.out.println(jsonstr);
			JSONObject jsonObject = JSONObject.fromObject(jsonstr);
			output = (HouseBaseResponse<T>) new GsonUtils<T>().fromJson(jsonstr, HouseBaseResponse.class, class1);
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpBizException("测试数据解析错误");
		}
		return output;
	}

	public BaseResponse<T> genDataByGson(String filename, Class<T> class1) throws HttpBizException {
		BaseResponse<T> output = null;
		try {
			String jsonstr = FileUtil.readFile(filename, null);
			System.out.println(jsonstr);
			JSONObject jsonObject = JSONObject.fromObject(jsonstr);
			output = (BaseResponse<T>) new GsonUtils<T>().fromJson(jsonstr, BaseResponse.class, class1);
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpBizException("测试数据解析错误");
		}
		return output;
	}

	public T genDataByJsonLib(String filename, Class<T> outputClass) {
		T output = null;
		try {
			String jsonstr = FileUtil.readFile(filename, null);
			System.out.println(jsonstr);
			JSONObject jsonObject = JSONObject.fromObject(jsonstr);

			output = JSONUtils.toBean(jsonObject, outputClass);
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	public static void main(String[] args) {
		/*
		 * GsonUtils<PersonLitigationResult> gson = new
		 * GsonUtils<PersonLitigationResult>(); gson.genData4test
		 * BaseResponse<PersonLitigationResult> output = gson.fromJson(retstr,
		 * BaseResponse.class, PersonLitigationResult.class);
		 */

		try {
			GenTestData<PersonLitigationResult> gtd = new GenTestData<PersonLitigationResult>();
			BaseResponse<PersonLitigationResult> output = gtd.genData4test(PersonLitigationResult.class);
			System.out.println(output.getData().getCpws());
		} catch (HttpBizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
