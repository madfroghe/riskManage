package duomi.dispatch.factory;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import duomi.com.constants.ComRspConstants;
import duomi.com.constants.HouseRspConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.com.httpIvk.house.param.HouseBaseResponse;
import duomi.com.utils.JSONUtils;
import duomi.dispatch.request.ComRequest;
import duomi.dispatch.response.ComResponse;

public class HsResponseSimpleHelper<T> {

	private final static Logger logger = LoggerFactory.getLogger(HttpIvkHelper.class);

	/**
	 * 将第三方服务返回消息 转换为 返回调度消息。
	 * 
	 * @param request
	 * @param baseRsp
	 * @return
	 */
	public ComResponse<T> ConvertRsp(ComRequest request, HouseBaseResponse<T> baseRsp) {

		String baseRspStr = JSONUtils.toJSONString(baseRsp);
		logger.debug("第三方返回信息：" + baseRspStr);

		ComResponse<T> comRsp = new ComResponse<T>();
		if (HouseRspConstants.CODE_SUCCESS.equals(baseRsp.getCode())) {
			comRsp = this.createSuccessRsp(request, baseRsp.getResult());
		} else {
			comRsp = this.createErrorRsp(request, baseRsp);
		}
		String comRspStr = JSONUtils.toJSONString(comRsp);
		logger.debug("返回调度平台信息：" + comRspStr);
		return comRsp;
	}

	public ComResponse<T> createSuccessRsp(ComRequest request, T t) {
		ComResponse<T> output = new ComResponse<T>();
		output.setResData(t);
		this.iniSuccessRsp(request, output);
		return output;
	}

	public ComResponse<T> createErrorRsp(ComRequest request, HouseBaseResponse baseRsp) {
		ComResponse<T> output = new ComResponse<T>();
		output.setTradStat(ComRspConstants.TRADSTAT_FAIL);
		output.setDataStat(ComRspConstants.dataStat_NODATA);
		output.setCode(baseRsp.getCode());
		output.setCodeDesc(baseRsp.getMsg());
		return output;
	}

	public ComResponse<T> createComErrorRsp(ComRequest request, T t) {
		ComResponse<T> output = new ComResponse<T>();
		output.setResData(t);
		this.iniErrorRsp(request, output);
		return output;
	}

	public ComResponse<T> createComErrorRsp(ComRequest request, T t, HttpBizException e) {
		ComResponse<T> output = new ComResponse<T>();
		output.setResData(t);
		this.iniErrorRsp(request, output, e);
		return output;
	}

	public ComResponse<T> iniSuccessRsp(ComRequest request, ComResponse<T> rsp) {
		rsp.setAppNo(request.getAppNo());
		rsp.setMobile(request.getMobile());
		rsp.setTradStat(ComRspConstants.TRADSTAT_SUCCESS);
		return rsp;
	}

	private ComResponse<T> iniErrorRsp(ComRequest request, ComResponse<T> rsp, HttpBizException e) {
		rsp.setAppNo(request.getAppNo());
		rsp.setMobile(request.getMobile());
		rsp.setTradStat(ComRspConstants.TRADSTAT_FAIL);
		rsp.setDataStat(ComRspConstants.dataStat_NODATA);
		if (StringUtils.isBlank(e.getCode())) {
			rsp.setCode(ComRspConstants.Code_ComError);
		} else {
			rsp.setCode(e.getCode());
		}

		rsp.setCodeDesc(e.getMessage());
		return rsp;
	}

	private ComResponse<T> iniErrorRsp(ComRequest request, ComResponse<T> rsp) {
		rsp.setAppNo(request.getAppNo());
		rsp.setMobile(request.getMobile());
		rsp.setTradStat(ComRspConstants.TRADSTAT_FAIL);
		rsp.setDataStat(ComRspConstants.dataStat_NODATA);
		rsp.setCode(ComRspConstants.Code_ComError);
		rsp.setCodeDesc(ComRspConstants.Code_ComErrorDesc);
		return rsp;
	}
}
