package duomi.control;

import duomi.com.constants.InterFaceConstants;
import duomi.dispatch.request.baiRong.BRCommonRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.BRongService.BRBizCommonService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bairong/biz",method = RequestMethod.POST)
public class BRongBizController {

    private static Logger log = Logger.getLogger(BRongBizController.class);

    @Autowired
    private BRBizCommonService brBizCommonService;


    @RequestMapping(value = "/getBizDetail")
    public ComResponse<JSONObject> getBizDetail(BRCommonRequest request) {
        request.setInterNo(InterFaceConstants.BR_BIZ_DETAIL_NO);
        request.setInterName(InterFaceConstants.BR_BIZ_DETAIL_NAME);
        request.setInterType(InterFaceConstants.BR_BIZ_DETAIL_TYPE);
        return brBizCommonService.getBizDetail(request);
    }

    @RequestMapping(value = "/getBizCompanyMap")
    public ComResponse<JSONObject> getBizCompanyMap(BRCommonRequest request) {
        request.setInterNo(InterFaceConstants.BR_BIZ_COMPANY_MAP_NO);
        request.setInterName(InterFaceConstants.BR_BIZ_COMPANY_MAP_NAME);
        request.setInterType(InterFaceConstants.BR_BIZ_COMPANY_MAP_TYPE);
        return brBizCommonService.getCompanyMap(request);
    }

    @RequestMapping(value = "/getBizBlackList")
    public ComResponse<JSONObject> getBlackList(BRCommonRequest request) {
        request.setInterNo(InterFaceConstants.BR_BIZ_BLACK_LIST_NO);
        request.setInterName(InterFaceConstants.BR_BIZ_BLACK_LIST_NAME);
        request.setInterType(InterFaceConstants.BR_BIZ_BLACK_LIST_TYPE);
        return brBizCommonService.getBlackList(request);
    }

    @RequestMapping(value = "/getBizKeySearch")
    public ComResponse<JSONObject> getBizKeySearch(BRCommonRequest request) {
        request.setInterNo(InterFaceConstants.BR_BIZ_BLACK_LIST_NO);
        request.setInterName(InterFaceConstants.BR_BIZ_BLACK_LIST_NAME);
        request.setInterType(InterFaceConstants.BR_BIZ_BLACK_LIST_TYPE);
        return brBizCommonService.getBizKeySearch(request);
    }

    @RequestMapping(value = "/getBizRelationship")
    public ComResponse<JSONObject> getBizRelationship(BRCommonRequest request) {
        request.setInterNo(InterFaceConstants.BR_BIZ_RELATIONSHIP_NO);
        request.setInterName(InterFaceConstants.BR_BIZ_RELATIONSHIP_NAME);
        request.setInterType(InterFaceConstants.BR_BIZ_RELATIONSHIP_TYPE);
        return brBizCommonService.getBizRelationship(request);
    }

    @RequestMapping(value = "/getBizExecutor")
    public ComResponse<JSONObject> getBizExecutor(BRCommonRequest request) {
        request.setInterNo(InterFaceConstants.BR_BIZ_EXECUTOR_NO);
        request.setInterName(InterFaceConstants.BR_BIZ_EXECUTOR_NAME);
        request.setInterType(InterFaceConstants.BR_BIZ_EXECUTOR_TYPE);
        return brBizCommonService.getBizExecutor(request);
    }

    @RequestMapping(value = "/getBizUnCreditExecutor")
    public ComResponse<JSONObject> getBizUnCreditExecutor(BRCommonRequest request) {
        request.setInterNo(InterFaceConstants.BR_BIZ_UNCREDIT_EXECUTOR_NO);
        request.setInterName(InterFaceConstants.BR_BIZ_UNCREDIT_EXECUTOR_NAME);
        request.setInterType(InterFaceConstants.BR_BIZ_UNCREDIT_EXECUTOR_TYPE);
        return brBizCommonService.getBizUncreditExecutor(request);
    }
    @RequestMapping(value = "/getCourtAnnouncement")
    public ComResponse<JSONObject> getCourtAnnouncement (BRCommonRequest request) {
        request.setInterNo(InterFaceConstants.BR_BIZ_COURT_ANNOUNCEMENT_NO);
        request.setInterName(InterFaceConstants.BR_BIZ_COURT_ANNOUNCEMENT_NAME);
        request.setInterType(InterFaceConstants.BR_BIZ_COURT_ANNOUNCEMENT_TYPE);
        return brBizCommonService.getBizCourtAnnouncement(request);
    }

    @RequestMapping(value = "/getBizFour")
    public ComResponse<JSONObject> getBizFour (BRCommonRequest request) {
        request.setInterNo(InterFaceConstants.BR_BIZ_FOUR_NO);
        request.setInterName(InterFaceConstants.BR_BIZ_FOUR_NAME);
        request.setInterType(InterFaceConstants.BR_BIZ_FOUR_TYPE);
        return brBizCommonService.getBizFour(request);
    }

}
