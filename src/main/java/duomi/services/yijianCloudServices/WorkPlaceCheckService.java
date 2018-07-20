package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.WorkPlaceCheckRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/5/10.
 */
public interface WorkPlaceCheckService {

    //易简云-手机号工作地区验证
    public ComResponse<JSONObject> workPlaceCheck(WorkPlaceCheckRequest workPlaceCheckRequest) throws Exception;

}
