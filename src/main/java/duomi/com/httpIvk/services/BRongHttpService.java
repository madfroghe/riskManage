package duomi.com.httpIvk.services;

import duomi.dispatch.request.baiRong.BRApplyLoanRequest;
import duomi.dispatch.request.baiRong.BRExecutionRequest;
import duomi.dispatch.request.baiRong.BRSpecialListRequest;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/3/26.
 */
public interface BRongHttpService {

    //百融-特殊名单
    public JSONObject getSpecialList(BRSpecialListRequest request) throws Exception;

    //百融-多次申请
    public JSONObject getApplyLoan(BRApplyLoanRequest request) throws Exception;

    //百融-法院被执行人-个人版
    public JSONObject execution(BRExecutionRequest request) throws Exception;

}
