package duomi.control;

import duomi.com.constants.InterFaceConstants;
import duomi.dispatch.request.wightknight.DecisionRequest;
import duomi.dispatch.request.wightknight.LoanReportRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.wightknightservice.WKDecisionService;
import duomi.services.wightknightservice.WKLoanReportService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DEllComputer on 2018/2/27.
 * 白骑士接口
 *
 */
@Controller
@RequestMapping("/wightknight")
public class WightKnightController {
    private static Logger log = Logger.getLogger(WightKnightController.class);

    @Autowired
    private WKDecisionService wkDecisionService;

    @Autowired
    private WKLoanReportService wkLoanReportService;


    /**
     * 白骑士-反欺诈云
     * */
    @RequestMapping(value = "/decision" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> decision(DecisionRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.WHIGHT_KNIGHT_DECISION_NO);
        request.setInterName(InterFaceConstants.WHIGHT_KNOGHT_DECISION_NAME);
        request.setInterType(InterFaceConstants.WHIGHT_KNOGHT_DECISION_TYPE);

        try {
            rsp = wkDecisionService.decision(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

    /**
     * 白骑士-信贷报告
     * */
    @RequestMapping(value = "/loanreport" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> loanReport(LoanReportRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.WHIGHT_KNIGHT_LOAN_REPORT_NO);
        request.setInterName(InterFaceConstants.WHIGHT_KNOGHT_LOAN_REPORT_NAME);
        request.setInterType(InterFaceConstants.WHIGHT_KNOGHT_LOAN_REPORT_TYPE);

        try {
            rsp = wkLoanReportService.loanReport(request);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return rsp;
    }


}
