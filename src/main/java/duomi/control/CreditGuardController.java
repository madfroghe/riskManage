package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.creditGuard.CreditGuardResult;
import duomi.com.utils.JSONUtils;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.CreditGuardRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.impl.CreditGuardServiceImpl;

/**
 * 信贷保镖
 * 
 * @author Administrator
 *
 */
@Controller
public class CreditGuardController {
	private static Logger log = Logger.getLogger(CreditGuardController.class);

	@Autowired
	private CreditGuardServiceImpl service;

	@RequestMapping(value = "/getCreditGuard", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getCreditGuard(CreditGuardRequest request) {
		ComResponse<CreditGuardResult> rsp = null;
		request.setInterNo(InterFaceConstants.TD_CREDITGUARD_NO);
		request.setInterName(InterFaceConstants.TD_CREDITGUARD_NAME);// 信贷保镖
		request.setInterType(InterFaceConstants.TD_CREDITGUARD_TYPE);
		String comRspStr = "";
		try {
			rsp = service.getCreditGuard(request);

		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<CreditGuardResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<CreditGuardResult>().createComErrorRsp(request, null);
		}
		comRspStr = JSONUtils.toJSONString(rsp);
		return comRspStr;
	}
}
