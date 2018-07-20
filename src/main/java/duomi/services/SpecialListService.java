package duomi.services;

import duomi.com.httpIvk.param.special.SpecialListResult;
import duomi.dispatch.request.SpecialListRequest;
import duomi.dispatch.response.ComResponse;

public interface SpecialListService {
	// 特殊名单核查
	public ComResponse<SpecialListResult> getSpecialList(SpecialListRequest request) throws Exception;

}
