package duomi.services;

import duomi.com.httpIvk.param.phone.MobileAverageFeeResult;
import duomi.dispatch.request.MobileAverageFeeRequest;
import duomi.dispatch.response.ComResponse;

public interface MobileAverageFeeService {
	//智宝手机月平均消费
	public ComResponse<MobileAverageFeeResult> checkMobileAverageFee(MobileAverageFeeRequest request) throws Exception;
}
