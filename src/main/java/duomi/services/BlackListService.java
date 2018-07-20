package duomi.services;

import duomi.com.httpIvk.param.blacklist.BlackListResult;
import duomi.dispatch.request.BlackListRequest;
import duomi.dispatch.response.ComResponse;

public interface BlackListService {
	//个人黑名单
	public ComResponse<BlackListResult> CheckBlackList(BlackListRequest request) throws Exception;
			
}
