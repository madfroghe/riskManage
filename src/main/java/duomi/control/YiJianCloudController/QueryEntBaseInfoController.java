package duomi.control.YiJianCloudController;

import duomi.com.constants.InterFaceConstants;
import duomi.dispatch.request.yijianCloud.QueryEntBaseInfoRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.yijianCloudServices.QueryEntBseInfoService;
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
 * 易简云-企业基本信息查询
 */
@Controller
@RequestMapping("/yjcloud")
public class QueryEntBaseInfoController {

    private static Logger log = Logger.getLogger(QueryEntBaseInfoController.class);

    @Autowired
    private QueryEntBseInfoService queryEntBseInfoService;

    @RequestMapping(value = "/queryEntBaseInfo" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> queryEntBaseInfo(QueryEntBaseInfoRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_ENT_BASE_INFO_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_ENT_BASE_INFO_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_ENT_BASE_INFO_TYPE);

        try {
            rsp = queryEntBseInfoService.queryEntBaseInfo(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }


}
