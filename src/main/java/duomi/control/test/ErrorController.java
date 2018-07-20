package duomi.control.test;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.InterfacePo;
import duomi.com.httpIvk.param.phone.PhoneCheckResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.BackCard3ERequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.BankCardService;
import duomi.services.impl.OutsideServiceRegistServiceImpl;

@Controller
public class ErrorController {
	
	@Autowired
	private BankCardService service;
	
	@Autowired
	private OutsideServiceRegistServiceImpl regitSrv;
	
	@RequestMapping(value="/bankCard3E2",method=RequestMethod.POST)
	@ResponseBody
    public ComResponse CheckBankCard3Element(BackCard3ERequest request){
		
		return (ComResponse<PhoneCheckResult>) new ResponseSimpleHelper<PhoneCheckResult>().createErrorRsp(request, null);
	}
}
