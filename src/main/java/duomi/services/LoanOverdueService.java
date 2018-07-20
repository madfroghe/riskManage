package duomi.services;

import duomi.com.httpIvk.param.overdue.LoanOverdueResult;
import duomi.dispatch.request.LoanOverdueRequest;
import duomi.dispatch.response.ComResponse;

public interface LoanOverdueService {
	public ComResponse<LoanOverdueResult> getLoanOverdue(LoanOverdueRequest request) throws Exception;
}
