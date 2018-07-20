package duomi.services;

import duomi.dispatch.request.PersonJudicialListRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/5/9.
 */
public interface PersonJudicialListService {
    //智宝-个人司法信息
    ComResponse<JSONObject> personJudicialList(PersonJudicialListRequest request) throws Exception;

}
