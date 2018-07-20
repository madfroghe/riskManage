package duomi.services.yijianCloudServices.impl;

import duomi.com.constants.MongoDbCollectionConstants;
import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.services.YJCloudHttpService;
import duomi.com.httpIvk.yiJianCloud.param.YiJianCloudResponse;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.JSONUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.yijianCloud.LocationOrderRequest;
import duomi.dispatch.response.ComResponse;
import duomi.mongodb.dao.Impl.MongodbBaseDao2Impl;
import duomi.services.OutsideServiceRegistService;
import duomi.services.yijianCloudServices.LocationOrderService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by DEllComputer on 2018/4/27.
 */
@Service
public class LocationOrderServiceImpl implements LocationOrderService {

    @Autowired
    private OutsideServiceRegistService regitSrv;
    @Autowired
    private YJCloudHttpService yjCloudHttpService;

    @Autowired
    private MongodbBaseDao2Impl mongodbBaseDao2;

    public static JSONObject staticJSON = new JSONObject();



    public ComResponse<JSONObject> locationOrder(LocationOrderRequest request) throws Exception {
        ComResponse<JSONObject> output = null;
        Map<String, Object> retMap = regitSrv.isRequested(request);
        boolean isQuested = (Boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);

        if (isQuested) {
            CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
            output = this.getInfoFromLocal(request, cspStatPo);
        } else {
            output = this.getInfoFromOutside(request);
        }

        return output;
    }

    /**
     * 从本地数据库获取信息
     * */
    private ComResponse<JSONObject> getInfoFromLocal(LocationOrderRequest request,
                                                     CspInterfaceStatPoWithBLOBs cspStatPo) {
        String retMsg = cspStatPo.getRetMessage();

        GsonUtils<JSONObject> gson = new GsonUtils<JSONObject>();
        YiJianCloudResponse<JSONObject> output = (YiJianCloudResponse<JSONObject>) gson.fromJson(retMsg, YiJianCloudResponse.class,
                JSONObject.class);

        ComResponse<JSONObject> rsp = new ResponseSimpleHelper<JSONObject>().ConvertRsp(request, output);
        return rsp;
    }

    /**
     * 从外部资信获取信息
     * */
    private ComResponse<JSONObject> getInfoFromOutside(LocationOrderRequest request)
            throws Exception {
        // 接受到调度请求后，登记外部服务查询状态表
        regitSrv.insertCspStus(request);

        // 发送外部服务
        YiJianCloudResponse<JSONObject> output = yjCloudHttpService.locationOrder(request);

        //将外部数据保存到mongdb
        JSONObject mgJson = new JSONObject();
        mgJson.put("appno",request.getAppNo());
        mgJson.put("mobile",request.getTelNO());
        mgJson.put("data",output);
        if(output.getData() != null){
            mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
        }

        mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_YJY_LOCATIONORDER);

        // 返回消息转换
        ComResponse<JSONObject> rsp = new ResponseSimpleHelper<JSONObject>()
                .ConvertRsp(request, output);

        return rsp;
    }
}
