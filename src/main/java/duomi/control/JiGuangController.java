package duomi.control;

import duomi.com.constants.InterFaceConstants;
import duomi.dbMap.bean.EigenfactorPo;
import duomi.dispatch.request.*;
import duomi.dispatch.response.ComResponse;
import duomi.services.*;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 极光接口
 * Created by DEllComputer on 2018/1/30.
 */
@Controller
@RequestMapping("/jiguang")
public class JiGuangController {
    private static Logger log = Logger.getLogger(JiGuangController.class);

    @Autowired
    private EigenfactorService eigenfactorService;

    @Autowired
    private JGBlackListService jgBlackListService;

    @Autowired
    private JGLbsCheckService jgLbsCheckService;

    @Autowired
    private JGLbsBlurCheckService jgLbsBlurCheckService;

    @Autowired
    private JGUserAddressService jgUserAddressService;

    @Autowired
    private JGUserTagService jgUserTagService;
    /**
     * 极光反欺诈-特征因子接口
     * */
    @RequestMapping(value = "/getEigenfactor", method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<EigenfactorPo> getEigenfactor(EigenfactorRequest resquest){
        ComResponse<EigenfactorPo> rsp = null;
        resquest.setInterNo(InterFaceConstants.JG_EIGENFACTOR_NO);
        resquest.setInterName(InterFaceConstants.JG_EIGENFACTOR_NAME);
        resquest.setInterType(InterFaceConstants.JG_EIGENFACTOR_TYPE);

        try {
            rsp = eigenfactorService.getEigenfactor(resquest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsp;
    }

    /**
     * 极光反欺诈-黑名单接口
     * */
    @RequestMapping(value = "/getBlackList", method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> getBalckList(JGBlackListRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.JG_BLACKLIST_NO);
        request.setInterName(InterFaceConstants.JG_BLACKLIST_NAME);
        request.setInterType(InterFaceConstants.JG_BLACKLIST_TYPE);

        try {
            rsp = jgBlackListService.getBlackList(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

    /**
     * 极光-LBS验真接口(分级验真)
     * */
    @RequestMapping(value = "/lbsCheck" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> lbsCheck(LbsCheckRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.JG_LBSCHECK_NO);
        request.setInterName(InterFaceConstants.JG_LBSCHECK_NAME);
        request.setInterType(InterFaceConstants.JG_LBSCHECK_TYPE);

        try {
            rsp = jgLbsCheckService.lbsCheck(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

    /**
     * 极光-LBS验真接口(模糊匹配)
     * */
    @RequestMapping(value = "/lbsBlurCheck" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> lbsCheckBlur(JGLbsBlurCheckRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.JG_LBSCHECKBLUR_NO);
        request.setInterName(InterFaceConstants.JG_LBSCHECKBLUR_NAME);
        request.setInterType(InterFaceConstants.JG_LBSCHECKBLUR_TYPE);

        try {
            rsp = jgLbsBlurCheckService.lbsBlurCheck(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

    /**
     * 极光-用户常用地址
     * */
    @RequestMapping(value = "/users/address" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> usersAddress(JGUsersAddressRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.JG_USERS_ADDRESS_NO);
        request.setInterName(InterFaceConstants.JG_USERS_ADDRESS_NAME);
        request.setInterType(InterFaceConstants.JG_USERS_ADDRESS_TYPE);

        try {
            rsp = jgUserAddressService.userAddress(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

    /**
     * 极光-用户标签接口
     * */
    @RequestMapping(value = "/users/tag" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> usersTag(JGUsersTagRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.JG_USERS_TAG_NO);
        request.setInterName(InterFaceConstants.JG_USERS_TAG_NAME);
        request.setInterType(InterFaceConstants.JG_USERS_TAG_TYPE);

        try {
            rsp = jgUserTagService.userTag(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

}
