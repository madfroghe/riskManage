package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.FinanceBlackListRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/5/8.
 */
public interface FinanceBlackListService {

    //易简云-金融黑名单查询
    public ComResponse<JSONObject> financeBlackList(FinanceBlackListRequest financeBlackListRequest) throws Exception;
}
