package duomi.services.BRongService;

import duomi.dispatch.request.baiRong.BRCommonRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

public interface BRBizCommonService {



    //百融-企业工商信息详情
    ComResponse<JSONObject> getBizDetail(BRCommonRequest request);

    //百融-企业图谱
    ComResponse<JSONObject> getCompanyMap(BRCommonRequest request);

    //百融-失信黑名单
    ComResponse<JSONObject> getBlackList(BRCommonRequest request);

    //百融-企业关键字查询
    ComResponse<JSONObject> getBizKeySearch(BRCommonRequest request);

    //百融-企业疑似关系
    ComResponse<JSONObject> getBizRelationship(BRCommonRequest request);

    //百融-企业被执行人信息
    ComResponse<JSONObject> getBizExecutor(BRCommonRequest request);

    //百融-企业失信被执行人信息
    ComResponse<JSONObject> getBizUncreditExecutor(BRCommonRequest request);

    //百融-企业法院公告
    ComResponse<JSONObject> getBizCourtAnnouncement(BRCommonRequest request);

    //百融-企业四要素验证
    ComResponse<JSONObject> getBizFour(BRCommonRequest request);
}
