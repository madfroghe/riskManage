package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.CustomerValueLevelRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/3/13.
 */
public interface CustomerValueLevelService {

    //易简云-旅客价值评级接口
    public ComResponse<JSONObject> customerValueLevel(CustomerValueLevelRequest request) throws Exception;
}
