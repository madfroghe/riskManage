package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.GoAboardRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/3/13.
 */
public interface GoAboardService {

    //易简云-是否登机出口
    public ComResponse<JSONObject> goAboard(GoAboardRequest request) throws Exception;
}
