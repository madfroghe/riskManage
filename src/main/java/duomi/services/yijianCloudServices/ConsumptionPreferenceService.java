package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.ConsumptionPreferenceRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/3/13.
 */
public interface ConsumptionPreferenceService {

    //易简云-航旅消费等级
    public ComResponse<JSONObject> consumptionPreference(ConsumptionPreferenceRequest request) throws Exception;
}
