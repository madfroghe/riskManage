package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.idcard.IdCardCheckResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.IdCardRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.IdCardCheckService;

/**
 * 身份证一致性验证
 * 
 * @author Administrator
 *
 */
@Controller
public class IdCardCheckController {
	private static Logger log = Logger.getLogger(IdCardCheckController.class);

	@Autowired
	private IdCardCheckService idCardSrv;

	@RequestMapping(value = "/checkIdCard", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse checkIdCard(IdCardRequest request) {
		ComResponse rsp = null;
		request.setInterNo(InterFaceConstants.ZB_IDCARD_NO);
		request.setInterName(InterFaceConstants.ZB_IDCARD_NAME);// 身份证一致性验证
		request.setInterType(InterFaceConstants.ZB_IDCARD_TYPE);
		try {
			rsp = idCardSrv.CheckIdCard(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<IdCardCheckResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<IdCardCheckResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}

}
