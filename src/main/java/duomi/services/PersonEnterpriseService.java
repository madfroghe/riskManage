package duomi.services;

import duomi.com.httpIvk.param.enterprise.PersonEnterpriseResult;
import duomi.dispatch.request.PersonEnterpriseRequest;
import duomi.dispatch.response.ComResponse;

public interface PersonEnterpriseService {
	//个人名下企业查询
	public ComResponse<PersonEnterpriseResult> getPersonEnterprise(PersonEnterpriseRequest request) throws Exception;
}
