package duomi.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.bankCard.BackCardFourELementResult;
import duomi.com.httpIvk.param.bankCard.BackCardThreeELementResult;
import duomi.com.httpIvk.param.bankCard.BankCardTradeResult;
import duomi.com.utils.JSONUtils;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.BackCard3ERequest;
import duomi.dispatch.request.BackCard4ERequest;
import duomi.dispatch.request.BankCardTradeRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.BankCardService;

@Controller
public class BankCardController {

	private static Logger log = Logger.getLogger(BankCardController.class);
	@Autowired
	private BankCardService service;

	@RequestMapping(value = "/bankCard3E", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<BackCardThreeELementResult> CheckBankCard3Element(BackCard3ERequest request) {
		ComResponse<BackCardThreeELementResult> rsp = null;

		request.setInterNo(InterFaceConstants.ZB_BANKCARD_3E_NO);
		request.setInterName(InterFaceConstants.ZB_BANKCARD_3E_NAME);// 银行卡三要素验证
		request.setInterType(InterFaceConstants.ZB_BANKCARD_3E_TYPE);
		try {
			rsp = service.CheckBankCard3Element(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<BackCardThreeELementResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<BackCardThreeELementResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}

	@RequestMapping(value = "/bankCard4E", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<BackCardFourELementResult> CheckBankCard4Element(BackCard4ERequest request) {
		ComResponse<BackCardFourELementResult> rsp = null;

		request.setInterNo(InterFaceConstants.ZB_BANKCARD_4E_NO);
		request.setInterName(InterFaceConstants.ZB_BANKCARD_4E_NAME);// 银行卡四要素验证
		request.setInterType(InterFaceConstants.ZB_BANKCARD_4E_TYPE);

		try {
			rsp = service.CheckBankCard4Element(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<BackCardFourELementResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			rsp = new ResponseSimpleHelper<BackCardFourELementResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}

	@RequestMapping(value = "/getBackCardTrade", method = RequestMethod.POST)
	public void getBackCardTrade(BankCardTradeRequest request, HttpServletResponse response) {

		try {
			PrintWriter out = response.getWriter();
			ComResponse<BankCardTradeResult> rsp = null;

			request.setInterNo(InterFaceConstants.ZB_BACKCARDTRADE_NO);
			request.setInterName(InterFaceConstants.ZB_BACKCARDTRADE_NAME);// 银行卡信息查询
			request.setInterType(InterFaceConstants.ZB_BACKCARDTRADE_TYPE);

			try {
				rsp = service.getBackCardTrade(request);
			} catch (HttpBizException e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
				rsp = new ResponseSimpleHelper<BankCardTradeResult>().createComErrorRsp(request, null, e);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
				rsp = new ResponseSimpleHelper<BankCardTradeResult>().createComErrorRsp(request, null);
			}

			String rspStr = JSONUtils.toJSONString(rsp);
			out.write(rspStr);
			out.flush();
			out.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return;
	}

}
