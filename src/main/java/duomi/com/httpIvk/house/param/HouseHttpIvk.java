package duomi.com.httpIvk.house.param;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import duomi.com.constants.PubConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.GenData.GenTestData;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.com.httpIvk.helper.HttpTool;
import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.JSONUtils;
import duomi.dispatch.request.ComRequest;
import duomi.services.OutsideServiceRegistService;

public class HouseHttpIvk<E> {
	private final static Logger logger = LoggerFactory.getLogger(HttpIvkHelper.class);

	private static final String url = PubConstants.ZBADATA_URL;

	private OutsideServiceRegistService regitSrv;

	public BaseResponse<E> postData(String url, ComRequest comReq, HouseBaseInput baseReq, Class<?> class1)
			throws Exception {
		BaseResponse<E> output = null;

		// 更新登记表状态为发送，
		regitSrv.updateCspStusBefore(comReq);

		if (PubConstants.ZBADATA_SWITCH_ON.equals(PubConstants.ZBADATA_SWITCH)) {

			// List<NameValuePair> list =
			// JSONUtils.toNameValuePairList(baseReq);
			String reqstr = JSONUtils.toJSONString(baseReq);

			logger.info("---------请求消息-----------接口识别标志：" + class1.getName());
			logger.info("审批工单编号：" + comReq.getAppNo() + ",请求消息：" + reqstr);

			String retstr = HttpTool.doPost(url, reqstr);

			logger.info("---------返回消息-----------接口识别标志：" + class1.getName());
			logger.info("审批工单编号：" + comReq.getAppNo() + ",返回消息：" + retstr);

			GsonUtils<E> gson = new GsonUtils<E>();
			output = (BaseResponse<E>) gson.fromJson(retstr, BaseResponse.class, class1);

			if (!output.isSuccess()) {
				logger.info("审批工单编号：" + comReq.getAppNo() + ",errorDesc:" + output.getErrorDesc() + "接口识别标志："
						+ class1.getName());
				throw new HttpBizException(output.getCode(), output.getErrorDesc());
			}
		} else {
			GenTestData gtd = new GenTestData();
			output = gtd.genData4test(class1);
		}

		// 更新登记表状态为收到服务
		regitSrv.updateCspStusAfter(comReq, output);

		return output;
	}
}
