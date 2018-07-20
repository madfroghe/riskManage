package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.antifraud.AntiFraudResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.AntiFraudRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.AntiFraudService;

/**
 * 反欺诈云
 * 
 * @author Administrator
 *
 */
@Controller
public class AntiFraudController {
	private static Logger log = Logger.getLogger(AntiFraudController.class);
	@Autowired
	private AntiFraudService brSrv;

	@RequestMapping(value = "/checkAntiFraud", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse checkAntiFraud(AntiFraudRequest request) {
		ComResponse rsp = null;
		request.setInterNo(InterFaceConstants.ZB_ANTIFRAUD_NO);
		request.setInterName(InterFaceConstants.ZB_ANTIFRAUD_NAME);// 反欺诈云
		request.setInterType(InterFaceConstants.ZB_ANTIFRAUD_TYPE);
		try {
			rsp = brSrv.CheckAntiFraud(request);
		} catch (HttpBizException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			rsp = new ResponseSimpleHelper<AntiFraudResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			rsp = new ResponseSimpleHelper<AntiFraudResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
