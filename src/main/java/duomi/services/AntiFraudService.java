package duomi.services;

import duomi.com.httpIvk.param.antifraud.AntiFraudResult;
import duomi.dispatch.request.AntiFraudRequest;
import duomi.dispatch.response.ComResponse;

public interface AntiFraudService {
	//反欺诈云
	public ComResponse<AntiFraudResult> CheckAntiFraud(AntiFraudRequest request) throws Exception;
}
