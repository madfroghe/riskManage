package duomi.services;

import duomi.com.httpIvk.param.register.CensusRegisterResult;
import duomi.dispatch.request.CensusRegisterRequest;
import duomi.dispatch.response.ComResponse;

public interface CensusRegisterService {
	// 户籍查询
	public ComResponse<CensusRegisterResult> getCensusRegister(CensusRegisterRequest request) throws Exception;

}
