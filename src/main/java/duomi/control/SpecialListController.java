package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.special.SpecialListResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.SpecialListRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.SpecialListService;

/**
 * 特殊名单核查
 * 
 * @author Administrator
 *
 */
@Controller
public class SpecialListController {
	private static Logger log = Logger.getLogger(SpecialListController.class);

	@Autowired
	private SpecialListService service;

	@RequestMapping(value = "/getSpecialList", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<SpecialListResult> getSpecialList(SpecialListRequest request) {
		ComResponse<SpecialListResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_SPECIALLIST_NO);
		request.setInterName(InterFaceConstants.ZB_SPECIALLIST_NAME);// 特殊名单核查
		request.setInterType(InterFaceConstants.ZB_SPECIALLIST_TYPE);
		try {
			rsp = service.getSpecialList(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<SpecialListResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<SpecialListResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
