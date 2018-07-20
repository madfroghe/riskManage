package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.CustomerHighSeatRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by DEllComputer on 2018/3/13.
 */
public interface CustomerHighSeatService {

    //易简云-航空行为接口
    public ComResponse<JSONObject> customerHighSeat(CustomerHighSeatRequest customerHighSeatRequest) throws Exception;
}
