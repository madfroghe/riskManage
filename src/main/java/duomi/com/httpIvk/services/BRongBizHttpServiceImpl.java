package duomi.com.httpIvk.services;

import com.bfd.facade.MerchantServer;
import com.bfd.util.MD5Utils;
import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.dispatch.request.baiRong.BRCommonRequest;
import duomi.services.OutsideServiceRegistService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BRongBizHttpServiceImpl implements BRongBizHttpService {

    private String dataUrl = PubConstants.BR_BIZ_V1_QUERY_DATA_URL;

    private String resultUrl = PubConstants.Br_BIZ_V1_GET_RESULT_URL;

    public static  String tokenid = login();

    @Autowired
    private OutsideServiceRegistService regitSrv;

    public static String login(){
        if(tokenid == null){
            MerchantServer ms=new MerchantServer();
            String login_result= null;
            try {
                login_result = ms.login("ymwjr","ymwjr",PubConstants.BR_LOGIN_NAME,PubConstants.BR_BIZ_API_CODE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tokenid =JSONObject.fromObject(login_result).getString("tokenid");
        }
        return tokenid;
    }

    //百融-企业详情查询
    public JSONObject getBizDetail(BRCommonRequest request) {
        request.setProductApi(BRCommonRequest.ProductApiEnum.BIZ_DETAIL.getKey());
        JSONObject out = null;
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);
        JSONObject jsonData = new JSONObject();
        jsonData.put("search_key",request.getBizFullName());
        try {
            out = helper.getBaiRongBizForData(buildRequest(request,jsonData),request,dataUrl,resultUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public JSONObject getBizCompanyMap(BRCommonRequest request) {
        request.setProductApi(BRCommonRequest.ProductApiEnum.BIZ_COMPANY_MAP.getKey());
        JSONObject out = null;
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<>();
        helper.setRegitSrv(regitSrv);
        JSONObject jsonData = new JSONObject();
        jsonData.put("key_no",request.getKeyNo());
        try {
            out = helper.getBaiRongBizForData(buildRequest(request,jsonData),request,dataUrl,resultUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    private JSONObject buildRequest(BRCommonRequest request, JSONObject jsonData) {
        JSONObject jso = new JSONObject();
        jso.put("tokenid",tokenid);  //拿到tokenid
        jso.put("apiCode",PubConstants.BR_BIZ_API_CODE);
        jsonData.put("api",request.getProductApi());
        jso.put("jsonData",jsonData);
        jso.put("checkCode",MD5Utils.genMd5(jsonData.toString() + MD5Utils.genMd5(PubConstants.BR_BIZ_API_CODE + tokenid)));
        return jso;
    }

    @Override
    public JSONObject getBizBlackList(BRCommonRequest request) {
        request.setProductApi(BRCommonRequest.ProductApiEnum.BIZ_BLACK_LIST.getKey());
        JSONObject out = null;
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);
        try {
            JSONObject jsonData = new JSONObject();
            jsonData.put("search_key",request.getBizFullName());
            out = helper.getBaiRongBizForData(buildRequest(request,jsonData),request,dataUrl,resultUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public JSONObject getBizKeySearch(BRCommonRequest request) {
        request.setProductApi(BRCommonRequest.ProductApiEnum.BIZ_KEY_SEARCH.getKey());
        JSONObject out = null;
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);
        JSONObject jsonData = new JSONObject();
        jsonData.put("search_key",request.getBizFullName());
        try {
            out = helper.getBaiRongBizForData(buildRequest(request,jsonData),request,dataUrl,resultUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;

    }

    @Override
    public JSONObject getBizFour(BRCommonRequest request) {
        request.setProductApi(BRCommonRequest.ProductApiEnum.BIZ_FOUR.getKey());
        JSONObject out = null;
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);
        JSONObject jsonData = new JSONObject();
        jsonData.put("credit_code",request.getCreditCode());
        jsonData.put("company_name",request.getBizFullName());
        jsonData.put("legal_person",request.getLegalPerson());
        jsonData.put("person_id",request.getPersonId());
        try {
            out = helper.getBaiRongBizForData(buildRequest(request,jsonData),request,dataUrl,resultUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public JSONObject getBizRelationship(BRCommonRequest request) {
        request.setProductApi(BRCommonRequest.ProductApiEnum.BIZ_RELATIONSHIP.getKey());
        JSONObject out = null;
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);
        JSONObject jsonData = new JSONObject();
        jsonData.put("search_key",request.getBizFullName());
        try {
            out = helper.getBaiRongBizForData(buildRequest(request,jsonData),request,dataUrl,resultUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;

    }

    @Override
    public JSONObject getBizExecutor(BRCommonRequest request) {
        request.setProductApi(BRCommonRequest.ProductApiEnum.BIZ_EXECUTOR.getKey());
        JSONObject out = null;
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);
        JSONObject jsonData = new JSONObject();
        jsonData.put("search_key",request.getBizFullName());
        try {
            out = helper.getBaiRongBizForData(buildRequest(request,jsonData),request,dataUrl,resultUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;

    }

    @Override
    public JSONObject getBizUncreditExecutor(BRCommonRequest request) {
        request.setProductApi(BRCommonRequest.ProductApiEnum.BIZ_UNCREDIT_EXECUTOR.getKey());
        JSONObject out = null;
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);
        JSONObject jsonData = new JSONObject();
        jsonData.put("search_key",request.getBizFullName());
        try {
            out = helper.getBaiRongBizForData(buildRequest(request,jsonData),request,dataUrl,resultUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public JSONObject getBizCourtAnnouncement(BRCommonRequest request) {
        request.setProductApi(BRCommonRequest.ProductApiEnum.BIZ_COURT_ANNOUNCEMENT.getKey());
        JSONObject out = null;
        HttpIvkHelper<JSONObject> helper = new HttpIvkHelper<JSONObject>();
        helper.setRegitSrv(regitSrv);
        JSONObject jsonData = new JSONObject();
        jsonData.put("search_key",request.getBizFullName());
        try {
            out = helper.getBaiRongBizForData(buildRequest(request,jsonData),request,dataUrl,resultUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

}
