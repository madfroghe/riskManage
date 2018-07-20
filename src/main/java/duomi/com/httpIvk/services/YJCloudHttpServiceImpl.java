package duomi.com.httpIvk.services;

import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.YJCloud.param.PersonalFlightInput;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.com.httpIvk.jiguang.param.AntiFraudInput;
import duomi.com.httpIvk.param.JiGResponse;
import duomi.com.httpIvk.yiJianCloud.param.YiJianCloudResponse;
import duomi.dbMap.bean.EigenfactorPo;
import duomi.dispatch.request.yijianCloud.*;
import duomi.services.OutsideServiceRegistService;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DEllComputer on 2018/3/12.
 */
@Service
public class YJCloudHttpServiceImpl implements YJCloudHttpService {

    //易简云-个人乘机
    public final static String url_peronal_flight = "aviation/personalFlight";

    //易简云-航空行为
    public final static String url_customer_highSeat = "aviation/isCustomerHighSeat";

    //易简云-航旅消费等级
    public final static String url_consumption_preference = "aviation/consumptionPreference";

    //易简云-航旅消费能力评估
    public final static String url_consumption_capability_assessment = "aviation/consumptionCapabilityAssessment";

    //易简云-旅客价值评级接口
    public final static String url_customer_value_level = "aviation/customerValueLevel";

    //易简云-是否登机出国
    public final static String url_go_aboard = "aviation/hasCustomerGoAboard";

    //易简云-是否乘机
    public final static String url_check_theopportunity = "aviation/isCheckTheopportunity";

    //易简云-个人学历
    public final static String url_education = "society/queryEducation";

    //易简云-个人信息精准查询
    public final static String url_personJzcx = "law/personJzcx";

    public final static String url_loanOverDue = "telphone/overdue";

    //易简云-定位开通接口
    public final static String url_locationOrder = "telphone/locationOrder";

    //易简云-状态查询接口
    public final static String url_locationStatus = "telphone/locationStatus";

    //易简云-金融黑名单
    public final static String url_financeBlackList = "law/financeBlackList";

    //易简云-企业基本信息
    public final static String url_entBaseInfo = "enterprise/uQueryEntBaseInfo";

    //易简云-个人对外投资
    public final static String url_entByPerson = "enterprise/uQueryEntByPerson";

    //易简云-手机号工作地区验证
    public final static String url_workPlaceCheck = "tellocation/workPlaceCheck";

    //易简云-手机号居住地区验证
    public final static String url_housePlaceCheck = "tellocation/housePlaceCheck";

    @Autowired
    private OutsideServiceRegistService regitSrv;
    /**
     * 易简云-个人乘机
     * */
    public YiJianCloudResponse<JSONObject> personalFlight(PersonalFlightRequest request) throws Exception {

        String url = PubConstants.YJCLOUD_URL + this.url_peronal_flight;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("authID",request.getAuthID());
        map.put("certCode",request.getCertCode());
        map.put("userName",request.getUserName());
        map.put("queryMonth",request.getQueryMonth());

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-航空行为
     * */
    public YiJianCloudResponse<JSONObject> customerHighSeat(CustomerHighSeatRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_customer_highSeat;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("authID",request.getAuthID());
        map.put("certCode",request.getCertCode());

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-航旅消费等级
     * */
    public YiJianCloudResponse<JSONObject> consumptionPreference(ConsumptionPreferenceRequest request) throws Exception {

        String url = PubConstants.YJCLOUD_URL + this.url_consumption_preference;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("authID",request.getAuthID());
        map.put("passport",request.getPassport());

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"POST");

        return out;
    }

    /**
     * 易简云-航旅消费能力评估
     * */
    public YiJianCloudResponse<JSONObject> consumptionCapabilityAssessment(ConsumptionCapabilityAssessmentRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_consumption_capability_assessment;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("certCode",request.getCertCode());
        map.put("authID",request.getAuthID());

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-旅客价值评级接口
     * */
    public YiJianCloudResponse<JSONObject> customerValueLevel(CustomerValueLevelRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_customer_value_level;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("certCode",request.getCertCode());
        map.put("authID",request.getAuthID());

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-是否乘机出国
     * */
    public YiJianCloudResponse<JSONObject> goAboard(GoAboardRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_go_aboard;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("certCode",request.getCertCode());
        map.put("authID",request.getAuthID());

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-是否乘机
     * */
    public YiJianCloudResponse<JSONObject> checkTheopportunity(CheckTheopportunityRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_check_theopportunity;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("certCode",request.getCertCode());
        map.put("authID",request.getAuthID());

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-个人学历查询
     * */
    public YiJianCloudResponse<JSONObject> education(EducationRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_education;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("certCode",request.getCertCode());
        map.put("authID",request.getAuthID());
        map.put("userName",request.getUserName());

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-个人信息精准查询
     * */
    public YiJianCloudResponse<JSONObject> getPersonJzcx(PersonJzcxRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_personJzcx;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("certCode",request.getCertCode());
        map.put("authID",request.getAuthID());
        map.put("userName",request.getUserName());
        if(request.getPageNo() != 0 ){
            map.put("pageNo",request.getPageNo());
        }
        if(!"".equals(request.getRange())){
            map.put("range",request.getRange());
        }

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-信贷逾期详情集合查询
     * */
    public YiJianCloudResponse<JSONObject> getLoanOverDue(LoanOverDueRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_loanOverDue;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("certCode",request.getCertCode());
        map.put("authID",request.getAuthID());
        map.put("userName",request.getUserName());
        map.put("telNO",request.getTelNO());
        if(request.getQueryMonth() != null && !"".equals(request.getQueryMonth())){
            map.put("queryMonth",request.getQueryMonth());
        }

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-手机号码强授权定位查询-定位开通接口
     * */
    public YiJianCloudResponse<JSONObject> locationOrder(LocationOrderRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_locationOrder;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("authID",request.getAuthID());
        map.put("telNO",request.getTelNO());

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    public YiJianCloudResponse<JSONObject> locaionStatus(LocationStatusRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_locationStatus;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("authID",request.getAuthID());
        map.put("telNO",request.getTelNO());

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-金融黑名单查询
     * */
    public YiJianCloudResponse<JSONObject> financeBlackList(FinanceBlackListRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_financeBlackList;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("certCode",request.getCertCode());
        map.put("authID",request.getAuthID());
        map.put("userName",request.getUsername());
        map.put("telNO",request.getTelNO());


        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-手机号工作地区验证
     * */
    public YiJianCloudResponse<JSONObject> workPlaceCheck(WorkPlaceCheckRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_workPlaceCheck;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("latitude",request.getLatitude());
        map.put("authID",request.getAuthID());
        map.put("longitude",request.getLongitude());
        map.put("telNO",request.getTelNO());


        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-手机号居住地区验证
     * */
    public YiJianCloudResponse<JSONObject> housePlaceCheck(HousePlaceCheckRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_housePlaceCheck;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("latitude",request.getLatitude());
        map.put("authID",request.getAuthID());
        map.put("longitude",request.getLongitude());
        map.put("telNO",request.getTelNO());


        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-企业基本信息
     * */
    public YiJianCloudResponse<JSONObject> entBaseInfo(QueryEntBaseInfoRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_entBaseInfo;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("authID",request.getAuthID());
        map.put("entityName",request.getEntityName());

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }

    /**
     * 易简云-个人对外投资信息查询
     * */
    public YiJianCloudResponse<JSONObject> queryEntByPerson(QueryEntByPersonRequest request) throws Exception {
        String url = PubConstants.YJCLOUD_URL + this.url_entByPerson;

        Map<String , Object> map = new HashMap<String , Object>();
        map.put("entityName",request.getEntityName());
        map.put("userName",request.getUserName());
        map.put("authID",request.getAuthID());

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        YiJianCloudResponse<JSONObject> out = helper.getDataYijianCloud(url , map , request , JSONObject.class,"GET");

        return out;
    }
}
