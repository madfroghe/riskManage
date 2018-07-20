package duomi.control.YiJianCloudController;

import duomi.com.constants.InterFaceConstants;
import duomi.dispatch.request.yijianCloud.FinanceBlackListRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.yijianCloudServices.FinanceBlackListService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DEllComputer on 2018/5/8.
 *
 * 易简云-金融黑名单查询
 */
@Controller
@RequestMapping("/yjcloud")
public class FinanceBlackListController {

    private static Logger log = Logger.getLogger(FinanceBlackListController.class);
    @Autowired
    private FinanceBlackListService financeBlackListService;

    @RequestMapping(value = "/financeBlackList" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> loanOverDue(FinanceBlackListRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_FINANCE_BLACK_LIST_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_FINANCE_BLACK_LIST_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_FINANCE_BLACK_LIST_TYPE);

        try {
            rsp = financeBlackListService.financeBlackList(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

}
