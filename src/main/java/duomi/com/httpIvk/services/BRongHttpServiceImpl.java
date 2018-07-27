package duomi.com.httpIvk.services;

import com.bfd.facade.MerchantServer;
import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.bRong.param.BRExecutionInput;
import duomi.com.httpIvk.bRong.param.BrApplyLoanInput;
import duomi.com.httpIvk.bRong.param.BrSpecialListInput;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.dispatch.request.baiRong.BRApplyLoanRequest;
import duomi.dispatch.request.baiRong.BRExecutionRequest;
import duomi.dispatch.request.baiRong.BRSpecialListRequest;
import duomi.services.OutsideServiceRegistService;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by DEllComputer on 2018/3/26.
 */
@Component
public class BRongHttpServiceImpl implements BRongHttpService {
    @Autowired
    private OutsideServiceRegistService regitSrv;


    //百融-特殊名单
    public JSONObject getSpecialList(BRSpecialListRequest request) throws Exception {

        BrSpecialListInput brSpecialListInput = new BrSpecialListInput();//百融特殊名单请求参数
        BeanUtils.copyProperties(request,brSpecialListInput);

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        MerchantServer ms=new MerchantServer();
        //登陆百融，获取tokenID
        String login_result=ms.login(PubConstants.BR_PERSONAL_USERNAME,PubConstants.BR_PERSONAL_PWD,PubConstants.BR_LOGIN_NAME,PubConstants.BR_PERSONAL_API_CODE);

        JSONObject json=JSONObject.fromObject(login_result);

        String tokenid=json.getString("tokenid");//拿到tokenid

        JSONObject jso = new JSONObject();
        JSONObject reqData = new JSONObject();

        jso.put("apiName", "BankServer4Api");//百融打包调用地址
        jso.put("tokenid", tokenid);
        reqData.put("meal", "SpecialList_c");
        reqData.put("id",brSpecialListInput.getId());
        reqData.put("cell", brSpecialListInput.getCell());
        reqData.put("name", brSpecialListInput.getName());

        if(brSpecialListInput.getLinkman_cell() != null){
            reqData.put("linkman_cell",brSpecialListInput.getLinkman_cell());
        }
        if(brSpecialListInput.getTime_range() != null){
            reqData.put("time_range",brSpecialListInput.getTime_range());
        }
        jso.put("reqData", reqData);

        JSONObject out = helper.getDataBaiRong(jso,request,JSONObject.class);
        return out;
    }

    //百融-多次申请核查
    public JSONObject getApplyLoan(BRApplyLoanRequest request) throws Exception {
        BrApplyLoanInput brApplyLoanInput = new BrApplyLoanInput();//百融多次申请请求参数
        BeanUtils.copyProperties(request,brApplyLoanInput);

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        MerchantServer ms=new MerchantServer();
        //登陆百融，获取tokenID
        String login_result=ms.login(PubConstants.BR_PERSONAL_USERNAME,PubConstants.BR_PERSONAL_PWD,PubConstants.BR_LOGIN_NAME,PubConstants.BR_PERSONAL_API_CODE);

        JSONObject json=JSONObject.fromObject(login_result);

        String tokenid=json.getString("tokenid");//拿到tokenid

        JSONObject jso = new JSONObject();
        JSONObject reqData = new JSONObject();

        jso.put("apiName", "BankServer4Api");//百融打包调用地址
        jso.put("tokenid", tokenid);
        reqData.put("meal", "ApplyLoanStr");
        reqData.put("id",brApplyLoanInput.getId());
        reqData.put("cell", brApplyLoanInput.getCell());
        reqData.put("name", brApplyLoanInput.getName());

        jso.put("reqData", reqData);

        JSONObject out = helper.getDataBaiRong(jso,request,JSONObject.class);
        return out;
    }

    //百融-法院被执行人-个人版
    public JSONObject execution(BRExecutionRequest request) throws Exception {
        BRExecutionInput brExecutionInput = new BRExecutionInput();//百融法院被执行人-个人版请求参数
        BeanUtils.copyProperties(request,brExecutionInput);

        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);

        MerchantServer ms=new MerchantServer();
        //登陆百融，获取tokenID
        String login_result=ms.login(PubConstants.BR_PERSONAL_USERNAME,PubConstants.BR_PERSONAL_PWD,PubConstants.BR_LOGIN_NAME,PubConstants.BR_PERSONAL_API_CODE);

        JSONObject json=JSONObject.fromObject(login_result);

        String tokenid=json.getString("tokenid");//拿到tokenid

        JSONObject jso = new JSONObject();
        JSONObject reqData = new JSONObject();

        jso.put("apiName", "BankServer4Api");//百融打包调用地址
        jso.put("tokenid", tokenid);
        reqData.put("meal", "Execution");
        reqData.put("id",brExecutionInput.getId());
        reqData.put("cell", brExecutionInput.getCell());
        reqData.put("name", brExecutionInput.getName());

        jso.put("reqData", reqData);

        JSONObject out = helper.getDataBaiRong(jso,request,JSONObject.class);
        return out;
    }
}
