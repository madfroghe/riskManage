package duomi.services.impl;

import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.param.JiGResponse;
import duomi.com.httpIvk.services.JGDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.JSONUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.JGUsersAddressRequest;
import duomi.dispatch.response.ComResponse;
import duomi.mongodb.bean.JGInterFaceModel.UserAddressModel;
import duomi.mongodb.dao.Impl.JGUserAddressDaoImp;
import duomi.services.JGUserAddressService;
import duomi.services.OutsideServiceRegistService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by DEllComputer on 2018/2/24.
 */
@Service
public class JGUserAddressServiceImpl implements JGUserAddressService {

    @Autowired
    private OutsideServiceRegistService regitSrv;

    @Autowired
    private JGDataHttpService jgDataHttpService;

    @Autowired
    private JGUserAddressDaoImp jgUserAddressDao; //mongdb存储

    private static JSONObject staticJson = new JSONObject();

    public ComResponse<JSONObject> userAddress(JGUsersAddressRequest request) throws Exception {
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
    private ComResponse<JSONObject> getInfoFromLocal(JGUsersAddressRequest request,
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
    private ComResponse<JSONObject> getInfoFromOutside(JGUsersAddressRequest request)
            throws Exception {
        // 接受到调度请求后，登记外部服务查询状态表
        regitSrv.insertCspStus(request);

        // 发送外部服务
        JiGResponse<JSONObject> output = jgDataHttpService.userAddress(request);

        //将外部数据保存到mongdb
        UserAddressModel userAddressModel = new UserAddressModel();
        userAddressModel.setName(request.getName());
        userAddressModel.setAppno(request.getAppNo());
        userAddressModel.setIdCard(request.getId_number());
        userAddressModel.setMobiel(request.getPhone());
        userAddressModel.setInterSerno(request.getInterSerno());
        userAddressModel.setData(output);
        if(output.data != null){
            userAddressModel.setAnalyJSON(JSONUtils.analysisJson(output.data,"analysis",staticJson));
        }

        jgUserAddressDao.add(userAddressModel);

        // 返回消息转换
        ComResponse<JSONObject> rsp = new ResponseSimpleHelper<JSONObject>()
                .ConvertRsp(request, output);

        return rsp;
    }

}
