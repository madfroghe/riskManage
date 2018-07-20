package duomi.services;

import duomi.com.exception.HttpBizException;
import duomi.dispatch.request.JGLbsBlurCheckRequest;
import duomi.dispatch.request.LbsCheckRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/2/7.
 */
public interface JGLbsCheckService {

    //极光-LBS验真接口(分级验真)
    public ComResponse<JSONObject> lbsCheck(LbsCheckRequest request) throws Exception;

}
