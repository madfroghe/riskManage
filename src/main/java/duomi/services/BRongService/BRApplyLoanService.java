package duomi.services.BRongService;

import duomi.dispatch.request.baiRong.BRApplyLoanRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/3/28.
 */
public interface BRApplyLoanService {

    //百融-多次申请
    public ComResponse<JSONObject> getApplyLoan(BRApplyLoanRequest request) throws Exception;

}
