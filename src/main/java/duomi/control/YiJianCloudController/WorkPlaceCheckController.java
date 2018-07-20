package duomi.control.YiJianCloudController;

import duomi.com.constants.InterFaceConstants;
import duomi.dispatch.request.yijianCloud.WorkPlaceCheckRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.yijianCloudServices.WorkPlaceCheckService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DEllComputer on 2018/5/10.
 * 易简云-手机号工作地区验证
 */
@Controller
@RequestMapping("/yjcloud")
public class WorkPlaceCheckController {

    private static Logger log = Logger.getLogger(WorkPlaceCheckController.class);
    @Autowired
    private WorkPlaceCheckService workPlaceCheckService;

    @RequestMapping(value = "/workPlaceCheck" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> workPlaceCheck(WorkPlaceCheckRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_WORK_PLACE_CHECK_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_WORK_PLACE_CHECK_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_WORK_PLACE_CHECK_TYPE);

        try {
            rsp = workPlaceCheckService.workPlaceCheck(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }


}
