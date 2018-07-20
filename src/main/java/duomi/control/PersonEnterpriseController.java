package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.enterprise.PersonEnterpriseResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.PersonEnterpriseRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.PersonEnterpriseService;

/**
 * 个人名下企业查询
 * 
 * @author Administrator
 *
 */
@Controller
public class PersonEnterpriseController {
	private static Logger log = Logger.getLogger(PersonEnterpriseController.class);

	@Autowired
	private PersonEnterpriseService peService;

	@RequestMapping(value = "/getPersonEnterprise", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<PersonEnterpriseResult> getPersonEnterprise(PersonEnterpriseRequest request) {
		ComResponse<PersonEnterpriseResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_PERSONENTERPRISE_NO);
		request.setInterName(InterFaceConstants.ZB_PERSONENTERPRISE_NAME);// 个人名下企业查询
		request.setInterType(InterFaceConstants.ZB_PERSONENTERPRISE_TYPE);
		try {
			rsp = peService.getPersonEnterprise(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);

			rsp = new ResponseSimpleHelper<PersonEnterpriseResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<PersonEnterpriseResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
