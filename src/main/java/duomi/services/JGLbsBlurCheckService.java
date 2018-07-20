package duomi.services;

import duomi.dispatch.request.JGLbsBlurCheckRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/2/23.
 */
public interface JGLbsBlurCheckService {
    //极光-LBS验真接口(模糊匹配)
    public ComResponse<JSONObject> lbsBlurCheck(JGLbsBlurCheckRequest request) throws Exception;
}
