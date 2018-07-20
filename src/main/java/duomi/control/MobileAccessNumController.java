package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.phone.MobileAccessNumResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.MobileAccessNumRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.MobileAccessNumService;

/**
 * 手机自然人接入号码个数查询
 * 
 * @author Administrator
 *
 */
@Controller
public class MobileAccessNumController {
	private static Logger log = Logger.getLogger(MobileAccessNumController.class);

	@Autowired
	private MobileAccessNumService service;

	@RequestMapping(value = "/getMobileAccessNum", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<MobileAccessNumResult> getMobileAccessNum(MobileAccessNumRequest request) {
		ComResponse<MobileAccessNumResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_MOBILEACCESSNUM_NO);
		request.setInterName(InterFaceConstants.ZB_MOBILEACCESSNUM_NAME);// 手机自然人接入号码个数查询
		request.setInterType(InterFaceConstants.ZB_MOBILEACCESSNUM_TYPE);
		try {
			rsp = service.getMobileAccessNumService(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<MobileAccessNumResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<MobileAccessNumResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
