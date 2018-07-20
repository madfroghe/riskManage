package duomi.services;

import duomi.com.httpIvk.param.creditscore.CreditScoreResult;
import duomi.dispatch.request.CreditScoreRequest;
import duomi.dispatch.response.ComResponse;

public interface CreditScoreService {
	
	//个人信用评分
	public ComResponse<CreditScoreResult> getCreditScore(CreditScoreRequest request) throws Exception;
		

}
