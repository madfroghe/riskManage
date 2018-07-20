package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.badrecords.BadrecordResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.BadrecordRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.BadRecordService;

/**
 * 不良信息核查
 * 
 * @author Administrator
 *
 */
@Controller
public class BadRecordController {
	private static Logger log = Logger.getLogger(BadRecordController.class);
	@Autowired
	private BadRecordService brSrv;

	@RequestMapping(value = "/checkBadrecord", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse checkEducation(BadrecordRequest request) {
		ComResponse rsp = null;
		request.setInterNo(InterFaceConstants.ZB_BADRECORD_NO);
		request.setInterName(InterFaceConstants.ZB_BADRECORD_NAME);// 不良信息核查
		request.setInterType(InterFaceConstants.ZB_BADRECORD_TYPE);
		try {
			rsp = brSrv.CheckBadrecord(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<BadrecordResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<BadrecordResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
