package mobile;

import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.mobiledetail.MBDetailRequest;
import duomi.com.httpIvk.param.mobiledetail.MbLoginResponse;
import duomi.dispatch.request.MobileDetailRequest;
import duomi.dispatch.response.result.MobileDetailResponse;
import duomi.services.impl.TDMobileDetailServiceImpl;

public class Testmobile {
	public static void main(String[] args) {
		try {
			new Testmobile().getMobileDetail();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 开始
	 * 
	 * @throws HttpBizException
	 */
	public void start() throws HttpBizException {
		MobileDetailRequest req = new MobileDetailRequest();
		req.setIdcard("110101198701011372");
		req.setName("刘艺");
		req.setMobile("13658032580");
		req.setMbpasswd("870526");

		TDMobileDetailServiceImpl service = new TDMobileDetailServiceImpl();
		// MbLoginResponse rsp = service.sendVerificationCode(req, "QUERY");
		MobileDetailResponse rsp = service.startTesk(req);
		System.out.println(rsp.getCode() + "-----" + rsp.getMsg() + "------" + rsp.getNext_stage());
	}

	/**
	 * 发送验证码
	 * 
	 * @throws HttpBizException
	 */
	public void send() throws HttpBizException {
		MobileDetailRequest req = new MobileDetailRequest();
		// req.setIdcard("511028198702134825");
		// req.setName("刘春惠");
		// req.setMobile("13658032580");
		// req.setMbpasswd("870526");
		req.setShortMsgCode("132419");
		req.setTask_id("TASKYYS100000201712181637550720981118");
		req.setNext_stage("QUERY");

		TDMobileDetailServiceImpl service = new TDMobileDetailServiceImpl();
		// MbLoginResponse rsp = service.sendVerificationCode(req, "QUERY");
		MobileDetailResponse rsp = service.sendTesk(req);
		System.out.println(rsp.getCode() + "-----" + rsp.getMsg() + "------" + rsp.getNext_stage());
	}

	/**
	 * 重置验证码
	 */
	public void resetCode() {
		MobileDetailRequest req = new MobileDetailRequest();
		req.setIdcard("511028198702134825");
		req.setName("刘春惠");
		req.setMobile("13658032580");
		req.setMbpasswd("870526");
		req.setShortMsgCode("865654");
		req.setTask_id("TASKYYS100000201711301521590681101965");

		TDMobileDetailServiceImpl service = new TDMobileDetailServiceImpl();
		// MbLoginResponse rsp = service.sendVerificationCode(req, "QUERY");
		MbLoginResponse rsp = service.ResetCode(req);
		System.out.println(rsp.getCode() + "-----" + rsp.getMessage());
	}

	/**
	 * 从同盾获取验证详情
	 */
	public void getMobileDetail() {
		MBDetailRequest req = new MBDetailRequest();
		req.setTask_id("TASKYYS100000201801291657180661521515");
		TDMobileDetailServiceImpl service = new TDMobileDetailServiceImpl();
		String rsp = service.getMobileDetail(req);
		System.out.println(rsp);
	}

}
