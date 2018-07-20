package duomi.services;

import duomi.com.httpIvk.param.travel.FlightInfoResult;
import duomi.dispatch.request.FlightInfoRequest;
import duomi.dispatch.response.ComResponse;

public interface FlightInfoServcie {
	// 航旅信息查询
	public ComResponse<FlightInfoResult> getFlightInfo(FlightInfoRequest request) throws Exception;

}
