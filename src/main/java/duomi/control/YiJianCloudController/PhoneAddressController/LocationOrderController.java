package duomi.control.YiJianCloudController.PhoneAddressController;

import duomi.com.constants.InterFaceConstants;
import duomi.dispatch.request.yijianCloud.LocationOrderRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.yijianCloudServices.LocationOrderService;
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
 * 易简云-手机号码强授权定位查询-定位开通接口
 */
@Controller
@RequestMapping("/yjcloud")
public class LocationOrderController {

    private final static Logger logger = LoggerFactory.getLogger(LocationOrderController.class);

    @Autowired
    private LocationOrderService locationOrderService;

    @RequestMapping(value = "/locationOrder" , method = RequestMethod.POST)
    @ResponseBody
    public ComResponse<JSONObject> loanOverDue(LocationOrderRequest request){
        ComResponse<JSONObject> rsp = null;
        request.setInterNo(InterFaceConstants.YJ_CLOUD_LOCATIONORDER_NO);
        request.setInterName(InterFaceConstants.YJ_CLOUD_LOCATIONORDER_NAME);
        request.setInterType(InterFaceConstants.YJ_CLOUD_LOCATIONORDER_TYPE);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        request.setAppNo("YJLocationOrder1234567891"+timestamp);
        request.setAuthID("YJTest123456789");
        request.setMobile(request.getTelNO());

        try {
            request.setMobile(request.getTelNO());
            rsp = locationOrderService.locationOrder(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("返回易简云-手机号码强授权定位开通接口（YJ0000011）:" + request.getTelNO() + "   返回数据:"+rsp);

        return rsp;
    }
}
