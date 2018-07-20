package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.LocationOrderRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/4/27.
 */
public interface LocationOrderService {

    //易简云-手机号码强授权定位查询-定位开通接口
    public ComResponse<JSONObject> locationOrder(LocationOrderRequest locationOrderRequest) throws Exception;
}
