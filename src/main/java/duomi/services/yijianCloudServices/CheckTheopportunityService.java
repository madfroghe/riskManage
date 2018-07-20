package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.CheckTheopportunityRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/3/15.
 */
public interface CheckTheopportunityService {
    //易简云-是否乘机接口
    public ComResponse<JSONObject> isCheckTheopportunity(CheckTheopportunityRequest request) throws Exception;
}
