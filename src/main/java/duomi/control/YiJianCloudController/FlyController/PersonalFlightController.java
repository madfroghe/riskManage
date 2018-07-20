package duomi.control.YiJianCloudController.FlyController;

import duomi.com.constants.InterFaceConstants;
import duomi.com.httpIvk.services.YJCloudHttpService;
import duomi.dispatch.request.yijianCloud.*;
import duomi.dispatch.response.ComResponse;
import duomi.services.yijianCloudServices.*;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DEllComputer on 2018/3/1.
 * 易简云-翱翔系列接口
 */
@Controller
@RequestMapping("/yjcloud")
public class PersonalFlightController {
    private static Logger log = Logger.getLogger(PersonalFlightController.class);

    @Autowired
    private PersonalFlightService personalFlightService;

    @Autowired
    private CustomerHighSeatService customerHighSeatService;

    @Autowired
    private ConsumptionPreferenceService consumptionPreferenceService;

    @Autowired
    private ConsumptionCapabilityAssessmentService consumptionCapabilityAssessmentService;

    @Autowired
    private CustomerValueLevelService customerValueLevelService;

    @Autowired
    private GoAboardService goAboardService;

    @Autowired
    private CheckTheopportunityService checkTheopportunityService;

    @Autowired
    private PersonJzcxService personJzcxService;

    /**
     * 易简云-个人乘机信息查询
     * */
    @RequestMapping(value = "/personalFlight" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> personalFlight(PersonalFlightRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_PERSONAL_FLIGHT_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_PERSONAL_FLIGHT_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_PERSONAL_FLIGHT_TYPE);

        try {
            rsp = personalFlightService.personalFlight(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

    /**
     * 易简云-航空行为接口
     * */
    @RequestMapping(value = "/customerHighSeat" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> customerHighSeat(CustomerHighSeatRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_FLIGHT_CUSTOMER_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_FLIGHT_CUSTOMER_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_FLIGHT_CUSTOMER_TYPE);

        try {
            rsp = customerHighSeatService.customerHighSeat(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

    /**
     * 易简云-航旅消费等级
     * */
    @RequestMapping(value = "/consumptionPreference" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> consumptionPreference(ConsumptionPreferenceRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_CONSUMPTION_PREFERENCE_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_CONSUMPTION_PREFERENCE_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_CONSUMPTION_PREFERENCE_TYPE);

        try {
            rsp = consumptionPreferenceService.consumptionPreference(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

    /**
     * 易简云-航旅消费能力评估
     * */
    @RequestMapping(value = "/consumptionCapabilityAssessment" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> consumptionCapabilityAssessment(ConsumptionCapabilityAssessmentRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_CONSUMPTION_CAPABILITY_ASSESSMENT_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_CONSUMPTION_CAPABILITY_ASSESSMENT_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_CONSUMPTION_CAPABILITY_ASSESSMENT_TYPE);

        try {
            rsp = consumptionCapabilityAssessmentService.consumptionCapabilityAssessment(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

    /**
     * 易简云-旅客价值评级接口
     * */
    @RequestMapping(value = "/customerValueLevel" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> customerValueLevel(CustomerValueLevelRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_CUSTOMER_VALUELEVEL_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_CUSTOMER_VALUELEVEL_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_CUSTOMER_VALUELEVEL_TYPE);

        try {
            rsp = customerValueLevelService.customerValueLevel(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

    /**
     * 易简云-是否乘机出国
     * */
    @RequestMapping(value = "/goAboard" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> goAboard(GoAboardRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_GO_ABOARD_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_GO_ABOARD_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_GO_ABOARD_TYPE);

        try {
            rsp = goAboardService.goAboard(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

    /**
     * 易简云-是否乘机接口
     * */
    @RequestMapping(value = "/isCheckTheopportunity" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> isCheckTheopportunity(CheckTheopportunityRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_CHECKTHE_OPPORTUNITY_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_CHECKTHE_OPPORTUNITY_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_CHECKTHE_OPPORTUNITY_TYPE);

        try {
            rsp = checkTheopportunityService.isCheckTheopportunity(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

    /**
     * 易简云-个人信息精准查询
     * */
    @RequestMapping(value = "/personJzcx" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> getPersonJzcx(PersonJzcxRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_PERSONJZCX_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_PERSONJZCX_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_PERSONJZCX_TYPE);

        try {
            rsp = personJzcxService.getPersonJzcx(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }





}
