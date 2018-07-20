package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.travel.AirTravelScoreResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.AirTravelScoreRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.AirTravelScoreServcie;

/**
 * 航旅旅客价值等级查询
 * 
 * @author Administrator
 *
 */
@Controller
public class AirTravelScoreController {
	private static Logger log = Logger.getLogger(AirTravelScoreController.class);

	@Autowired
	private AirTravelScoreServcie service;

	@RequestMapping(value = "/getAirTravelScore", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<AirTravelScoreResult> getAirTravelScore(AirTravelScoreRequest request) {
		ComResponse<AirTravelScoreResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_AIRTRAVELSCORE_NO);
		request.setInterName(InterFaceConstants.ZB_AIRTRAVELSCORE_NAME);
		request.setInterType(InterFaceConstants.ZB_AIRTRAVELSCORE_TYPE);
		try {
			rsp = service.getAirTravelScore(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<AirTravelScoreResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<AirTravelScoreResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
