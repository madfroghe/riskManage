package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.HousePlaceCheckRequest;
import duomi.dispatch.request.yijianCloud.WorkPlaceCheckRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/5/11.
 */
public interface HousePlaceCheckService {

    //易简云-手机号居住地区验证
    public ComResponse<JSONObject> housePlaceCheck(HousePlaceCheckRequest housePlaceCheckRequest) throws Exception;

}
