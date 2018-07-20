package duomi.services;

import duomi.com.httpIvk.param.badrecords.BadrecordResult;
import duomi.dispatch.request.BadrecordRequest;
import duomi.dispatch.response.ComResponse;

public interface BadRecordService {
	//不良记录
	public ComResponse<BadrecordResult> CheckBadrecord(BadrecordRequest request) throws Exception;
		
}
