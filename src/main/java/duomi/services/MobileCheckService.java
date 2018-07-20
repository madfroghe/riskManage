package duomi.services;

import duomi.com.httpIvk.param.phone.MobileStatusResult;
import duomi.com.httpIvk.param.phone.PhoneCheckResult;
import duomi.com.httpIvk.param.phone.PhoneOnlineDurationResult;
import duomi.dispatch.request.Mobile3ERequest;
import duomi.dispatch.request.MobileStatusRequest;
import duomi.dispatch.request.PhoneOnlineDurationRequest;
import duomi.dispatch.response.ComResponse;

public interface MobileCheckService {
	//
	public ComResponse<PhoneCheckResult> checkMobileBy3E(Mobile3ERequest request) throws Exception;
	
	//手机号在网时长查询(三大运营商)
	public ComResponse<PhoneOnlineDurationResult> getOnlineDuration(PhoneOnlineDurationRequest request) throws Exception;
	
	//手机号状态查询(三大运营商)
	public ComResponse<MobileStatusResult> getMobileStatus(MobileStatusRequest request) throws Exception;
		
}
