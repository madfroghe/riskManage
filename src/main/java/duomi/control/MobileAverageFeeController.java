package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.phone.MobileAverageFeeResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.MobileAverageFeeRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.MobileAverageFeeService;

/**
 * 手机月平均消费
 * 
 * @author Administrator
 *
 */
@Controller
public class MobileAverageFeeController {
	private static Logger log = Logger.getLogger(MobileAverageFeeController.class);

	@Autowired
	private MobileAverageFeeService service;

	@RequestMapping(value = "/getMobileAverageFee", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<MobileAverageFeeResult> checkMobileAverageFee(MobileAverageFeeRequest request) {
		ComResponse<MobileAverageFeeResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_MOBILEAVERAGEFEE_NO);
		request.setInterName(InterFaceConstants.ZB_MOBILEAVERAGEFEE_NAME);// 手机月平均消费
		request.setInterType(InterFaceConstants.ZB_MOBILEAVERAGEFEE_TYPE);
		try {
			rsp = service.checkMobileAverageFee(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<MobileAverageFeeResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<MobileAverageFeeResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
