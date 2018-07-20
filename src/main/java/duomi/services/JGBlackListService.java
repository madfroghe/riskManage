package duomi.services;

import duomi.com.exception.HttpBizException;
import duomi.dispatch.request.JGBlackListRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/2/2.
 */
public interface JGBlackListService {
    //极光-黑名单
    public ComResponse<JSONObject> getBlackList(JGBlackListRequest request) throws Exception;
}
