package duomi.services.impl;

import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.phone.PhoneCheckResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.JSONUtils;
import duomi.dbMap.bean.CheckMobileForApp;
import duomi.dbMap.mapper.CheckMobileForAppMapper;
import duomi.dispatch.request.Mobile3ERequest;
import duomi.services.MobileCheckForAppService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DEllComputer on 2018/4/9.
 */
@Service
public class MobileCheckForAppServiceImpl implements MobileCheckForAppService {

    @Autowired
    CheckMobileForAppMapper checkMobileForAppMapper;

    @Autowired
    private ZbaDataHttpService httpservice;


    public String checkMobileBy3E(Mobile3ERequest request) throws Exception {

        String result = "N";

        Map<String , String> mapParam = new HashMap<String, String>();
        mapParam.put("name",request.getName());
        mapParam.put("idCard",request.getIdCard());
        mapParam.put("mobile",request.getMobile());

        List<CheckMobileForApp> checkMobileForAppList = checkMobileForAppMapper.querylistByMap(mapParam);
        if(checkMobileForAppList.size() == 0){
            //本地没有查询到,去三方查询
            BaseResponse<PhoneCheckResult> output = httpservice.checkPhone(request);

            //保存到mysql数据库
            CheckMobileForApp checkMobileForApp = new CheckMobileForApp();
            checkMobileForApp.setMobile(request.getMobile());
            checkMobileForApp.setIdCard(request.getIdCard());
            checkMobileForApp.setName(request.getName());
            checkMobileForApp.setResMessage(JSONUtils.toJSONString(output.getData()));

            checkMobileForAppMapper.insertSelective(checkMobileForApp);
            if("SAME".equals(output.getData().getStatus())){
                result = "Y";
            }else {
                result = "N";
            }
        }else{
            //本地查询到了
            String retStr = checkMobileForAppList.get(0).getResMessage();
            JSONObject jsonObject = JSONUtils.toJSONObject(retStr);
            String status = jsonObject.getString("status");
            if("SAME".equals(status)){
                result = "Y";
            }else{
                result = "N";
            }
        }
        return result;
    }
}
