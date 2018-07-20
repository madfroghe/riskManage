package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.LoanOverDueRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/4/10.
 */
public interface LoanOverDueService {

    //易简云-信贷逾期详情集合查询
    public ComResponse<JSONObject> loanOverDue(LoanOverDueRequest loanOverDueRequest) throws Exception;

}
