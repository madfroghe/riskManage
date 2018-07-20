package duomi.services.impl;

import duomi.com.constants.PubConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.JiGResponse;
import duomi.com.httpIvk.param.consume.ConsCreditOfflineResult;
import duomi.com.httpIvk.param.education.EducationCheckResult;
import duomi.com.httpIvk.param.multipleLend.MultipleCreditAppResult;
import duomi.com.httpIvk.param.travel.AirTravelScoreResult;
import duomi.com.httpIvk.services.JGDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.JSONUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.EigenfactorPo;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.AirTravelScoreRequest;
import duomi.dispatch.request.EducationCheckquest;
import duomi.dispatch.request.EigenfactorRequest;
import duomi.dispatch.request.MultipleCreditAppRequest;
import duomi.dispatch.response.ComResponse;
import duomi.mongodb.bean.JGInterFaceModel.EigenFactorModel;
import duomi.mongodb.dao.Impl.EigenFactorDaoImpl;
import duomi.services.EigenfactorService;
import duomi.services.OutsideServiceRegistService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by DEllComputer on 2018/1/30.
 */
@Service
public class EigenfactorServiceImpl implements EigenfactorService{

    @Autowired
    private OutsideServiceRegistService regitSrv;

    @Autowired
    private JGDataHttpService jgDataHttpService;

    @Autowired
    private EigenFactorDaoImpl eigenFactorDao; //mongdb存储

    private static JSONObject staticJson = new JSONObject();

    public ComResponse<EigenfactorPo> getEigenfactor(EigenfactorRequest request) throws Exception {
        ComResponse<EigenfactorPo> output = null;
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


    private ComResponse<EigenfactorPo> getInfoFromLocal(EigenfactorRequest request,
                                                                   CspInterfaceStatPoWithBLOBs cspStatPo) {
        String retMsg = cspStatPo.getRetMessage();

        GsonUtils<EigenfactorPo> gson = new GsonUtils<EigenfactorPo>();
        JiGResponse<EigenfactorPo> output = (JiGResponse<EigenfactorPo>) gson.fromJson(retMsg, JiGResponse.class,
                EigenfactorPo.class);

        ComResponse<EigenfactorPo> rsp = new ResponseSimpleHelper<EigenfactorPo>().ConvertRsp(request, output);
        return rsp;
    }


    private ComResponse<EigenfactorPo> getInfoFromOutside(EigenfactorRequest request)
            throws Exception {
        // 接受到调度请求后，登记外部服务查询状态表
        regitSrv.insertCspStus(request);

        // 发送外部服务
        JiGResponse<EigenfactorPo> output = jgDataHttpService.getEigenfactor(request);

        //将外部数据保存到mongdb
        EigenFactorModel eigenFactorModel = new EigenFactorModel();
        eigenFactorModel.setName(request.getName());
        eigenFactorModel.setAppno(request.getAppNo());
        eigenFactorModel.setIdCard(request.getId_number());
        eigenFactorModel.setMobiel(request.getPhone());
        eigenFactorModel.setInterSerno(request.getInterSerno());
        eigenFactorModel.setData(output);
        if(output.data != null){
            eigenFactorModel.setAnalyJSON(JSONUtils.analysisJson(output.data,"analysis",staticJson));
        }
        eigenFactorDao.add(eigenFactorModel);



        // 返回消息转换
        ComResponse<EigenfactorPo> rsp = new ResponseSimpleHelper<EigenfactorPo>()
                .ConvertRsp(request, output);

        return rsp;
    }



}
