package duomi.control;

import duomi.dispatch.request.*;
import duomi.services.MobileCheckForAppService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.phone.MobileHaltResult;
import duomi.com.httpIvk.param.phone.MobileMostCityResult;
import duomi.com.httpIvk.param.phone.MobileStatusResult;
import duomi.com.httpIvk.param.phone.PhoneCheckResult;
import duomi.com.httpIvk.param.phone.PhoneOnlineDurationResult;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.response.ComResponse;
import duomi.services.MobileCheckService;
import duomi.services.MobileCityService;
import duomi.services.MobileHaltService;

@Controller
public class MobileRelController {
	private static Logger log = Logger.getLogger(MobileRelController.class);

	@Autowired
	private MobileCheckService service;

	@Autowired
	private MobileHaltService moblieHaltservice;

	@Autowired
	private MobileCityService mobileCityService;

	@Autowired
	private MobileCheckForAppService mobileCheckForAppService;

	@RequestMapping(value = "/checkMoble", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse checkMobile(Mobile3ERequest request) {
		ComResponse rsp = null;
		request.setInterNo(InterFaceConstants.ZB_MOBILE_3E_NO);
		request.setInterName(InterFaceConstants.ZB_MOBILE_3E_NAME);// 手机身份验证
		request.setInterType(InterFaceConstants.ZB_MOBILE_3E_TYPE);
		try {
			rsp = service.checkMobileBy3E(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<PhoneCheckResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = (ComResponse) new ResponseSimpleHelper<PhoneCheckResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}

	/**
	 * 为app端新开的一个接口
	 * 因为他们没有appNo,只通过name,mobile,idCard去判断是否已经查询过;
	 *
	 * 手机身份验证一致就返回YES,不一致返回NO
	 * */
	@RequestMapping(value = "/checkMobleForApp", method = RequestMethod.POST)
	@ResponseBody
	public String checkMobileForApp(Mobile3ERequest request){
		String result = "N";

		try {
			result = mobileCheckForAppService.checkMobileBy3E(request);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

		return result;
	}





	@RequestMapping(value = "/getPhoneOnlineDuration", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse getOnlineDuration(PhoneOnlineDurationRequest request) {
		ComResponse rsp = null;
		request.setInterNo(InterFaceConstants.ZB_ONLINEDURATION_NO);
		request.setInterName(InterFaceConstants.ZB_ONLINEDURATION_NAME);// 手机号在网时长查询（三大运营商）
		request.setInterType(InterFaceConstants.ZB_ONLINEDURATION_TYPE);
		try {
			rsp = service.getOnlineDuration(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<PhoneOnlineDurationResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			rsp = (ComResponse) new ResponseSimpleHelper<PhoneOnlineDurationResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}

	@RequestMapping(value = "/getMobileStatus", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse getMobileStatus(MobileStatusRequest request) {
		ComResponse rsp = null;
		request.setInterNo(InterFaceConstants.ZB_MOBILESTATUS_NO);
		request.setInterName(InterFaceConstants.ZB_MOBILESTATUS_NAME);// 手机号状态查询(三大运营商)
		request.setInterType(InterFaceConstants.ZB_MOBILESTATUS_TYPE);
		try {
			rsp = service.getMobileStatus(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<MobileStatusResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = (ComResponse) new ResponseSimpleHelper<MobileStatusResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}

	@RequestMapping(value = "/getMobileHalt", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<MobileHaltResult> getMobileHalt(MobileHaltRequest request) {
		ComResponse<MobileHaltResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_MOBILEHALT_NO);
		request.setInterName(InterFaceConstants.ZB_MOBILEHALT_NAME);// 手机号状态查询(三大运营商)
		request.setInterType(InterFaceConstants.ZB_MOBILEHALT_TYPE);
		try {
			rsp = moblieHaltservice.CheckMoblieHalt(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<MobileHaltResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<MobileHaltResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}

	@RequestMapping(value = "/getMobileMostcity", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<MobileMostCityResult> getMobileMostcity(MobileMostCityRequest request) {
		ComResponse<MobileMostCityResult> rsp = null;
		request.setInterNo(InterFaceConstants.ZB_MOBILEMOSTCITY_NO);
		request.setInterName(InterFaceConstants.ZB_MOBILEMOSTCITY_NAME);// 手机号指定月通话天数最多的城市
		request.setInterType(InterFaceConstants.ZB_MOBILEMOSTCITY_TYPE);
		try {
			rsp = mobileCityService.CheckMobileCity(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<MobileMostCityResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<MobileMostCityResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
}
