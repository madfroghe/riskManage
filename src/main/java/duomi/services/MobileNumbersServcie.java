package duomi.services;

import duomi.com.httpIvk.param.phone.MobileNumbersResult;
import duomi.dispatch.request.MobileNumbersRequest;
import duomi.dispatch.response.ComResponse;

public interface MobileNumbersServcie {

	// 手机号自然人接入数
	public ComResponse<MobileNumbersResult> getMobileNumbers(MobileNumbersRequest request) throws Exception;
}
