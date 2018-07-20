package duomi.com.httpIvk.services;

import duomi.com.httpIvk.wightknight.param.WightKnightResponse;
import duomi.dispatch.request.wightknight.DecisionRequest;
import duomi.dispatch.request.wightknight.LoanReportRequest;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/2/27.
 */
public interface WhiteKnightHttpService {

    /**
     * 白骑士-风险决策
     * */
    public WightKnightResponse<JSONObject> decision(DecisionRequest request) throws Exception;

    /**
     * 白骑士-信贷报告
     * */
    public WightKnightResponse<JSONObject> loanReport(LoanReportRequest request) throws Exception;
}
