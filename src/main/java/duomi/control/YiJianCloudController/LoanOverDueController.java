package duomi.control.YiJianCloudController;

import duomi.com.constants.InterFaceConstants;
import duomi.dispatch.request.yijianCloud.LoanOverDueRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.yijianCloudServices.LoanOverDueService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DEllComputer on 2018/4/10.
 * 易简云-信贷逾期详情集合查询
 */
@Controller
@RequestMapping("/yjcloud")
public class LoanOverDueController {

    private static Logger log = Logger.getLogger(LoanOverDueController.class);

    @Autowired
    private LoanOverDueService loanOverDueService;

    @RequestMapping(value = "/loanoverDue" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> loanOverDue(LoanOverDueRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_LOANOVERDUE_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_LOANOVERDUE_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_LOANOVERDUE_TYPE);

        try {
            rsp = loanOverDueService.loanOverDue(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }


}
