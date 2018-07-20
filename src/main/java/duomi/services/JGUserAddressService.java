package duomi.services;

import duomi.dispatch.request.JGUsersAddressRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/2/24.
 */
public interface JGUserAddressService {
    //极光-用户常用地址接口
    public ComResponse<JSONObject> userAddress(JGUsersAddressRequest jgUsersAddressRequest) throws Exception;
}
