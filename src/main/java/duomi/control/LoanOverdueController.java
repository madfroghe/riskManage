package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.overdue.LoanOverdueResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.LoanOverdueRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.LoanOverdueService;

/**
 * 个人贷款逾期
 * 
 * @author Administrator
 *
 */
@Controller
public class LoanOverdueController {
	private static Logger log = Logger.getLogger(LoanOverdueController.class);

	@Autowired
	private LoanOverdueService service;

	@RequestMapping(value = "/getLoanOverdue", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<LoanOverdueResult> getLoanOverdue(LoanOverdueRequest request) {
		ComResponse<LoanOverdueResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_LOANOVERDUE_NO);
		request.setInterName(InterFaceConstants.ZB_LOANOVERDUE_NAME);// 个人贷款逾期
		request.setInterType(InterFaceConstants.ZB_LOANOVERDUE_TYPE);
		try {
			rsp = service.getLoanOverdue(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<LoanOverdueResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<LoanOverdueResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
