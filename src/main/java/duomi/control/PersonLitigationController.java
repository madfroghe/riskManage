package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.litigation.PersonLitigationResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.PersonLitigationRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.PersonLitigationService;

/**
 * 个人涉诉全类查询
 * 
 * @author Administrator
 *
 */
@Controller
public class PersonLitigationController {
	private static Logger log = Logger.getLogger(PersonLitigationController.class);

	@Autowired
	private PersonLitigationService plService;

	@RequestMapping(value = "/getPersonLitigation", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<PersonLitigationResult> getPersonLitigation(PersonLitigationRequest request) {
		ComResponse<PersonLitigationResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_PERSONLITIGATION_NO);
		request.setInterName(InterFaceConstants.ZB_PERSONLITIGATION_NAME);// 个人涉诉全类查询
		request.setInterType(InterFaceConstants.ZB_PERSONLITIGATION_TYPE);
		try {
			rsp = plService.getPersonEnterprise(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<PersonLitigationResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<PersonLitigationResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
