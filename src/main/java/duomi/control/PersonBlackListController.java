package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.blacklist.BlackListResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.BlackListRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.BlackListService;

/**
 * 个人黑名单查询
 * 
 * @author Administrator
 *
 */
@Controller
public class PersonBlackListController {
	private static Logger log = Logger.getLogger(PersonBlackListController.class);

	@Autowired
	private BlackListService blackListSrv;

	@RequestMapping(value = "/checkPersonBlackList", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<BlackListResult> checkPersonBlackList(BlackListRequest request) {
		ComResponse<BlackListResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_PERSONBLACKLIST_NO);
		request.setInterName(InterFaceConstants.ZB_PERSONBLACKLIST_NAME);// 个人黑名单查询
		request.setInterType(InterFaceConstants.ZB_PERSONBLACKLIST_TYPE);
		try {
			rsp = blackListSrv.CheckBlackList(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<BlackListResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<BlackListResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
