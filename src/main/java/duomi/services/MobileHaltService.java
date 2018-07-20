package duomi.services;

import duomi.com.httpIvk.param.phone.MobileHaltResult;
import duomi.dispatch.request.MobileHaltRequest;
import duomi.dispatch.response.ComResponse;

public interface MobileHaltService {
	
	//手机号码最近三月停机次数查询
	public ComResponse<MobileHaltResult> CheckMoblieHalt(MobileHaltRequest request) throws Exception;
		
}
