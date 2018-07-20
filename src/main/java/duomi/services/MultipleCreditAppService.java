package duomi.services;

import duomi.com.httpIvk.param.multipleLend.MultipleCreditAppResult;
import duomi.dispatch.request.MultipleCreditAppRequest;
import duomi.dispatch.response.ComResponse;

public interface MultipleCreditAppService {
	// 个人借款多次申请
	public ComResponse<MultipleCreditAppResult> getMultipleCreditApp(MultipleCreditAppRequest request) throws Exception;

}
