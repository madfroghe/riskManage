package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.PersonJzcxRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/4/3.
 */
public interface PersonJzcxService {

    //易简云-个人信息精准查询
    public ComResponse<JSONObject> getPersonJzcx(PersonJzcxRequest request) throws Exception;
}
