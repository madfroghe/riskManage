package duomi.com.httpIvk.services;

import duomi.com.httpIvk.param.creditGuard.CreditGuardResponse;
import duomi.dispatch.request.CreditGuardRequest;

public interface TongDDataHttpService {
	// 信贷保镖
	public CreditGuardResponse getCreditGuard(CreditGuardRequest request) throws Exception;

}
