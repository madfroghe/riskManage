package duomi.com.httpIvk.services;

import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.com.httpIvk.jiguang.param.*;
import duomi.com.httpIvk.param.JiGResponse;
import duomi.com.httpIvk.param.bankCard.BackCardThreeELementResult;
import duomi.com.httpIvk.services.JGDataHttpService;
import duomi.dbMap.bean.EigenfactorPo;
import duomi.dispatch.request.*;
import duomi.services.OutsideServiceRegistService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by DEllComputer on 2018/1/31.
 */
@Component
public class JGDataHttpServiceImpl implements JGDataHttpService {

    private final static Logger logger = LoggerFactory.getLogger(JGDataHttpServiceImpl.class);

    //极光:反欺诈-特征因子
    public final static String url_Anti_Fraud = "/v3/anti-fraud/users/eigenfactor";
    //极光:反欺诈-黑名单
    public final static String url_blacklist = "/v3/anti-fraud/users/blacklist/check";
    //极光:LBS验真接口(分级验真)
    public final static String url_check_lbs = "/v3/anti-fraud/users/lbs/check";
    //极光:LBS验真接口(模糊匹配)
    public final static String url_blur_check_lbs = "/v3/anti-fraud/users/lbs/fuzzy_check";
    //极光：用户常用地址
    public final static String url_user_address = "/v2/finance/users/address";
    //极光：用户标签接口地址
    public final static String url_user_tag = "/v2/finance/users";

    @Autowired
    private OutsideServiceRegistService regitSrv;

    /**
     * 极光-反欺诈
     * */
    public JiGResponse<EigenfactorPo> getEigenfactor(EigenfactorRequest request) throws Exception {

        String url = PubConstants.JIGUANGDATA_URL + this.url_Anti_Fraud;

        AntiFraudInput antiFraudInput = new AntiFraudInput(); //极光反欺诈请求参数
        BeanUtils.copyProperties(request,antiFraudInput);

        HttpIvkHelper<EigenfactorPo> helper = new HttpIvkHelper<EigenfactorPo>();
        helper.setRegitSrv(regitSrv);

        JiGResponse<EigenfactorPo> out = helper.getDataJiGUang(url , antiFraudInput , request , EigenfactorPo.class,"GET");

        return out;

    }

    /**
     * 极光-黑名单
     * */
    public JiGResponse<JSONObject> getBlackList(JGBlackListRequest request) throws Exception {
        String url = PubConstants.JIGUANGDATA_URL + this.url_blacklist;

        BlackListInput blackListInput = new BlackListInput(); //极光黑名单请求参数
        BeanUtils.copyProperties(request,blackListInput);
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);
        JiGResponse<JSONObject> out = helper.getDataJiGUang(url , blackListInput , request , JSONObject.class,"GET");
        return out;
    }

    /**
     * 极光-LBS验真接口(分级验真)
     * **/
    public JiGResponse<JSONObject> lbsCheck(LbsCheckRequest request) throws Exception {
        String url = PubConstants.JIGUANGDATA_URL + this.url_check_lbs;
        LBSCheckInput lbsCheckInput = new LBSCheckInput(); //极光LBS验真参数
        BeanUtils.copyProperties(request,lbsCheckInput);
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);
        JiGResponse<JSONObject> out = helper.getDataJiGUang(url , lbsCheckInput , request , JSONObject.class,"POST");
        return out;
    }

    /**
     * 极光-LBS验真接口(模糊匹配)
     * **/
    public JiGResponse<JSONObject> lbsBlurCheck(JGLbsBlurCheckRequest request) throws Exception {
        String url = PubConstants.JIGUANGDATA_URL + this.url_blur_check_lbs;
        LBSBlurCheckInput lbsBlurCheckInput = new LBSBlurCheckInput();
        BeanUtils.copyProperties(request,lbsBlurCheckInput);
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);
        JiGResponse<JSONObject> out = helper.getDataJiGUang(url , lbsBlurCheckInput , request , JSONObject.class,"POST");

        return out;
    }

    /**
     * 极光-用户常用地址
     * */
    public JiGResponse<JSONObject> userAddress(JGUsersAddressRequest request) throws Exception {
        String url = PubConstants.JIGUANGDATA_URL + this.url_user_address;

        UserAddressInput userAddressInput = new UserAddressInput(); //极光用户常用地址请求参数
        BeanUtils.copyProperties(request,userAddressInput);
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);
        JiGResponse<JSONObject> out = helper.getDataJiGUang(url , userAddressInput , request , JSONObject.class,"GET");
        return out;
    }

    /**
     * 极光-用户标签
     * */
    public JiGResponse<JSONObject> userTag(JGUsersTagRequest request) throws Exception {
        String url = PubConstants.JIGUANGDATA_URL + this.url_user_tag;

        UserTagInput userTagInput = new UserTagInput(); //极光用户标签请求参数
        BeanUtils.copyProperties(request,userTagInput);
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);
        JiGResponse<JSONObject> out = helper.getDataJiGUang(url , userTagInput , request , JSONObject.class,"GET");
        return out;
    }


}
