package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.multipleLend.MultipleLendResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.MultipleLendRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.MultipleLendService;

/**
 * 个人多重借贷查询
 * 
 * @author Administrator
 *
 */
@Controller
public class MultipleLendController {
	private static Logger log = Logger.getLogger(MultipleLendController.class);

	@Autowired
	private MultipleLendService mltpSrv;

	@RequestMapping(value = "/checkMultipleLend", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse checkMultipleLend(MultipleLendRequest request) {
		ComResponse rsp = null;
		request.setInterNo(InterFaceConstants.ZB_MULTIPLELEND_NO);
		request.setInterName(InterFaceConstants.ZB_MULTIPLELEND_NAME);// 个人多重借贷查询
		request.setInterType(InterFaceConstants.ZB_MULTIPLELEND_TYPE);
		try {
			rsp = mltpSrv.checkMultipleLend(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);

			rsp = new ResponseSimpleHelper<MultipleLendResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);

			rsp = new ResponseSimpleHelper<MultipleLendResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
