package duomi.com.httpIvk.services;

import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.com.httpIvk.wightknight.param.DecisionInput;
import duomi.com.httpIvk.wightknight.param.LoanReportInput;
import duomi.com.httpIvk.wightknight.param.WightKnightResponse;
import duomi.dbMap.bean.EigenfactorPo;
import duomi.dispatch.request.wightknight.DecisionRequest;
import duomi.dispatch.request.wightknight.LoanReportRequest;
import duomi.services.OutsideServiceRegistService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by DEllComputer on 2018/2/27.
 */
@Component
public class WhiteKnightHttpServiceImpl implements WhiteKnightHttpService {

    private final static Logger logger = LoggerFactory.getLogger(WhiteKnightHttpServiceImpl.class);

    public final static String url_whiteknight = "https://api.baiqishi.com/services/decision";

    public final static String url_loan_report = "https://api.baiqishi.com/fraud/report/loan";

    @Autowired
    private OutsideServiceRegistService regitSrv;


    /**
     * 白骑士-风险决策
     * */
    public WightKnightResponse<JSONObject> decision(DecisionRequest request) throws Exception {

        String url = url_whiteknight;
        request.setPartnerId(PubConstants.WKNIGHT_PARTNERID);
        request.setVerifyKey(PubConstants.WKNIGHT_VERIFYKEY);
        request.setAppId(PubConstants.WKNIGHT_APPID);
        request.setAccount(PubConstants.WKNIGHT_ACCOUNT);

        DecisionInput decisionInput = new DecisionInput(); //白骑士风险决策请求参数
        BeanUtils.copyProperties(request,decisionInput);
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        WightKnightResponse<JSONObject> out = helper.getDataWightKnight(url , decisionInput , request , JSONArray.class,"POST");

        return out;
    }

    /**
     * 白骑士-信贷报告
     * */
    public WightKnightResponse<JSONObject> loanReport(LoanReportRequest request) throws Exception {
        String url = url_loan_report;
        request.setPartnerId(PubConstants.WKNIGHT_PARTNERID);
        request.setVerifyKey(PubConstants.WKNIGHT_VERIFYKEY);
        request.setProductId("107001");

        LoanReportInput loanReportInput = new LoanReportInput();  //白骑士信贷报告请求参数
        BeanUtils.copyProperties(request,loanReportInput);
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        WightKnightResponse<JSONObject> out = helper.getDataWightKnight(url , loanReportInput , request , JSONArray.class,"POST");


        return out;
    }
}
