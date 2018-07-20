package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.CustomerHighSeatRequest;
import duomi.dispatch.request.yijianCloud.PersonalFlightRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/3/12.
 */
public interface PersonalFlightService {

    //易简云-个人乘机信息查询
    public ComResponse<JSONObject> personalFlight(PersonalFlightRequest personalFlightRequest) throws Exception;

}
