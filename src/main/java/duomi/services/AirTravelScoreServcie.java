package duomi.services;

import duomi.com.httpIvk.param.travel.AirTravelScoreResult;
import duomi.dispatch.request.AirTravelScoreRequest;
import duomi.dispatch.response.ComResponse;

public interface AirTravelScoreServcie {

	// 航旅旅客价值等级查询
	public ComResponse<AirTravelScoreResult> getAirTravelScore(AirTravelScoreRequest request) throws Exception;

}
