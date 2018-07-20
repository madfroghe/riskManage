package duomi.services;

import duomi.com.httpIvk.param.idcard.IdCardCheckResult;
import duomi.dispatch.request.IdCardRequest;
import duomi.dispatch.response.ComResponse;

public interface IdCardCheckService {
	public ComResponse<IdCardCheckResult> CheckIdCard(IdCardRequest request) throws Exception;
}
