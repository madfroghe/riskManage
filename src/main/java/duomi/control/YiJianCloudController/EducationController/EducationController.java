package duomi.control.YiJianCloudController.EducationController;

import duomi.com.constants.InterFaceConstants;
import duomi.dispatch.request.yijianCloud.EducationRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.yijianCloudServices.EducationService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DEllComputer on 2018/3/15.
 * 易简云-学历系列接口
 */
@Controller
@RequestMapping("/yjcloud")
public class EducationController {

    private static Logger log = Logger.getLogger(EducationController.class);

    @Autowired
    private EducationService educationService;
    /**
     * 易简云-个人学历查询
     * */
    @RequestMapping(value = "/education" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> personalFlight(EducationRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_EDUCATION_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_EDUCATION_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_EDUCATION_TYPE);

        try {
            rsp = educationService.education(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsp;
    }




}
