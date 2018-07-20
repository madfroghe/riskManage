package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.travel.FlightInfoResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.FlightInfoRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.FlightInfoServcie;

/**
 * 航旅信息查询
 * 
 * @author Administrator
 *
 */
@Controller
public class FlightInfoController {
	private static Logger log = Logger.getLogger(FlightInfoController.class);

	@Autowired
	private FlightInfoServcie service;

	@RequestMapping(value = "/getFlightInfo", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<FlightInfoResult> getFlightInfo(FlightInfoRequest request) {
		ComResponse<FlightInfoResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_FLIGHTINFO_NO);
		request.setInterName(InterFaceConstants.ZB_FLIGHTINFO_NAME);// 航旅信息查询
		request.setInterType(InterFaceConstants.ZB_FLIGHTINFO_TYPE);
		try {
			rsp = service.getFlightInfo(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<FlightInfoResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<FlightInfoResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
