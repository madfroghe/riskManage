package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.EducationRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/3/15.
 */
public interface EducationService {

    //易简云-个人学历查询
    public ComResponse<JSONObject> education(EducationRequest educationRequest) throws Exception;

}
