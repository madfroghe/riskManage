package duomi.com.httpIvk.services;

import duomi.dispatch.request.baiRong.BRCommonRequest;
import net.sf.json.JSONObject;

public interface BRongBizHttpService {

    JSONObject getBizDetail(BRCommonRequest request);

    JSONObject getBizCompanyMap(BRCommonRequest request);

    JSONObject getBizBlackList(BRCommonRequest request);

    JSONObject getBizKeySearch(BRCommonRequest request);

    JSONObject getBizFour(BRCommonRequest request);

    JSONObject getBizRelationship(BRCommonRequest request);

    JSONObject getBizExecutor(BRCommonRequest request);

    JSONObject getBizUncreditExecutor(BRCommonRequest request);

    JSONObject getBizCourtAnnouncement(BRCommonRequest request);
}
