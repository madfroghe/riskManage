package duomi.control;

import duomi.com.constants.InterFaceConstants;
import duomi.dispatch.request.baiRong.BRApplyLoanRequest;
import duomi.dispatch.request.baiRong.BRExecutionRequest;
import duomi.dispatch.request.baiRong.BRSpecialListRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.BRongService.BRApplyLoanService;
import duomi.services.BRongService.BRExecutionService;
import duomi.services.BRongService.BRSpecialListService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 百融接口
 */
@Controller
@RequestMapping("/bairong")
public class BRongController {

    private static Logger log = Logger.getLogger(BRongController.class);

    @Autowired
    private BRSpecialListService brSpecialListService;
    @Autowired
    private BRApplyLoanService brApplyLoanService;
    @Autowired
    private BRExecutionService brExecutionService;

    /**
     * 百融-特殊名单核查
     * */
    @RequestMapping(value = "/getSpecialList" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> getSpecialList(BRSpecialListRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.BR_SPECIAL_LIST_NO);
        request.setInterName(InterFaceConstants.BR_SPECIAL_LIST_NAME);
        request.setInterType(InterFaceConstants.BR_SPECIAL_LIST_TYPE);

        try {
            rsp = brSpecialListService.getSpecialList(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

    /**
     * 百融-多次申请核查
     * */
    @RequestMapping(value = "/applyLoan" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> getApplyLoan(BRApplyLoanRequest request){

        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.BR_APPLY_LOAN_NO);
        request.setInterName(InterFaceConstants.BR_APPLY_LOAN_NAME);
        request.setInterType(InterFaceConstants.BR_APPLY_LOAN_TYPE);

        try {
            rsp = brApplyLoanService.getApplyLoan(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;

    }

    /**
     * 百融-法院被执行人-个人版
     * */
    @RequestMapping(value = "/execution" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> getExecution(BRExecutionRequest request) throws Exception{

        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.BR_EXECUTION_NO);
        request.setInterName(InterFaceConstants.BR_EXECUTION_NAME);
        request.setInterType(InterFaceConstants.BR_EXECUTION_TYPE);

        try {
            rsp = brExecutionService.execution(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return rsp;

    }


}
