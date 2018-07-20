package duomi.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.register.CensusRegisterResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.CensusRegisterRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.CensusRegisterService;

/**
 * 户籍查询
 * 
 * @author Administrator
 *
 */
@Controller
public class CensusRegisterController {
	private static Logger log = Logger.getLogger(CensusRegisterController.class);
	@Autowired
	private CensusRegisterService service;

	@RequestMapping(value = "/getCensusRegister", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<CensusRegisterResult> getCreditScore(CensusRegisterRequest request) {
		ComResponse<CensusRegisterResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_CENSUSREGISTER_NO);
		request.setInterName(InterFaceConstants.ZB_CENSUSREGISTER_NAME);// 户籍查询
		request.setInterType(InterFaceConstants.ZB_CENSUSREGISTER_TYPE);
		try {
			rsp = service.getCensusRegister(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<CensusRegisterResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<CensusRegisterResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
