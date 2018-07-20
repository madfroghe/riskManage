package duomi.services;

import duomi.dispatch.request.BlacklistcheckRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/5/16.
 */
public interface BlacklistcheckService {

    // 反欺诈服务黑名单
    public ComResponse<JSONObject> blacklistcheck(BlacklistcheckRequest request) throws Exception;
}
