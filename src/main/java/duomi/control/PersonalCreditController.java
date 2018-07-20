package duomi.control;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.blacklist.BlackListResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.BlackListRequest;
import duomi.dispatch.request.PersonalCreditRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.PersonalCreditService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DEllComputer on 2018/5/9.
 * 智宝-个人信用验证
 */
@Controller
public class PersonalCreditController {
    private static Logger log = Logger.getLogger(PersonalCreditController.class);

    @Autowired
    private PersonalCreditService personalCreditService;

    @RequestMapping(value = "/personalCredit", method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> personalCredit(PersonalCreditRequest request) {
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.ZB_PERSONALCREDIT_NO);
        request.setInterName(InterFaceConstants.ZB_PERSONALCREDIT_NAME);// 个人信用验证
        request.setInterType(InterFaceConstants.ZB_PERSONALCREDIT_TYPE);
        try {
            rsp = personalCreditService.personalCredit(request);
        } catch (HttpBizException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            rsp = new ResponseSimpleHelper<JSONObject>().createComErrorRsp(request, null, e);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            rsp = new ResponseSimpleHelper<JSONObject>().createComErrorRsp(request, null);
        }
        return rsp;
    }


}
