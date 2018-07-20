package duomi.com.httpIvk.services;

import duomi.com.httpIvk.param.JiGResponse;
import duomi.dbMap.bean.EigenfactorPo;
import duomi.dispatch.request.*;
import net.sf.json.JSONObject;


/**
 * Created by DEllComputer on 2018/1/30.
 */
public interface JGDataHttpService {

    //极光特征因子
    public JiGResponse<EigenfactorPo> getEigenfactor(EigenfactorRequest request) throws Exception;

    //极光黑名单
    public JiGResponse<JSONObject> getBlackList(JGBlackListRequest request) throws Exception;

    //极光-LBS验真接口(分级验真)
    public JiGResponse<JSONObject> lbsCheck(LbsCheckRequest request) throws Exception;

    //极光-LBS验真接口(模糊匹配)
    public JiGResponse<JSONObject> lbsBlurCheck(JGLbsBlurCheckRequest request) throws Exception;

    //极光-用户常用地址
    public JiGResponse<JSONObject> userAddress(JGUsersAddressRequest request) throws Exception;

    //极光-用户标签
    public JiGResponse<JSONObject> userTag(JGUsersTagRequest request) throws Exception;
}
