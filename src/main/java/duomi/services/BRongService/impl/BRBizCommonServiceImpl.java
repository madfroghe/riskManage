package duomi.services.BRongService.impl;

import com.bfd.util.MD5Utils;
import duomi.com.constants.MongoDbCollectionConstants;
import duomi.com.constants.PubConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.helper.HttpUtil;
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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static duomi.com.httpIvk.services.BRongBizHttpServiceImpl.tokenid;

@Service
public class BRBizCommonServiceImpl implements BRBizCommonService {
    private Logger logger = Logger.getLogger(BRBizCommonServiceImpl.class);
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
        }
        return output;
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

    @Override
    public void queryResult() {
        BRCommonRequest request = new BRCommonRequest();

        List<CspInterfaceStatPoWithBLOBs> noResultDatas = regitSrv.queryNoResultData();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);

        for (CspInterfaceStatPoWithBLOBs data : noResultDatas) {
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    logger.info("开始查询" + data.getName() +"的"+ data.getInterName() + "结果信息");
                    //封装查询参数
                    String productApi = BRCommonRequest.ProductApiEnum.getKeyByInnerNo(data.getInterNo());
                    com.alibaba.fastjson.JSONObject retJson = com.alibaba.fastjson.JSONObject.parseObject(data.getRetMessageCt());
                    String taskId = retJson.getJSONObject("Task").getString("Id");
                    JSONObject jso = new JSONObject();
                    jso.put("tokenid",tokenid);  //拿到tokenid
                    jso.put("apiCode",PubConstants.BR_BIZ_API_CODE);
                    JSONObject jsonData = new JSONObject();
                    jsonData.put("api",productApi);
                    jsonData.put("taskid",taskId);
                    jso.put("jsonData",jsonData);
                    jso.put("checkCode",MD5Utils.genMd5(jsonData.toString() + MD5Utils.genMd5(jso.getString("apiCode") + jso.getString("tokenid"))));
                    String result = HttpUtil.sendFormPost( PubConstants.Br_BIZ_V1_GET_RESULT_URL,jso.toString(),60000);
                    JSONObject rtJson = JSONUtils.toJSONObject(result);
                    //如果返回结果为查询成功
                    if(rtJson.getString("Code").equals("20000")){
                        //获取到成功或失败结果时记录结果,其他为处理中
                        if (rtJson.getJSONObject("Task").getString("Status").equals("FINISHED") ||
                                rtJson.getJSONObject("Task").getString("Status").equals("FAILED") ) {
                            request.setInterId(data.getId());
                            regitSrv.updateCspStusAfterOfSync(request, rtJson.toString());
                        }
                        //结果为查无结果
                    }else if(rtJson.getString("Code").equals("80011")){
                        request.setInterId(data.getId());
                        regitSrv.updateCspStusAfterOfSync(request, rtJson.toString());
                    }else{ //失败的情况
                        request.setInterId(data.getId());
                        regitSrv.updateCspStus4ErrorOfSync(request, rtJson.toString());
                    }

                    logger.info("查询" + data.getName() + "的" + data.getInterName() + "结果信息结束,返回报文{}" + result);
                }
            });
        }
    }
}
