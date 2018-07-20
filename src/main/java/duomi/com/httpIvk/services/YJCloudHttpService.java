package duomi.com.httpIvk.services;

import duomi.com.httpIvk.yiJianCloud.param.YiJianCloudResponse;
import duomi.dispatch.request.yijianCloud.*;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/3/12.
 */
public interface YJCloudHttpService {

    //易简云-个人乘机信息
    public YiJianCloudResponse<JSONObject> personalFlight(PersonalFlightRequest request) throws Exception;

    //易简云-航空行为接口
    public YiJianCloudResponse<JSONObject> customerHighSeat(CustomerHighSeatRequest request) throws Exception;

    //易简云-航旅消费等级
    public YiJianCloudResponse<JSONObject> consumptionPreference(ConsumptionPreferenceRequest request) throws Exception;

    //易简云-航旅消费等级
    public YiJianCloudResponse<JSONObject> consumptionCapabilityAssessment(ConsumptionCapabilityAssessmentRequest request) throws Exception;

    //易简云-旅客价值评级接口
    public YiJianCloudResponse<JSONObject> customerValueLevel(CustomerValueLevelRequest request) throws Exception;

    //易简云-是否乘机出国接口
    public YiJianCloudResponse<JSONObject> goAboard(GoAboardRequest request) throws Exception;

    //易简云-是否乘机接口
    public YiJianCloudResponse<JSONObject> checkTheopportunity(CheckTheopportunityRequest request) throws Exception;

    //易简云-个人学历接口
    public YiJianCloudResponse<JSONObject> education(EducationRequest request) throws Exception;

    //易简云-个人信息精准查询
    public YiJianCloudResponse<JSONObject> getPersonJzcx(PersonJzcxRequest request) throws Exception;

    //易简云-信贷逾期详情集合查询
    public YiJianCloudResponse<JSONObject> getLoanOverDue(LoanOverDueRequest request) throws Exception;

    //易简云-手机号码强授权定位查询-定位开通接口
    public YiJianCloudResponse<JSONObject> locationOrder(LocationOrderRequest request) throws Exception;

    //易简云-手机号码强授权定位查询-状态查询接口
    public YiJianCloudResponse<JSONObject> locaionStatus(LocationStatusRequest request) throws Exception;

    //易简云-金融黑名单查询
    public YiJianCloudResponse<JSONObject> financeBlackList(FinanceBlackListRequest request) throws Exception;

    //易简云-手机号工作地区验证
    public YiJianCloudResponse<JSONObject> workPlaceCheck(WorkPlaceCheckRequest request) throws Exception;

    //易简云-手机号居住地区验证
    public YiJianCloudResponse<JSONObject> housePlaceCheck(HousePlaceCheckRequest request) throws Exception;

    //易简云-企业基本信息
    public YiJianCloudResponse<JSONObject> entBaseInfo(QueryEntBaseInfoRequest request) throws Exception;

    //易简云-个人对外投资信息查询
    public YiJianCloudResponse<JSONObject> queryEntByPerson(QueryEntByPersonRequest request) throws Exception;
}
