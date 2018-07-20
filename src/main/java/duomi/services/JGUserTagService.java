package duomi.services;

import duomi.dispatch.request.JGUsersTagRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/2/24.
 */
public interface JGUserTagService {
    //极光-用户标签接口
    public ComResponse<JSONObject> userTag(JGUsersTagRequest jgUsersTagRequest) throws Exception;
}
