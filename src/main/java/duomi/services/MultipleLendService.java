package duomi.services;

import duomi.com.httpIvk.param.multipleLend.MultipleLendResult;
import duomi.dispatch.request.MultipleLendRequest;
import duomi.dispatch.response.ComResponse;

public interface MultipleLendService {
	
	public ComResponse<MultipleLendResult> checkMultipleLend(MultipleLendRequest request) throws Exception;

}
