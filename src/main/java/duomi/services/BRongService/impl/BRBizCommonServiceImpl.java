package duomi.services.BRongService.impl;

import duomi.com.constants.MongoDbCollectionConstants;
import duomi.com.constants.PubConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.services.BRongBizHttpService;
import duomi.com.utils.JSONUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.baiRong.BRCommonRequest;
import duomi.dispatch.response.ComResponse;
import duomi.mongodb.dao.Impl.MongodbBaseDao2Impl;
import duomi.services.BRongService.BRBizCommonService;
import duomi.services.OutsideServiceRegistService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BRBizCommonServiceImpl implements BRBizCommonService {

    @Autowired
    private OutsideServiceRegistService regitSrv;
    @Autowired
    private BRongBizHttpService bRongBizHttpService;
    @Autowired
    private MongodbBaseDao2Impl mongodbBaseDao2;

    @Override
    public ComResponse<JSONObject> getBizDetail(BRCommonRequest request) {
        ComResponse<JSONObject> output;
        Map<String, Object> retMap = null;
        try {
            retMap = regitSrv.isRequested(request);
        } catch (HttpBizException e) {
            e.printStackTrace();
        }
        boolean isQuested = (Boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
        if (isQuested) {
            CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
            output = this.getInfoFromLocal(request, cspStatPo);
        } else {
            // 发送外部服务
            JSONObject data = bRongBizHttpService.getBizDetail(request);
            dataPersist(request,data);
            // 返回消息转换
            ComResponse<JSONObject> rsp = new ResponseSimpleHelper<JSONObject>()
                    .BizConvertRsp(request, data);
            return rsp;
        }

        return output;
    }

    private ComResponse<JSONObject> getInfoFromLocal(BRCommonRequest request, CspInterfaceStatPoWithBLOBs cspStatPo) {
        String retMsg = cspStatPo.getRetMessage();
        JSONObject output = JSONUtils.toJSONObject(retMsg);
        ComResponse<JSONObject> rsp = new ResponseSimpleHelper<JSONObject>().BizConvertRsp(request, output);
        return rsp;
    }

    @Override
    public ComResponse<JSONObject> getCompanyMap(BRCommonRequest request) {
        ComResponse<JSONObject> output;
        Map<String,Object> retMap = null;
        try {
            retMap = regitSrv.isRequested(request);
        } catch (HttpBizException e) {
            e.printStackTrace();
        }
        boolean isQuested = (boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
        if(isQuested) {
            CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
            output = this.getInfoFromLocal(request, cspStatPo);
        } else {
            // 发送外部服务
            JSONObject data = bRongBizHttpService.getBizCompanyMap(request);
            //将外部数据保存到mongdb
            JSONObject mgJson = new JSONObject();
            mgJson.put("appno",request.getAppNo());
            mgJson.put("name",request.getName());
            mgJson.put("mobile",request.getMobile());
            mgJson.put("idCard",request.getIdCard());
            mgJson.put("data",data);
            mgJson.put("interSerno",request.getInterSerno());
            mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(data),"analysis",new JSONObject()));
            mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_BR_EXECUTION);
            // 返回消息转换
            output = new ResponseSimpleHelper<JSONObject>()
                    .BizConvertRsp(request, data);
            return output;
        }
        return output;
    }

    @Override
    public ComResponse<JSONObject> getBlackList(BRCommonRequest request) {
        ComResponse<JSONObject> output;
        Map<String, Object> retMap = null;
        try {
            retMap = regitSrv.isRequested(request);
        } catch (HttpBizException e) {
            e.printStackTrace();
        }
        boolean isQuested = (Boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
        if (isQuested) {
            CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
            output = this.getInfoFromLocal(request, cspStatPo);
        } else {
            // 发送外部服务
            JSONObject data = bRongBizHttpService.getBizBlackList(request);
            //将外部数据保存到mongdb
            JSONObject mgJson = new JSONObject();
            mgJson.put("appno",request.getAppNo());
            mgJson.put("name",request.getName());
            mgJson.put("mobile",request.getMobile());
            mgJson.put("idCard",request.getIdCard());
            mgJson.put("data",data);
            mgJson.put("interSerno",request.getInterSerno());
            mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(data),"analysis",new JSONObject()));
            mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_BR_EXECUTION);
            // 返回消息转换
            output = new ResponseSimpleHelper<JSONObject>()
                    .BizConvertRsp(request, data);
            return output;
        }
        return output;
    }

    @Override
    public ComResponse<JSONObject> getBizKeySearch(BRCommonRequest request) {
        ComResponse<JSONObject> output;
        Map<String,Object> retMap = null;
        try {
            retMap = regitSrv.isRequested(request);
        } catch (HttpBizException e) {
            e.printStackTrace();
        }
        boolean isQuested = (boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
        if(isQuested) {
            CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
            output = this.getInfoFromLocal(request, cspStatPo);
        } else {
            // 发送外部服务
            JSONObject data = bRongBizHttpService.getBizKeySearch(request);
            //将外部数据保存到mongdb
            JSONObject mgJson = new JSONObject();
            mgJson.put("appno",request.getAppNo());
            mgJson.put("name",request.getName());
            mgJson.put("mobile",request.getMobile());
            mgJson.put("idCard",request.getIdCard());
            mgJson.put("data",data);
            mgJson.put("interSerno",request.getInterSerno());
            mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(data),"analysis",new JSONObject()));
            mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_BR_EXECUTION);
            // 返回消息转换
            output = new ResponseSimpleHelper<JSONObject>()
                    .BizConvertRsp(request, data);
            return output;
        }
        return output;
    }

    @Override
    public ComResponse<JSONObject> getBizRelationship(BRCommonRequest request) {
        ComResponse<JSONObject> output;
        Map<String,Object> retMap = null;
        try {
            retMap = regitSrv.isRequested(request);
        } catch (HttpBizException e) {
            e.printStackTrace();
        }
        boolean isQuested = (boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
        if(isQuested) {
            CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
            output = this.getInfoFromLocal(request, cspStatPo);
        } else {
            // 发送外部服务
            JSONObject data = bRongBizHttpService.getBizRelationship(request);
            //保存数据到mongodb
            dataPersist(request,data);
            // 返回消息转换
            output = new ResponseSimpleHelper<JSONObject>()
                    .BizConvertRsp(request, data);
            return output;
        }
        return output;
    }

    private void dataPersist(BRCommonRequest request, JSONObject data){
        //将外部数据保存到mongdb
        JSONObject mgJson = new JSONObject();
        mgJson.put("appno",request.getAppNo());
        mgJson.put("name",request.getName());
        mgJson.put("mobile",request.getMobile());
        mgJson.put("idCard",request.getIdCard());
        mgJson.put("data",data);
        mgJson.put("interSerno",request.getInterSerno());
        mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(data),"analysis",new JSONObject()));
        mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_BR_EXECUTION);
    }


    @Override
    public ComResponse<JSONObject> getBizExecutor(BRCommonRequest request) {
        ComResponse<JSONObject> output;
        Map<String,Object> retMap = null;
        try {
            retMap = regitSrv.isRequested(request);
        } catch (HttpBizException e) {
            e.printStackTrace();
        }
        boolean isQuested = (boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
        if(isQuested) {
            CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
            output = this.getInfoFromLocal(request, cspStatPo);
        } else {
            // 发送外部服务
            JSONObject data = bRongBizHttpService.getBizExecutor(request);
            //保存数据到mongodb
            dataPersist(request,data);
            // 返回消息转换
            output = new ResponseSimpleHelper<JSONObject>()
                    .BizConvertRsp(request, data);
            return output;
        }
        return output;
    }

    @Override
    public ComResponse<JSONObject> getBizUncreditExecutor(BRCommonRequest request) {
        ComResponse<JSONObject> output;
        Map<String,Object> retMap = null;
        try {
            retMap = regitSrv.isRequested(request);
        } catch (HttpBizException e) {
            e.printStackTrace();
        }
        boolean isQuested = (boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
        if(isQuested) {
            CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
            output = this.getInfoFromLocal(request, cspStatPo);
        } else {

            // 发送外部服务
            JSONObject data = bRongBizHttpService.getBizUncreditExecutor(request);
            //保存数据到mongodb
            dataPersist(request,data);
            // 返回消息转换
            output = new ResponseSimpleHelper<JSONObject>()
                    .BizConvertRsp(request, data);
            return output;
        }
        return null;
    }

    @Override
    public ComResponse<JSONObject> getBizCourtAnnouncement(BRCommonRequest request) {
        ComResponse<JSONObject> output;
        Map<String,Object> retMap = null;
        try {
            retMap = regitSrv.isRequested(request);
        } catch (HttpBizException e) {
            e.printStackTrace();
        }
        boolean isQuested = (boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
        if(isQuested) {
            CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
            output = this.getInfoFromLocal(request, cspStatPo);
        } else {
            // 发送外部服务
            JSONObject data = bRongBizHttpService.getBizCourtAnnouncement(request);
            //保存数据到mongodb
            dataPersist(request,data);
            // 返回消息转换
            output = new ResponseSimpleHelper<JSONObject>()
                    .BizConvertRsp(request, data);
            return output;
        }
        return output;
    }

    @Override
    public ComResponse<JSONObject> getBizFour(BRCommonRequest request) {
        ComResponse<JSONObject> output;
        Map<String,Object> retMap = null;
        try {
            retMap = regitSrv.isRequested(request);
        } catch (HttpBizException e) {
            e.printStackTrace();
        }
        boolean isQuested = (boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
        if(isQuested) {
            CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
            output = this.getInfoFromLocal(request, cspStatPo);
        } else {
            // 发送外部服务
            JSONObject data = bRongBizHttpService.getBizFour(request);
            //保存数据到mongodb
            dataPersist(request,data);
            // 返回消息转换
            output = new ResponseSimpleHelper<JSONObject>()
                    .BizConvertRsp(request, data);
            return output;
        }
        return output;
    }
}
