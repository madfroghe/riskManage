package duomi.com.httpIvk.services;

import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.antifraud.AntiFraudResult;
import duomi.com.httpIvk.param.badrecords.BadrecordResult;
import duomi.com.httpIvk.param.bankCard.BackCardFourELementResult;
import duomi.com.httpIvk.param.bankCard.BackCardThreeELementResult;
import duomi.com.httpIvk.param.bankCard.BankCardTradeResult;
import duomi.com.httpIvk.param.blacklist.BlackListResult;
import duomi.com.httpIvk.param.consume.ConsCreditOfflineResult;
import duomi.com.httpIvk.param.creditscore.CreditScoreResult;
import duomi.com.httpIvk.param.education.EducationCheckResult;
import duomi.com.httpIvk.param.enterprise.PersonEnterpriseResult;
import duomi.com.httpIvk.param.idcard.IdCardCheckResult;
import duomi.com.httpIvk.param.litigation.PersonLitigationResult;
import duomi.com.httpIvk.param.multipleLend.MultipleCreditAppResult;
import duomi.com.httpIvk.param.multipleLend.MultipleLendResult;
import duomi.com.httpIvk.param.overdue.LoanOverdueResult;
import duomi.com.httpIvk.param.phone.MobileAccessNumResult;
import duomi.com.httpIvk.param.phone.MobileAverageFeeResult;
import duomi.com.httpIvk.param.phone.MobileHaltResult;
import duomi.com.httpIvk.param.phone.MobileMostCityResult;
import duomi.com.httpIvk.param.phone.MobileNumbersResult;
import duomi.com.httpIvk.param.phone.MobileStatusResult;
import duomi.com.httpIvk.param.phone.PhoneCheckResult;
import duomi.com.httpIvk.param.phone.PhoneOnlineDurationResult;
import duomi.com.httpIvk.param.register.CensusRegisterResult;
import duomi.com.httpIvk.param.special.SpecialListResult;
import duomi.com.httpIvk.param.travel.AirTravelScoreResult;
import duomi.com.httpIvk.param.travel.FlightInfoResult;
import duomi.dispatch.request.*;
import net.sf.json.JSONObject;

public interface ZbaDataHttpService {
	// 反欺诈云
	public BaseResponse<AntiFraudResult> checkAntiFraud(AntiFraudRequest request) throws Exception;

	// 银行卡三要素权鉴
	public BaseResponse<BackCardThreeELementResult> CheckBankCard3Element(BackCard3ERequest request) throws Exception;

	// 银行卡四要素权鉴
	public BaseResponse<BackCardFourELementResult> CheckBankCard4Element(BackCard4ERequest request) throws Exception;

	// 手机号三要素权鉴
	public BaseResponse<PhoneCheckResult> checkPhone(Mobile3ERequest request) throws Exception;

	// 多投贷款
	public BaseResponse<MultipleLendResult> checkMultipleLend(MultipleLendRequest request) throws Exception;

	// 贷款多次申请
	public BaseResponse<MultipleCreditAppResult> checkMultipleCreditApp(MultipleCreditAppRequest request)
			throws Exception;

	// 身份证一致性验证
	public BaseResponse<IdCardCheckResult> checkIdCard(IdCardRequest request) throws Exception;

	// 学历查询
	public BaseResponse<EducationCheckResult> checkEducation(EducationCheckquest request) throws Exception;

	// 不良信息核查
	public BaseResponse<BadrecordResult> checkBadrecord(BadrecordRequest request) throws Exception;

	// 个人信用评分
	public BaseResponse<CreditScoreResult> getCreditScore(CreditScoreRequest request) throws Exception;

	// 手机号在网时长查询(三大运营商)
	public BaseResponse<PhoneOnlineDurationResult> getPhoneOnlineDuration(PhoneOnlineDurationRequest request)
			throws Exception;

	// 手机号状态查询(三大运营商)
	public BaseResponse<MobileStatusResult> getMobileStatus(MobileStatusRequest request) throws Exception;

	// 个人黑名单
	public BaseResponse<BlackListResult> getBlackList(BlackListRequest request) throws Exception;

	//个人信用查询
	public BaseResponse<JSONObject> getPersonalCredit(PersonalCreditRequest request) throws Exception;

	//个人司法信息列表
	public BaseResponse<JSONObject> getPersonJudicialList(PersonJudicialListRequest request) throws Exception;

	//反欺诈服务黑名单
	public BaseResponse<JSONObject> blacklistcheck(BlacklistcheckRequest request) throws Exception;

	// 手机号码最近三月停机次数查询
	public BaseResponse<MobileHaltResult> getHaltResult(MobileHaltRequest request) throws Exception;

	// 手机号指定月通话天数最多的城市
	public BaseResponse<MobileMostCityResult> getMobileCityResult(MobileMostCityRequest request) throws Exception;

	// 月平均消费水平
	public BaseResponse<MobileAverageFeeResult> getMobileAverageFee(MobileAverageFeeRequest request) throws Exception;

	// 手机号码自然人接入号码个数查询
	public BaseResponse<MobileAccessNumResult> getMobileAccessNum(MobileAccessNumRequest request) throws Exception;

	// 个人名下企业查询
	public BaseResponse<PersonEnterpriseResult> getPersonEnterprise(PersonEnterpriseRequest request) throws Exception;

	// 个人涉诉全类查询
	public BaseResponse<PersonLitigationResult> getPersonLitigation(PersonLitigationRequest request) throws Exception;

	// 户籍查询
	public BaseResponse<CensusRegisterResult> getCensusRegister(CensusRegisterRequest request) throws Exception;

	// 银行卡
	public BaseResponse<BankCardTradeResult> getBankCardTrade(BankCardTradeRequest request) throws Exception;

	// 线下消费贷客群评分
	public BaseResponse<ConsCreditOfflineResult> getConsCreditOffline(ConsCreditOfflineRequest request)
			throws Exception;

	// 航旅旅客价值等级查询
	public BaseResponse<AirTravelScoreResult> getAirTravelScore(AirTravelScoreRequest request) throws Exception;

	// 航旅信息查询
	public BaseResponse<FlightInfoResult> getFlightInfo(FlightInfoRequest request) throws Exception;

	// 特殊名单核查
	public BaseResponse<SpecialListResult> getSpecialList(SpecialListRequest request) throws Exception;

	// 手机号码自然人接入号码个数查询
	public BaseResponse<MobileNumbersResult> getMobileNumber(MobileNumbersRequest request) throws Exception;

	// 个人贷款逾期
	public BaseResponse<LoanOverdueResult> getLoanOverdue(LoanOverdueRequest request) throws Exception;

}
