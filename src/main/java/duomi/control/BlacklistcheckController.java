package duomi.control;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.BlacklistcheckRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.BlacklistcheckService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DEllComputer on 2018/5/16.
 *
 * 反欺诈服务黑名单
 */
@Controller
public class BlacklistcheckController {

    private static Logger log = Logger.getLogger(BlacklistcheckController.class);

    @Autowired
    private BlacklistcheckService service;

    @RequestMapping(value = "/blacklistcheck", method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> blacklistcheck(BlacklistcheckRequest request) {
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.ZB_BLACKLISTCHECK_NO);
        request.setInterName(InterFaceConstants.ZB_BLACKLISTCHECK_NAME);// 反欺诈服务黑名单
        request.setInterType(InterFaceConstants.ZB_BLACKLISTCHECK_TYPE);
        try {
            rsp = service.blacklistcheck(request);
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
