package duomi.services;

import duomi.com.httpIvk.param.creditGuard.CreditGuardResult;
import duomi.dispatch.request.CreditGuardRequest;
import duomi.dispatch.response.ComResponse;

public interface CreditGuardService {
	// 信贷保镖
	public ComResponse<CreditGuardResult> getCreditGuard(CreditGuardRequest request) throws Exception;
}
