package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.QueryEntBaseInfoRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/5/9.
 */
public interface QueryEntBseInfoService {
    //易简云-企业基本信息查询
    public ComResponse<JSONObject> queryEntBaseInfo(QueryEntBaseInfoRequest request) throws Exception;
}
