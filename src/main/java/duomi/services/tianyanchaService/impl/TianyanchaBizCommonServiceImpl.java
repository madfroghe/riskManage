package duomi.services.tianyanchaService.impl;

import duomi.Enums.tianyancha.TianyanchaBizEnum;
import duomi.Enums.tianyancha.TianyanchaRespCodeEnum;
import duomi.com.constants.ComRspConstants;
import duomi.com.constants.PubConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.helper.tianyanchaHelper.TianyanchaHttpHelper;
import duomi.com.utils.JSONUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.ComRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.OutsideServiceRegistService;
import duomi.services.tianyanchaService.TianyanchaBizCommonService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class TianyanchaBizCommonServiceImpl implements TianyanchaBizCommonService {
    private final static Logger logger = Logger.getLogger(TianyanchaBizCommonServiceImpl.class);
    @Value("#{configProperties['tyc_page_num']}")
    private int pageNum;
    //异常控制集合
    private final static Set<String> syncCtrlSet = new HashSet<>();
    @Autowired
    private OutsideServiceRegistService regitSrv;
    @Override
    public ComResponse<JSONObject> entranceOfqueryBizInfo(TianyanchaBizEnum bizEnum, ComRequest request) throws HttpBizException {
        String appNo = request.getAppNo();
        if(StringUtils.isEmpty(appNo)){
            throw new HttpBizException("工单编号appNo不能为空!");
        }
        ComResponse<JSONObject> response;
        String syncCtrlKey = null;
        try{
            /*
             * 通过工单编号-接口编号作为唯一标志,用于判断同一时间是否有相同的工单编号访问相同的接口
             * 防止并发情况多次访问接口
             */
            syncCtrlKey = appNo + "-" + bizEnum.getCode();
            synchronized (this){
                while(syncCtrlSet.contains(syncCtrlKey)){
                    Thread.sleep(100);
                }
                //没有异步操作或异步操作已完成，当前操作继续执行，并在异步控制集合中记录当前操作
                syncCtrlSet.add(syncCtrlKey);
            }

            //获取要访问接口是否进行分页
            boolean isPage = bizEnum.isPage();
            if(isPage){
                //调用分页逻辑
                response = queryBizInfoOfPage(bizEnum,request);
            } else{
                //调用通用逻辑
                response = queryBizInfoOfCom(bizEnum,request);
            }
        }catch (HttpBizException e){
            logger.error(e.getMessage(),e);
            throw new HttpBizException(e.getMessage());
        }catch (Exception e){
            logger.error("查询" + bizEnum.getName() + "信息发生异常",e);
            throw new HttpBizException("查询" + bizEnum.getName() + "信息发生异常" + e);
        }finally {
            syncCtrlSet.remove(syncCtrlKey);
        }

        return response;
    }

    @Override
    public ComResponse<JSONObject> queryBizInfoOfPage(TianyanchaBizEnum bizEnum, ComRequest request) throws HttpBizException {
        ComResponse<JSONObject> AllRespJson = null;
        //因为要分页，InterSerno在表中是用于区分一个接口调用多次的情况，此处以该字段标记当前页
        request.setInterSerno("1");
        //查询出第一页的数据，获取到数据总数
        ComResponse<JSONObject> firstPageRespJson = queryBizInfoOfCom(bizEnum,request);
        if(ComRspConstants.TRADSTAT_FAIL.equals(firstPageRespJson.getTradStat())){
            throw new HttpBizException("获取" + bizEnum.getName() + "第一页的数据失败,"
                    + "结果码值:" + firstPageRespJson.getCode() + ",失败原因:" + firstPageRespJson.getCodeDesc());
        }

        //获取总数据条数
        JSONObject result = firstPageRespJson.getResData();
        int totalNum = result.getInt("total");

        //根据总条数计算总共有多少页
        int pageSize = totalNum % pageNum == 0 ? totalNum/pageNum : (totalNum/pageNum) + 1;
        AllRespJson = firstPageRespJson;
        JSONArray itemJsonArr = result.getJSONArray("items");

        //因为第一页数据已经获取,所以遍历获取第一页之后的数据
        for (int i = 2; i <= pageSize; i++) {
            request.setInterSerno("1");
            //其中某页获取失败,继续获取之后页数的数据，获取成功则将结果集汇总
            try{
                ComResponse<JSONObject> response = queryBizInfoOfCom(bizEnum,request);
                if(ComRspConstants.TRADSTAT_FAIL.equals(response.getTradStat())){
                    throw new HttpBizException("失败码值:" + response.getCode() + ",失败原因:" + response.getCodeDesc());
                }

                //获取成功，将结果汇总
                JSONArray curItemJsonArr = response.getResData().getJSONArray("items");
                itemJsonArr.addAll(curItemJsonArr);
            } catch (Exception e){
                logger.error("获取" + bizEnum.getName() + "第" + i + "页数据异常",e);
            }
        }
        //将结果汇总后,把值填入汇总对象AllRespJson中
        AllRespJson.getResData().put("items",itemJsonArr);
        return AllRespJson;
    }

    @Override
    public ComResponse<JSONObject> queryBizInfoOfCom(TianyanchaBizEnum bizEnum, ComRequest request) throws HttpBizException {
        logger.info("接收到" + bizEnum.getName() + "请求,入参{}" + JSONUtils.toJSONString(request));
        //默认返回值为失败
        ComResponse<JSONObject> output;
        Map<String,String> reqParams = null;
        Map<String, Object> retMap = null;
        try {
            //将枚举值封装入通用参数对象
            request.setInterNo(bizEnum.getCode());
            request.setInterName(bizEnum.getName());
            request.setInterType(bizEnum.getType());

            //登记外部服务查询状态表状态为“接收到请求-01”
            regitSrv.insertCspStus(request);

            //查询该接口是否已请求
            retMap = regitSrv.isRequested(request);
            boolean isQuested = (Boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);

            //已请求
            if(isQuested){
                //获取到历史接口请求成功数据,不再请求“天眼查接口”
                CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
                String retMsg = cspStatPo.getRetMessage();
                output = ResponseSimpleHelper.tycConvertRsp(request,retMsg);
            }else{ //未请求
                reqParams = bizEnum.getParams();
                if(reqParams == null){
                    throw new HttpBizException("请求参数不能为空!");
                }
                //发送http请求并处理数据
                String retMsg = reqAndDealData(request,bizEnum.getIntfName(),reqParams);
                //解析返回报文
                output = ResponseSimpleHelper.tycConvertRsp(request,retMsg);
            }
        }catch (Exception e){
            logger.error(bizEnum.getName() + "查询异常:",e);
            throw new HttpBizException(bizEnum.getName() + "查询异常:" + e.toString());
        }
        return output;
    }

    /**
     * 发送http请求并处理数据
     * @param request  通用请求参数对象
     * @param intfName 请求接口名称
     * @param params    请求参数
     * @return 返回报文
     */
    private String reqAndDealData(ComRequest request,String intfName,Map<String,String> params)throws Exception{
        //登记外部服务查询状态表状态为“发送请求-02”
        regitSrv.updateCspStusBefore(request);

        //发送http请求
        logger.info("发送请求至" + intfName + "接口,入参{}" + JSONObject.fromObject(params));
        String retMsg = TianyanchaHttpHelper.httpGetReq(intfName,params);
        logger.info("接收到接口" + intfName + "返回结果,出参{}" + retMsg);

        JSONObject resultJson = JSONObject.fromObject(retMsg);

        String code = resultJson.getString("error_code");

        //如果返回结果不是成功，
        if(!TianyanchaRespCodeEnum.SUCC.getCode().equals(code)){
            //更新外部服务查询状态表为“异常-99”
            regitSrv.updateCspStus4Error(request, retMsg);
        }else{
            // 更新登记表状态为“收到服务-03”
            regitSrv.updateCspStusAfter(request, retMsg);
        }
        return retMsg;
    }

    @Override
    public String getBizId(ComRequest request) throws HttpBizException {
        Map<String,String> params = new HashMap<>();
        String bizFullName = request.getBizFullName();
        if(StringUtils.isEmpty(bizFullName)){
            throw new HttpBizException("企业全称不能为空!");
        }
        params.put("name",bizFullName);
        try{
            //获取对应枚举，并封装参数
            TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_BASE_INFO;
            bizEnum.setParams(params);

            ComResponse<JSONObject> resp = queryBizInfoOfCom(bizEnum,request);

            //处理状态为“成功”,则获取解析json结果获取企业id
            if(ComRspConstants.TRADSTAT_SUCCESS.equals(resp.getTradStat())){
                JSONObject result = resp.getResData();
                String bizId = result.getString("id");
                if(StringUtils.isEmpty(bizId)){
                    throw new HttpBizException("获取的企业id为空，数据异常");
                }
                return bizId;
            }else{ //处理状态为失败,抛出异常
                throw new HttpBizException("获取企业id失败，失败结果码:" + resp.getCode() + "失败原因:" +  resp.getCodeDesc());
            }
        }catch (HttpBizException e){
            logger.error(e.getMessage());
            throw new HttpBizException(e.getMsg());
        }catch (Exception e){
            logger.error("查询企业id时发生异常",e);
            throw new HttpBizException("查询企业id时发生异常" + e.toString());
        }
    }
}
