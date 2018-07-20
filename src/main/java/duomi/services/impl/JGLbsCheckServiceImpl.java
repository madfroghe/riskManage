package duomi.services.impl;

import duomi.com.constants.PubConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.JiGResponse;
import duomi.com.httpIvk.services.JGDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.JSONUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.JGBlackListRequest;
import duomi.dispatch.request.LbsCheckRequest;
import duomi.dispatch.response.ComResponse;
import duomi.mongodb.bean.JGInterFaceModel.LBSCheckModel;
import duomi.mongodb.dao.Impl.JGLBSCheckDaoImpl;
import duomi.services.JGLbsCheckService;
import duomi.services.OutsideServiceRegistService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by DEllComputer on 2018/2/7.
 */
@Service
public class JGLbsCheckServiceImpl implements JGLbsCheckService{

    @Autowired
    private OutsideServiceRegistService regitSrv;

    @Autowired
    private JGDataHttpService jgDataHttpService;

    @Autowired
    private JGLBSCheckDaoImpl jglbsCheckDao; //mongdb存储

    private static JSONObject staticJson = new JSONObject();

    public ComResponse<JSONObject> lbsCheck(LbsCheckRequest request) throws Exception {
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
    private ComResponse<JSONObject> getInfoFromLocal(LbsCheckRequest request,
                                                     CspInterfaceStatPoWithBLOBs cspStatPo) {
        String retMsg = cspStatPo.getRetMessage();

        GsonUtils<JSONObject> gson = new GsonUtils<JSONObject>();
        JiGResponse<JSONObject> output = (JiGResponse<JSONObject>) gson.fromJson(retMsg, JiGResponse.class,
                JSONObject.class);

        ComResponse<JSONObject> rsp = new ResponseSimpleHelper<JSONObject>().ConvertRsp(request, output);
        return rsp;
    }

    /**
     * 从外部资信获取信息
     * */
    private ComResponse<JSONObject> getInfoFromOutside(LbsCheckRequest request)
            throws Exception {
        // 接受到调度请求后，登记外部服务查询状态表
        regitSrv.insertCspStus(request);

        // 发送外部服务
        JiGResponse<JSONObject> output = jgDataHttpService.lbsCheck(request);

        //将外部数据保存到mongdb
        LBSCheckModel lbsCheckModel = new LBSCheckModel();
        lbsCheckModel.setName(request.getName());
        lbsCheckModel.setAppno(request.getAppNo());
        lbsCheckModel.setIdCard(request.getId_number());
        lbsCheckModel.setMobiel(request.getPhone());
        lbsCheckModel.setInterSerno(request.getInterSerno());
        lbsCheckModel.setData(output);
        if(output.data != null){
            lbsCheckModel.setAnalyJSON(JSONUtils.analysisJson(output.data,"analysis",staticJson));
        }

        jglbsCheckDao.add(lbsCheckModel);

        // 返回消息转换
        ComResponse<JSONObject> rsp = new ResponseSimpleHelper<JSONObject>()
                .ConvertRsp(request, output);

        return rsp;
    }
}
