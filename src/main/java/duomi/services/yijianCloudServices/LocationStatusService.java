package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.LocationStatusRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/4/27.
 */
public interface LocationStatusService {

    //易简云-手机号码强授权定位查询-状态查询接口
    public ComResponse<JSONObject> locationStatus(LocationStatusRequest locationStatusRequest) throws Exception;
}
