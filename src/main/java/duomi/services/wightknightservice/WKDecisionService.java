package duomi.services.wightknightservice;

import duomi.dispatch.request.wightknight.DecisionRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/2/27.
 */
public interface WKDecisionService {
    //白骑士-风险决策
    public ComResponse<JSONObject> decision(DecisionRequest request) throws Exception;
}
