package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.consume.ConsCreditOfflineResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.ConsCreditOfflineRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.ConsCreditOfflineService;

/**
 * 线下消费贷客群评分
 * 
 * @author Administrator
 *
 */
@Controller
public class ConsCreditOfflineController {
	private static Logger log = Logger.getLogger(CensusRegisterController.class);
	@Autowired
	private ConsCreditOfflineService service;

	@RequestMapping(value = "/getConsCreditOffline", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<ConsCreditOfflineResult> getConsCreditOffline(ConsCreditOfflineRequest request) {
		ComResponse<ConsCreditOfflineResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_CONSCREDITOFFLINE_NO);
		request.setInterName(InterFaceConstants.ZB_CONSCREDITOFFLINE_NAME);// 线下消费贷客群评分
		request.setInterType(InterFaceConstants.ZB_CONSCREDITOFFLINE_TYPE);
		try {
			rsp = service.getConsCreditOffline(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<ConsCreditOfflineResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<ConsCreditOfflineResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
