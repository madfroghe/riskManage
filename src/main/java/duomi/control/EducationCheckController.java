package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.education.EducationCheckResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.EducationCheckquest;
import duomi.dispatch.response.ComResponse;
import duomi.services.EducationCheckService;

/**
 * 学历查询
 * 
 * @author Administrator
 *
 */
@Controller
public class EducationCheckController {
	private static Logger log = Logger.getLogger(EducationCheckController.class);

	@Autowired
	private EducationCheckService edctSrv;

	@RequestMapping(value = "/checkEducation", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse checkEducation(EducationCheckquest request) {
		ComResponse rsp = null;
		request.setInterNo(InterFaceConstants.ZB_EDUCATION_NO);
		request.setInterName(InterFaceConstants.ZB_EDUCATION_NAME);// 学历查询
		request.setInterType(InterFaceConstants.ZB_EDUCATION_TYPE);
		try {
			rsp = edctSrv.CheckEducation(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<EducationCheckResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<EducationCheckResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}

}
