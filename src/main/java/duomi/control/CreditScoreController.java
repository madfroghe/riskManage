package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.creditscore.CreditScoreResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.CreditScoreRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.CreditScoreService;

@Controller
public class CreditScoreController {
	private static Logger log = Logger.getLogger(CreditScoreController.class);

	@Autowired
	private CreditScoreService service;

	@RequestMapping(value = "/getCreditScore", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse getCreditScore(CreditScoreRequest request) {
		ComResponse rsp = null;
		request.setInterNo(InterFaceConstants.ZB_CREDITSCORE_NO);
		request.setInterName(InterFaceConstants.ZB_CREDITSCORE_NAME);// 个人信用评分
		request.setInterType(InterFaceConstants.ZB_CREDITSCORE_TYPE);
		try {
			rsp = service.getCreditScore(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<CreditScoreResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<CreditScoreResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
