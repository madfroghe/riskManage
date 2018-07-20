package duomi.com.httpIvk.services;

import java.util.List;

import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duomi.com.constants.PubConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.com.httpIvk.helper.HttpUtil;
import duomi.com.httpIvk.param.creditGuard.CreditGuardInput;
import duomi.com.httpIvk.param.creditGuard.CreditGuardResponse;
import duomi.com.httpIvk.param.creditGuard.CreditGuardResult;
import duomi.com.utils.JSONUtils;
import duomi.dispatch.request.CreditGuardRequest;
import duomi.services.OutsideServiceRegistService;

@Service
public class TongDDataHttpServiceImpl implements TongDDataHttpService {
	private final static Logger logger = LoggerFactory.getLogger(ZbaDataHttpServiceImpl.class);

	private final static String url_CreditGuard = "/bodyguard/apply/v4?";

	private final static String url_param = "partner_code=duomian&partner_key=f0cc1ba1555e441288aa5f4787fdfac7&app_name=fjd_web";

	@Autowired
	private OutsideServiceRegistService regitSrv;

	/**
	 * 获取信贷保镖
	 */
	public CreditGuardResponse getCreditGuard(CreditGuardRequest request) throws Exception {
		String url = PubConstants.TONGDUNDATA_URL + this.url_CreditGuard + this.url_param;
		CreditGuardInput input = new CreditGuardInput();
		input.setAccount_mobile(request.getMobile());
		input.setAccount_name(request.getName());
		input.setId_number(request.getIdCard());

		HttpIvkHelper<CreditGuardResult> helper = new HttpIvkHelper<CreditGuardResult>();
		helper.setRegitSrv(regitSrv);

		CreditGuardResponse output = null;
		String retstr = "";
		// 更新登记表状态为发送，
		regitSrv.updateCspStusBefore(request);
		String appno = request.getAppNo();
		String interName = request.getInterName();
		if (PubConstants.SWITCH_ON.equals(PubConstants.TONGDUDATA_SWITCH)) {

			List<NameValuePair> list = JSONUtils.toNameValuePairList(input);
			String reqstr = JSONUtils.toJSONString(input);

			logger.info("---------请求消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",请求消息：" + reqstr);

			// String retstr = HttpTool.post(url, list);
			retstr = HttpUtil.post(url, list);

			// 个人贷款逾期特殊处理

			logger.info("---------返回消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",返回消息：" + retstr);

			output = JSONUtils.toBean(retstr, CreditGuardResponse.class);

			/*
			 * GsonUtils<CreditGuardResult> gson = new
			 * GsonUtils<CreditGuardResult>(); output =
			 * (BaseResponse<CreditGuardResult>) gson.fromJson(retstr,
			 * BaseResponse.class, CreditGuardResult.class);
			 */

			if (!output.isSuccess()) {
				logger.error("接口：" + interName + "审批工单编号：" + appno + ",errorDesc:" + output.getReason_desc());
				throw new HttpBizException(output.getReason_code(), output.getReason_desc());
			}
		} else {
			logger.error("接口：" + interName + "审批工单编号：" + appno + "调用同盾接口开关已关闭");
			throw new HttpBizException("调用同盾接口开关已关闭");
		}

		// 更新登记表状态为收到服务
		// retstr = duomi.com.utils.StringUtils.decodeUnicode(retstr);
		regitSrv.updateCspStusAfter(request, retstr);

		return output;

	}

}
