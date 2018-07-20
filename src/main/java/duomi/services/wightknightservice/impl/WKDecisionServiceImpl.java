package duomi.services.wightknightservice.impl;

import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.services.WhiteKnightHttpService;
import duomi.com.httpIvk.wightknight.param.WightKnightResponse;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.JSONUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.wightknight.DecisionRequest;
import duomi.dispatch.response.ComResponse;
import duomi.mongodb.bean.wightknightInterfaecModel.DecisionModel;
import duomi.mongodb.dao.WhiteKnightInterFaceDao.impl.WKDecisionDaoImpl;
import duomi.services.OutsideServiceRegistService;
import duomi.services.wightknightservice.WKDecisionService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by DEllComputer on 2018/2/27.
 */
@Service
public class WKDecisionServiceImpl implements WKDecisionService {
    @Autowired
    private OutsideServiceRegistService regitSrv;

    @Autowired
    private WhiteKnightHttpService whiteKnightHttpService;

    @Autowired
    private WKDecisionDaoImpl wkDecisionDao;

    private static JSONObject staticJson = new JSONObject();

    public ComResponse<JSONObject> decision(DecisionRequest request) throws Exception {

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
    private ComResponse<JSONObject> getInfoFromLocal(DecisionRequest request,
                                                     CspInterfaceStatPoWithBLOBs cspStatPo) {
        String retMsg = cspStatPo.getRetMessage();

        GsonUtils<JSONObject> gson = new GsonUtils<JSONObject>();
        WightKnightResponse<JSONObject> output = (WightKnightResponse<JSONObject>) gson.fromJson(retMsg, WightKnightResponse.class,
                JSONArray.class);

        ComResponse<JSONObject> rsp = new ResponseSimpleHelper<JSONObject>().ConvertRsp(request, output);
        return rsp;
    }

    /**
     * 从外部资信获取信息
     * */
    private ComResponse<JSONObject> getInfoFromOutside(DecisionRequest request)
            throws Exception {
        // 接受到调度请求后，登记外部服务查询状态表
        regitSrv.insertCspStus(request);

        // 发送外部服务
        WightKnightResponse<JSONObject> output = whiteKnightHttpService.decision(request);

        //将外部数据保存到mongdb
        DecisionModel decisionModel = new DecisionModel();
        decisionModel.setName(request.getName());
        decisionModel.setAppno(request.getAppNo());
        decisionModel.setIdCard(request.getIdCard());
        decisionModel.setMobiel(request.getMobile());
        decisionModel.setData(output);
        if(output.strategySet != null){
            decisionModel.setAnalyJSON(JSONUtils.analysisJson(output.strategySet,"analysis",staticJson));
        }
        wkDecisionDao.add(decisionModel);



        // 返回消息转换
        ComResponse<JSONObject> rsp = new ResponseSimpleHelper<JSONObject>()
                .ConvertRsp(request, output);

        return rsp;
    }
}
