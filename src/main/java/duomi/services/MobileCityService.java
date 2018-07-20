package duomi.services;

import duomi.com.httpIvk.param.phone.MobileMostCityResult;
import duomi.dispatch.request.MobileMostCityRequest;
import duomi.dispatch.response.ComResponse;

public interface MobileCityService {
	
	//手机号码最近三月停机次数查询
	public ComResponse<MobileMostCityResult> CheckMobileCity(MobileMostCityRequest request) throws Exception;
		
}
