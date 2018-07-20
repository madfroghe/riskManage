package duomi.control.YiJianCloudController;

import duomi.com.constants.InterFaceConstants;
import duomi.dispatch.request.yijianCloud.HousePlaceCheckRequest;
import duomi.dispatch.request.yijianCloud.WorkPlaceCheckRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.yijianCloudServices.HousePlaceCheckService;
import duomi.services.yijianCloudServices.WorkPlaceCheckService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DEllComputer on 2018/5/11.
 * 易简云-手机号居住地区验证
 */
@Controller
@RequestMapping("/yjcloud")
public class HousePlaceCheckController {

    private static Logger log = Logger.getLogger(HousePlaceCheckController.class);
    @Autowired
    private HousePlaceCheckService housePlaceCheckService;

    @RequestMapping(value = "/housePlaceCheck" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> housePlaceCheck(HousePlaceCheckRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_HOUSE_PLACE_CHECK_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_HOUSE_PLACE_CHECK_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_HOUSE_PLACE_CHECK_TYPE);

        try {
            rsp = housePlaceCheckService.housePlaceCheck(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }

}
