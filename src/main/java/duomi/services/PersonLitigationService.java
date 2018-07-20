package duomi.services;

import duomi.com.httpIvk.param.litigation.PersonLitigationResult;
import duomi.dispatch.request.PersonLitigationRequest;
import duomi.dispatch.response.ComResponse;

public interface PersonLitigationService {
	// 个人涉诉全类查询
	public ComResponse<PersonLitigationResult> getPersonEnterprise(PersonLitigationRequest request) throws Exception;
}
