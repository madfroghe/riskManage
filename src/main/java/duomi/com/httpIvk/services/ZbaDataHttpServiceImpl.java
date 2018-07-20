package duomi.com.httpIvk.services;

import duomi.com.httpIvk.param.BlacklistcheckInput;
import duomi.com.httpIvk.param.PersonJudicialList.PersonJudicialListInput;
import duomi.com.httpIvk.param.personalCredit.PersonalCreditInput;
import duomi.dispatch.request.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.antifraud.AntiFraudInput;
import duomi.com.httpIvk.param.antifraud.AntiFraudResult;
import duomi.com.httpIvk.param.badrecords.BadrecordInput;
import duomi.com.httpIvk.param.badrecords.BadrecordResult;
import duomi.com.httpIvk.param.bankCard.BackCardFourELementInput;
import duomi.com.httpIvk.param.bankCard.BackCardFourELementResult;
import duomi.com.httpIvk.param.bankCard.BackCardThreeELementInput;
import duomi.com.httpIvk.param.bankCard.BackCardThreeELementResult;
import duomi.com.httpIvk.param.bankCard.BankCardTradeInput;
import duomi.com.httpIvk.param.bankCard.BankCardTradeResult;
import duomi.com.httpIvk.param.blacklist.BlackListInput;
import duomi.com.httpIvk.param.blacklist.BlackListResult;
import duomi.com.httpIvk.param.consume.ConsCreditOfflineInput;
import duomi.com.httpIvk.param.consume.ConsCreditOfflineResult;
import duomi.com.httpIvk.param.creditscore.CreditScoreInput;
import duomi.com.httpIvk.param.creditscore.CreditScoreResult;
import duomi.com.httpIvk.param.education.EducationCheckInput;
import duomi.com.httpIvk.param.education.EducationCheckResult;
import duomi.com.httpIvk.param.enterprise.PersonEnterpriseInput;
import duomi.com.httpIvk.param.enterprise.PersonEnterpriseResult;
import duomi.com.httpIvk.param.idcard.IdCardCheckInput;
import duomi.com.httpIvk.param.idcard.IdCardCheckResult;
import duomi.com.httpIvk.param.litigation.PersonLitigationInput;
import duomi.com.httpIvk.param.litigation.PersonLitigationResult;
import duomi.com.httpIvk.param.multipleLend.MultipleCreditAppInput;
import duomi.com.httpIvk.param.multipleLend.MultipleCreditAppResult;
import duomi.com.httpIvk.param.multipleLend.MultipleLendInput;
import duomi.com.httpIvk.param.multipleLend.MultipleLendOutput;
import duomi.com.httpIvk.param.multipleLend.MultipleLendResult;
import duomi.com.httpIvk.param.overdue.LoanOverdueInput;
import duomi.com.httpIvk.param.overdue.LoanOverdueResult;
import duomi.com.httpIvk.param.phone.MobileAccessNumInput;
import duomi.com.httpIvk.param.phone.MobileAccessNumResult;
import duomi.com.httpIvk.param.phone.MobileAverageFeeInput;
import duomi.com.httpIvk.param.phone.MobileAverageFeeResult;
import duomi.com.httpIvk.param.phone.MobileHaltInput;
import duomi.com.httpIvk.param.phone.MobileHaltResult;
import duomi.com.httpIvk.param.phone.MobileMostCityInput;
import duomi.com.httpIvk.param.phone.MobileMostCityResult;
import duomi.com.httpIvk.param.phone.MobileNumbersInput;
import duomi.com.httpIvk.param.phone.MobileNumbersResult;
import duomi.com.httpIvk.param.phone.MobileStatusResult;
import duomi.com.httpIvk.param.phone.PhoneCheckInput;
import duomi.com.httpIvk.param.phone.PhoneCheckResult;
import duomi.com.httpIvk.param.phone.PhoneOnlineDurationResult;
import duomi.com.httpIvk.param.register.CensusRegisterInput;
import duomi.com.httpIvk.param.register.CensusRegisterResult;
import duomi.com.httpIvk.param.special.SpecialListIntput;
import duomi.com.httpIvk.param.special.SpecialListResult;
import duomi.com.httpIvk.param.travel.AirTravelScoreInput;
import duomi.com.httpIvk.param.travel.AirTravelScoreResult;
import duomi.com.httpIvk.param.travel.FlightInfoInput;
import duomi.com.httpIvk.param.travel.FlightInfoResult;
import duomi.com.utils.FileUtil;
import duomi.com.utils.JSONUtils;
import duomi.services.OutsideServiceRegistService;
import net.sf.json.JSONObject;

@Component
public class ZbaDataHttpServiceImpl implements ZbaDataHttpService {

	private final static Logger logger = LoggerFactory.getLogger(ZbaDataHttpServiceImpl.class);

	// 反欺诈地址
	public final static String url_AntiFraud = "/risk/decision/";
	// 银行卡三要素验证
	public final static String url_BankCard3Element = "/base/bankCard/";
	// 银行卡三要素验证
	public final static String url_BankCard4Element = "/base/fourElement/";
	// 手机号身份证号验证
	public final static String url_Phone = "/mobile/threeElement/";
	// 个人多重借贷查询
	public final static String url_MultipleLend = "/trade/multipleLending/";
	// 个人多重借贷查询
	public final static String url_MultipleCreditApp = "/risk/multipleCreditApply/";

	// 手机号身份证号验证
	public final static String url_IdCard = "/base/identity/";
	// 学历查询
	public final static String url_Education = "/base/degree/";

	// 不良记录
	public final static String url_Badrecord = "/risk/negative/";

	// 个人信用评分
	public final static String url_CreditScore = "/risk/creditScore/";

	// 手机号在网时长查询(三大运营商)
	public final static String url_PhoneOnlineDuration = "/mobile/time/";

	// 手机号在网时长查询(三大运营商)
	public final static String url_MoblileStatus = "/mobile/status/";

	// 个人黑名单
	public final static String url_BlackList = "/risk/personBlackList";

	//个人信用
	public final static String url_PersonalCredit = "/risk/personalCredit/";

	//个人司法信息
	public final static String url_PersonJudicialList = "/risk/personJudicialList";

	//反欺诈服务黑名单
	public final static String url_Blacklistcheck = "/risk/blacklistcheck/";

	// 手机号码最近三月停机次数查询
	public final static String url_MoblieHalt = "/mobile/disconnectTimes/";

	// 手机号指定月通话天数最多的城市
	public final static String url_MobileCity = "/mobile/callMostCity/";

	// 月平均消费水平
	public final static String url_MobileAverageFee = "/mobile/consume/";

	// 手机号码自然人接入号码个数查询
	public final static String url_MobileAccessNum = "/mobile/accessMobileNumbers/";

	// 个人名下企业查询
	public final static String url_PersonEnterprise = "/asset/enterprise/";
	// 个人涉诉全类查询
	public final static String url_PersonLitigation = "/risk/personalLaw/";

	// 户籍查询
	public final static String url_CensusRegister = "/base/register/";

	// 银行卡
	public final static String url_BankCardTrade = "/base/bankCardInfo/";

	// 线下消费贷客群评分
	public final static String url_ConsCreditOffline = "/risk/scoreConsOff/";

	// 航旅旅客价值等级查询
	public final static String url_AirTravelScore = "/risk/airTravelScore/";

	// 特殊名单查询
	public final static String url_SpecialList = "/risk/specialList/";

	// 航旅信息查询
	public final static String url_FlightInfo = "/trade/flight/";

	// 手机号码自然人接入号码个数查询
	public final static String url_MobileNumbers = "/mobile/accessMobileNumbers/";
	// 贷款逾期
	public final static String url_LoanOverdue = "/trade/loanOverdue/";

	@Autowired
	private OutsideServiceRegistService regitSrv;

	/**
	 * 反欺诈
	 *
	 * @param name
	 * @param idCard
	 * @throws Exception
	 */
	public BaseResponse<AntiFraudResult> checkAntiFraud(AntiFraudRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_AntiFraud;
		AntiFraudInput input = new AntiFraudInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<AntiFraudResult> helper = new HttpIvkHelper<AntiFraudResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<AntiFraudResult> out = helper.postData(url, request, input, AntiFraudResult.class);
		return out;
	}

	/**
	 * 银行卡三要素验证
	 *
	 * @param name
	 *            真实姓名
	 * @param idCard
	 *            身份证号
	 * @param accountNo
	 *            银行卡号
	 * @throws Exception
	 */
	public BaseResponse<BackCardThreeELementResult> CheckBankCard3Element(BackCard3ERequest req) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_BankCard3Element;
		BackCardThreeELementInput input = new BackCardThreeELementInput();
		BeanUtils.copyProperties(req, input);
		HttpIvkHelper<BackCardThreeELementResult> helper = new HttpIvkHelper<BackCardThreeELementResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<BackCardThreeELementResult> out = helper.postData(url, req, input,
				BackCardThreeELementResult.class);
		return out;
	}

	/**
	 * 银行卡四要素验证
	 *
	 * @param mobile
	 *            电话号码
	 * @param name
	 *            真实姓名
	 * @param idCard
	 *            身份证号
	 * @param accountNo
	 *            银行卡号
	 */
	public BaseResponse<BackCardFourELementResult> CheckBankCard4Element(BackCard4ERequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_BankCard4Element;
		BackCardFourELementInput input = new BackCardFourELementInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<BackCardFourELementResult> helper = new HttpIvkHelper<BackCardFourELementResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<BackCardFourELementResult> out = helper.postData(url, request, input,
				BackCardFourELementResult.class);
		return out;
	}

	/**
	 * 手机号身份证号验证 手机三要素鉴权
	 *
	 * @param name
	 * @param mobile
	 * @param idCard
	 */
	public BaseResponse<PhoneCheckResult> checkPhone(Mobile3ERequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_Phone;
		PhoneCheckInput input = new PhoneCheckInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<PhoneCheckResult> helper = new HttpIvkHelper<PhoneCheckResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<PhoneCheckResult> out = helper.postData(url, request, input, PhoneCheckResult.class);
		return out;
	}

	/**
	 * 个人多重借贷查询
	 */
	public BaseResponse<MultipleLendResult> checkMultipleLend(MultipleLendRequest request) throws Exception {

		String url = PubConstants.ZBADATA_URL + this.url_MultipleLend;
		MultipleLendInput input = new MultipleLendInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<MultipleLendResult> helper = new HttpIvkHelper<MultipleLendResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<MultipleLendResult> out = helper.postData(url, request, input, MultipleLendResult.class);

		return out;
	}

	/**
	 * 个人贷款多次申请
	 */
	public BaseResponse<MultipleCreditAppResult> checkMultipleCreditApp(MultipleCreditAppRequest request)
			throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_MultipleCreditApp;
		MultipleCreditAppInput input = new MultipleCreditAppInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<MultipleCreditAppResult> helper = new HttpIvkHelper<MultipleCreditAppResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<MultipleCreditAppResult> out = helper.postData(url, request, input, MultipleCreditAppResult.class);

		return out;
	}

	/**
	 * 身份证一致性验证
	 */
	public BaseResponse<IdCardCheckResult> checkIdCard(IdCardRequest request) throws Exception {

		String url = PubConstants.ZBADATA_URL + this.url_IdCard;
		IdCardCheckInput input = new IdCardCheckInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<IdCardCheckResult> helper = new HttpIvkHelper<IdCardCheckResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<IdCardCheckResult> out = helper.postData(url, request, input, IdCardCheckResult.class);
		return out;
	}

	/**
	 * 学历查询
	 */
	public BaseResponse<EducationCheckResult> checkEducation(EducationCheckquest request) throws Exception {

		String url = PubConstants.ZBADATA_URL + this.url_Education;
		EducationCheckInput input = new EducationCheckInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<EducationCheckResult> helper = new HttpIvkHelper<EducationCheckResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<EducationCheckResult> out = helper.postData(url, request, input, EducationCheckResult.class);
		return out;
	}

	/**
	 * 不良信息核查
	 */
	public BaseResponse<BadrecordResult> checkBadrecord(BadrecordRequest request) throws Exception {

		String url = PubConstants.ZBADATA_URL + this.url_Badrecord;
		BadrecordInput input = new BadrecordInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<BadrecordResult> helper = new HttpIvkHelper<BadrecordResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<BadrecordResult> out = helper.postData(url, request, input, BadrecordResult.class);
		return out;
	}

	// 个人信用评分
	public BaseResponse<CreditScoreResult> getCreditScore(CreditScoreRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_CreditScore;
		CreditScoreInput input = new CreditScoreInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<CreditScoreResult> helper = new HttpIvkHelper<CreditScoreResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<CreditScoreResult> out = helper.postData(url, request, input, CreditScoreResult.class);
		return out;
	}

	// 手机号在网时长查询(三大运营商)
	public BaseResponse<PhoneOnlineDurationResult> getPhoneOnlineDuration(PhoneOnlineDurationRequest request)
			throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_PhoneOnlineDuration;
		CreditScoreInput input = new CreditScoreInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<PhoneOnlineDurationResult> helper = new HttpIvkHelper<PhoneOnlineDurationResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<PhoneOnlineDurationResult> out = helper.postData(url, request, input,
				PhoneOnlineDurationResult.class);
		return out;
	}

	// 手机号状态查询(三大运营商)
	public BaseResponse<MobileStatusResult> getMobileStatus(MobileStatusRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_MoblileStatus;
		CreditScoreInput input = new CreditScoreInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<MobileStatusResult> helper = new HttpIvkHelper<MobileStatusResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<MobileStatusResult> out = helper.postData(url, request, input, MobileStatusResult.class);
		return out;
	}

	// 个人黑名单
	public BaseResponse<BlackListResult> getBlackList(BlackListRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_BlackList;
		BlackListInput input = new BlackListInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<BlackListResult> helper = new HttpIvkHelper<BlackListResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<BlackListResult> out = helper.postData(url, request, input, BlackListResult.class);
		return out;
	}

	// 个人信用
	public BaseResponse<JSONObject> getPersonalCredit(PersonalCreditRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_PersonalCredit;
		PersonalCreditInput input = new PersonalCreditInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<JSONObject> out = helper.postData(url, request, input, JSONObject.class);
		return out;
	}

	// 个人司法信息列表
	public BaseResponse<JSONObject> getPersonJudicialList(PersonJudicialListRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_PersonJudicialList;
		PersonJudicialListInput input = new PersonJudicialListInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<JSONObject> out = helper.postData(url, request, input, JSONObject.class);
		return out;
	}

	//反欺诈服务黑名单
	public BaseResponse<JSONObject> blacklistcheck(BlacklistcheckRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_Blacklistcheck;
		BlacklistcheckInput input = new BlacklistcheckInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<JSONObject> out = helper.postData(url, request, input, JSONObject.class);
		return out;
	}

	// 手机号码最近三月停机次数查询
	public BaseResponse<MobileHaltResult> getHaltResult(MobileHaltRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_MoblieHalt;
		MobileHaltInput input = new MobileHaltInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<MobileHaltResult> helper = new HttpIvkHelper<MobileHaltResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<MobileHaltResult> out = helper.postData(url, request, input, MobileHaltResult.class);
		return out;
	}

	// 手机号指定月通话天数最多的城市
	public BaseResponse<MobileMostCityResult> getMobileCityResult(MobileMostCityRequest request) throws Exception {

		String url = PubConstants.ZBADATA_URL + this.url_MobileCity;
		MobileMostCityInput input = new MobileMostCityInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<MobileMostCityResult> helper = new HttpIvkHelper<MobileMostCityResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<MobileMostCityResult> out = helper.postData(url, request, input, MobileMostCityResult.class);
		return out;
	}

	// 月平均消费水平
	public BaseResponse<MobileAverageFeeResult> getMobileAverageFee(MobileAverageFeeRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_MobileAverageFee;
		MobileAverageFeeInput input = new MobileAverageFeeInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<MobileAverageFeeResult> helper = new HttpIvkHelper<MobileAverageFeeResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<MobileAverageFeeResult> out = helper.postData(url, request, input, MobileAverageFeeResult.class);
		return out;
	}

	// 手机号码自然人接入号码个数查询
	public BaseResponse<MobileAccessNumResult> getMobileAccessNum(MobileAccessNumRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_MobileAccessNum;
		MobileAccessNumInput input = new MobileAccessNumInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<MobileAccessNumResult> helper = new HttpIvkHelper<MobileAccessNumResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<MobileAccessNumResult> out = helper.postData(url, request, input, MobileAccessNumResult.class);
		return out;
	}

	// 个人名下企业查询
	public BaseResponse<PersonEnterpriseResult> getPersonEnterprise(PersonEnterpriseRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_PersonEnterprise;
		PersonEnterpriseInput input = new PersonEnterpriseInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<PersonEnterpriseResult> helper = new HttpIvkHelper<PersonEnterpriseResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<PersonEnterpriseResult> out = helper.postData(url, request, input, PersonEnterpriseResult.class);
		return out;
	}

	// 个人涉诉全类查询
	public BaseResponse<PersonLitigationResult> getPersonLitigation(PersonLitigationRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_PersonLitigation;
		PersonLitigationInput input = new PersonLitigationInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<PersonLitigationResult> helper = new HttpIvkHelper<PersonLitigationResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<PersonLitigationResult> out = helper.postData(url, request, input, PersonLitigationResult.class);
		return out;
	}

	// 户籍才查询
	public BaseResponse<CensusRegisterResult> getCensusRegister(CensusRegisterRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_CensusRegister;
		CensusRegisterInput input = new CensusRegisterInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<CensusRegisterResult> helper = new HttpIvkHelper<CensusRegisterResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<CensusRegisterResult> out = helper.postData(url, request, input, CensusRegisterResult.class);
		return out;
	}

	// 银行卡信息查询
	public BaseResponse<BankCardTradeResult> getBankCardTrade(BankCardTradeRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_BankCardTrade;
		BankCardTradeInput input = new BankCardTradeInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<BankCardTradeResult> helper = new HttpIvkHelper<BankCardTradeResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<BankCardTradeResult> out = helper.postData(url, request, input, BankCardTradeResult.class);
		return out;
	}

	// 线下消费贷客群评分
	public BaseResponse<ConsCreditOfflineResult> getConsCreditOffline(ConsCreditOfflineRequest request)
			throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_ConsCreditOffline;
		ConsCreditOfflineInput input = new ConsCreditOfflineInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<ConsCreditOfflineResult> helper = new HttpIvkHelper<ConsCreditOfflineResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<ConsCreditOfflineResult> out = helper.postData(url, request, input, ConsCreditOfflineResult.class);
		return out;
	}

	// 航旅旅客价值等级查询
	public BaseResponse<AirTravelScoreResult> getAirTravelScore(AirTravelScoreRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_AirTravelScore;
		AirTravelScoreInput input = new AirTravelScoreInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<AirTravelScoreResult> helper = new HttpIvkHelper<AirTravelScoreResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<AirTravelScoreResult> out = helper.postData(url, request, input, AirTravelScoreResult.class);
		return out;
	}

	// 特殊名单核查
	public BaseResponse<SpecialListResult> getSpecialList(SpecialListRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_SpecialList;
		SpecialListIntput input = new SpecialListIntput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<SpecialListResult> helper = new HttpIvkHelper<SpecialListResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<SpecialListResult> out = helper.postData(url, request, input, SpecialListResult.class);
		return out;
	}

	// 航旅信息查询
	public BaseResponse<FlightInfoResult> getFlightInfo(FlightInfoRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_FlightInfo;
		FlightInfoInput input = new FlightInfoInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<FlightInfoResult> helper = new HttpIvkHelper<FlightInfoResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<FlightInfoResult> out = helper.postData(url, request, input, FlightInfoResult.class);
		return out;
	}

	// 手机号码自然人接入号码个数查询
	public BaseResponse<MobileNumbersResult> getMobileNumber(MobileNumbersRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_MobileNumbers;
		MobileNumbersInput input = new MobileNumbersInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<MobileNumbersResult> helper = new HttpIvkHelper<MobileNumbersResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<MobileNumbersResult> out = helper.postData(url, request, input, MobileNumbersResult.class);
		return out;
	}

	// 个人贷款逾期
	public BaseResponse<LoanOverdueResult> getLoanOverdue(LoanOverdueRequest request) throws Exception {
		String url = PubConstants.ZBADATA_URL + this.url_LoanOverdue;
		LoanOverdueInput input = new LoanOverdueInput();
		BeanUtils.copyProperties(request, input);

		HttpIvkHelper<LoanOverdueResult> helper = new HttpIvkHelper<LoanOverdueResult>();
		helper.setRegitSrv(regitSrv);
		BaseResponse<LoanOverdueResult> out = helper.postData(url, request, input, LoanOverdueResult.class);
		return out;
	}

	public static void main(String[] args) {
		ZbaDataHttpServiceImpl service = new ZbaDataHttpServiceImpl();
		/// service.CheckBankCard3Element("张杰", "110102199901019150",
		/// "6221532320013112557");

		// service.CheckBankCard4Element("13658043580","张杰",
		// "110102199901019150", "6221532320013112557");

		// service.checkPhone("张杰","18108160213", "110102199901019150");

		// service.checkAntiFraud("张杰2", "110102199901019150");

	}

	public MultipleLendOutput testdata() {
		String filename = "E:\\workspace\\riskManage\\src\\main\\resources\\msg\\MultipleLend.json";
		MultipleLendOutput output = null;
		try {
			String jsonstr = FileUtil.readFile(filename, null);
			System.out.println(jsonstr);
			JSONObject jsonObject = JSONObject.fromObject(jsonstr);
			output = JSONUtils.toBean(jsonObject, MultipleLendOutput.class);
			System.out.println(output);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}

}
