package duomi.services.BRongService;

import duomi.dispatch.request.baiRong.BRExecutionRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/5/9.
 */
public interface BRExecutionService {

    //百融-法院被执行人-个人版
    public ComResponse<JSONObject> execution(BRExecutionRequest request) throws Exception;

}
