package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.multipleLend.MultipleCreditAppResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.MultipleCreditAppRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.MultipleCreditAppService;

/**
 * 多次信贷申请
 * 
 * @author Administrator
 *
 */
@Controller
public class MultipleCreditAppController {
	private static Logger log = Logger.getLogger(MultipleCreditAppController.class);
	@Autowired
	private MultipleCreditAppService service;

	@RequestMapping(value = "/getMultipleCreditApp", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<MultipleCreditAppResult> getMultipleCreditApp(MultipleCreditAppRequest request) {
		ComResponse<MultipleCreditAppResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_MULTIPLECREDITAPPE_NO);
		request.setInterName(InterFaceConstants.ZB_MULTIPLECREDITAPPE_NAME);// 多次信贷申请
		request.setInterType(InterFaceConstants.ZB_MULTIPLECREDITAPPE_TYPE);
		try {
			rsp = service.getMultipleCreditApp(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);

			rsp = new ResponseSimpleHelper<MultipleCreditAppResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);

			rsp = new ResponseSimpleHelper<MultipleCreditAppResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
