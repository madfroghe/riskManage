package duomi.services.BRongService.impl;

import duomi.com.constants.MongoDbCollectionConstants;
import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.services.BRongHttpService;
import duomi.com.utils.JSONUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.baiRong.BRApplyLoanRequest;
import duomi.dispatch.request.baiRong.BRExecutionRequest;
import duomi.dispatch.response.ComResponse;
import duomi.mongodb.dao.Impl.MongodbBaseDao2Impl;
import duomi.services.BRongService.BRExecutionService;
import duomi.services.OutsideServiceRegistService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by DEllComputer on 2018/5/9.
 */
@Service
public class BRExecutionServiceImpl implements BRExecutionService {

    @Autowired
    private OutsideServiceRegistService regitSrv;
    @Autowired
    private BRongHttpService bRongHttpService;
    @Autowired
    private MongodbBaseDao2Impl mongodbBaseDao2;

    public static JSONObject staticJSON = new JSONObject();


    public ComResponse<JSONObject> execution(BRExecutionRequest request) throws Exception {
        ComResponse<JSONObject> output;
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

    private ComResponse<JSONObject> getInfoFromLocal(BRExecutionRequest request,
                                                     CspInterfaceStatPoWithBLOBs cspStatPo) {
        String retMsg = cspStatPo.getRetMessage();

        JSONObject output = JSONUtils.toJSONObject(retMsg);

        ComResponse<JSONObject> rsp = new ResponseSimpleHelper<JSONObject>().ConvertRsp(request, output);
        return rsp;
    }

    private ComResponse<JSONObject> getInfoFromOutside(BRExecutionRequest request)
            throws Exception {
        // 接受到调度请求后，登记外部服务查询状态表
        regitSrv.insertCspStus(request);

        // 发送外部服务
        JSONObject output = bRongHttpService.execution(request);

        //将外部数据保存到mongdb
        JSONObject mgJson = new JSONObject();
        mgJson.put("appno",request.getAppNo());
        mgJson.put("name",request.getName());
        mgJson.put("mobile",request.getMobile());
        mgJson.put("idCard",request.getIdCard());
        mgJson.put("data",output);
        mgJson.put("interSerno",request.getInterSerno());
        mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
        mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_BR_EXECUTION);

        // 返回消息转换
        ComResponse<JSONObject> rsp = new ResponseSimpleHelper<JSONObject>()
                .ConvertRsp(request, output);

        return rsp;
    }
}
