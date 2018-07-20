package duomi.dispatch.factory;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import duomi.com.constants.ComRspConstants;
import duomi.com.constants.HouseRspConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.com.httpIvk.house.param.HouseListResponse;
import duomi.com.utils.JSONUtils;
import duomi.dispatch.request.ComRequest;
import duomi.dispatch.response.ComListResponse;

public class HsRspListSimpleHelper<T> {

	private final static Logger logger = LoggerFactory.getLogger(HttpIvkHelper.class);

	/**
	 * 将第三方服务返回消息 转换为 返回调度消息。
	 * 
	 * @param request
	 * @param baseRsp
	 * @return
	 */
	public ComListResponse<T> ConvertRsp(ComRequest request, HouseListResponse<T> baseRsp) {

		String baseRspStr = JSONUtils.toJSONString(baseRsp);
		logger.debug("第三方返回信息：" + baseRspStr);

		ComListResponse<T> comRsp = new ComListResponse<T>();
		if (HouseRspConstants.CODE_SUCCESS.equals(baseRsp.getCode())) {
			comRsp = this.createSuccessRsp(request, baseRsp.getResult());
		} else {
			comRsp = this.createErrorRsp(request, baseRsp);
		}
		String comRspStr = JSONUtils.toJSONString(comRsp);
		logger.debug("返回调度平台信息：" + comRspStr);
		return comRsp;
	}

	public ComListResponse<T> createSuccessRsp(ComRequest request, List<T> t) {
		ComListResponse<T> output = new ComListResponse<T>();
		output.setResData(t);
		this.iniSuccessRsp(request, output);
		return output;
	}

	public ComListResponse<T> createErrorRsp(ComRequest request, HouseListResponse baseRsp) {
		ComListResponse<T> output = new ComListResponse<T>();
		output.setTradStat(ComRspConstants.TRADSTAT_FAIL);
		output.setDataStat(ComRspConstants.dataStat_NODATA);
		output.setCode(baseRsp.getCode());
		output.setCodeDesc(baseRsp.getMsg());
		return output;
	}

	public ComListResponse<T> createComErrorRsp(ComRequest request, List<T> t) {
		ComListResponse<T> output = new ComListResponse<T>();
		output.setResData(t);
		this.iniErrorRsp(request, output);
		return output;
	}

	public ComListResponse<T> createComErrorRsp(ComRequest request, List<T> t, HttpBizException e) {
		ComListResponse<T> output = new ComListResponse<T>();
		output.setResData(t);
		this.iniErrorRsp(request, output, e);
		return output;
	}

	private ComListResponse<T> iniSuccessRsp(ComRequest request, ComListResponse<T> rsp) {
		if (request != null) {
			rsp.setAppNo(request.getAppNo());
			rsp.setMobile(request.getMobile());
		}
		rsp.setTradStat(ComRspConstants.TRADSTAT_SUCCESS);
		return rsp;
	}

	private ComListResponse<T> iniErrorRsp(ComRequest request, ComListResponse<T> rsp, HttpBizException e) {
		if (request != null) {
			rsp.setAppNo(request.getAppNo());
			rsp.setMobile(request.getMobile());
		}

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

	private ComListResponse<T> iniErrorRsp(ComRequest request, ComListResponse<T> rsp) {
		if (request != null) {
			rsp.setAppNo(request.getAppNo());
			rsp.setMobile(request.getMobile());
		}
		rsp.setTradStat(ComRspConstants.TRADSTAT_FAIL);
		rsp.setDataStat(ComRspConstants.dataStat_NODATA);
		rsp.setCode(ComRspConstants.Code_ComError);
		rsp.setCodeDesc(ComRspConstants.Code_ComErrorDesc);
		return rsp;
	}
}
