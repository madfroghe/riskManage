package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.QueryEntByPersonRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/5/9.
 */
public interface QueryEntByPersonService {

    //易简云-个人对外投资信息查询
    public ComResponse<JSONObject> queryEntByPerson(QueryEntByPersonRequest request) throws Exception;

}
