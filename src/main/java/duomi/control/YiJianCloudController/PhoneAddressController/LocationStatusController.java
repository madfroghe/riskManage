package duomi.control.YiJianCloudController.PhoneAddressController;

import duomi.com.constants.InterFaceConstants;
import duomi.dispatch.request.yijianCloud.LocationStatusRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.yijianCloudServices.LocationStatusService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by DEllComputer on 2018/4/27.
 * 易简云-手机号码强授权定位查询-状态查询接口
 */
@Controller
@RequestMapping("/yjcloud")
public class LocationStatusController {

    private final static Logger logger = LoggerFactory.getLogger(LocationStatusController.class);

    @Autowired
    private LocationStatusService locationStatusService;

    @RequestMapping(value = "/locationStatus" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> loanOverDue(LocationStatusRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_LOCATIONSTATUS_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_LOCATIONSTATUS_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_LOCATIONSTATUS_TYPE);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        request.setAppNo("YJLocationStatus123456789"+timestamp);
        request.setAuthID("YJTest123456789");
        request.setMobile(request.getTelNO());

        try {
            request.setMobile(request.getTelNO());
            rsp = locationStatusService.locationStatus(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("返回易简云-手机号码强授权定位状态查询接口（YJ0000012）:" + request.getTelNO() + "   返回数据:"+rsp);

        return rsp;
    }

}
