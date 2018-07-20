package duomi.services;

import duomi.com.httpIvk.param.consume.ConsCreditOfflineResult;
import duomi.dispatch.request.ConsCreditOfflineRequest;
import duomi.dispatch.response.ComResponse;

public interface ConsCreditOfflineService {
	// 线下消费贷
	public ComResponse<ConsCreditOfflineResult> getConsCreditOffline(ConsCreditOfflineRequest request) throws Exception;

}
