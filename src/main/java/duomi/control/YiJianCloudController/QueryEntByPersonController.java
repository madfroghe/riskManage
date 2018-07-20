package duomi.control.YiJianCloudController;

import duomi.com.constants.InterFaceConstants;
import duomi.dispatch.request.yijianCloud.QueryEntByPersonRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.yijianCloudServices.QueryEntByPersonService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DEllComputer on 2018/5/9.
 *
 * 易简云-个人对外投资信息查询
 */
@Controller
@RequestMapping("/yjcloud")
public class QueryEntByPersonController {

    private static Logger log = Logger.getLogger(QueryEntByPersonController.class);

    @Autowired
    private QueryEntByPersonService queryEntByPersonService;


    @RequestMapping(value = "/queryEntByPerson" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> queryEntByPerson(QueryEntByPersonRequest request){
    ComResponse<JSONObject> rsp = null;
    request.setInterNo(InterFaceConstants.YJ_CLOUD_QUERY_ENT_BY_PERSON_NO);
    request.setInterName(InterFaceConstants.YJ_CLOUD_QUERY_ENT_BY_PERSON_NAME);
    request.setInterType(InterFaceConstants.YJ_CLOUD_QUERY_ENT_BY_PERSON_TYPE);

    try {
        rsp = queryEntByPersonService.queryEntByPerson(request);
    } catch (Exception e) {
        e.printStackTrace();
    }

    return rsp;
}

}
