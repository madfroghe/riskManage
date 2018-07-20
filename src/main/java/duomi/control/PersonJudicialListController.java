package duomi.control;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.PersonJudicialListRequest;
import duomi.dispatch.request.PersonalCreditRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.PersonJudicialListService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DEllComputer on 2018/5/9.
 * 智宝-个人司法信息列表
 */
@Controller
public class PersonJudicialListController {

    private static Logger log = Logger.getLogger(PersonJudicialListController.class);

    @Autowired
    private PersonJudicialListService personJudicialListService;


    @RequestMapping(value = "/personJudicialList", method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> personJudicialList(PersonJudicialListRequest request) {
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.ZB_PERSONJUDICIALLIST_NO);
        request.setInterName(InterFaceConstants.ZB_PERSONJUDICIALLIST_NAME);// 个人司法信息列表
        request.setInterType(InterFaceConstants.ZB_PERSONJUDICIALLIST_TYPE);
        try {
            rsp = personJudicialListService.personJudicialList(request);
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
