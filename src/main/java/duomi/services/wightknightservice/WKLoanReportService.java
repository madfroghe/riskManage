package duomi.services.wightknightservice;

import duomi.dispatch.request.wightknight.LoanReportRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/2/28.
 */
public interface WKLoanReportService {

    //白骑士-信贷报告
    public ComResponse<JSONObject> loanReport(LoanReportRequest request) throws Exception;
}
