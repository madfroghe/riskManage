package duomi.services.BRongService;

import duomi.dispatch.request.baiRong.BRSpecialListRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/3/26.
 */
public interface BRSpecialListService {

    //百融-特殊名单核查
    public ComResponse<JSONObject> getSpecialList(BRSpecialListRequest request) throws Exception;

}
