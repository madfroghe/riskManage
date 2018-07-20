package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.phone.MobileNumbersResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.MobileNumbersRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.MobileNumbersServcie;

/**
 * 自然人手机接入个数
 * 
 * @author Administrator
 *
 */
@Controller
public class MobileNumbersController {
	private static Logger log = Logger.getLogger(MobileNumbersController.class);

	@Autowired
	private MobileNumbersServcie service;

	@RequestMapping(value = "/getMobileNumbers", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<MobileNumbersResult> getMobileNumbers(MobileNumbersRequest request) {
		ComResponse<MobileNumbersResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_MOBILENUMBERS_NO);
		request.setInterName(InterFaceConstants.ZB_MOBILENUMBERS_NAME);// 自然人手机接入个数
		request.setInterType(InterFaceConstants.ZB_MOBILENUMBERS_TYPE);
		try {
			rsp = service.getMobileNumbers(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<MobileNumbersResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<MobileNumbersResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
