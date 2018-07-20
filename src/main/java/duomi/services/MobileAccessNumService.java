package duomi.services;

import duomi.com.httpIvk.param.phone.MobileAccessNumResult;
import duomi.dispatch.request.MobileAccessNumRequest;
import duomi.dispatch.response.ComResponse;

public interface MobileAccessNumService {
	//手机号码自然人接入号码个数查询
	public ComResponse<MobileAccessNumResult> getMobileAccessNumService(MobileAccessNumRequest request) throws Exception;
}
