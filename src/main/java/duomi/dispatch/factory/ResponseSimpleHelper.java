package duomi.dispatch.factory;

import duomi.Enums.tianyancha.TianyanchaRespCodeEnum;
import duomi.com.constants.ComRspConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.JiGResponse;
import duomi.com.httpIvk.wightknight.param.WightKnightResponse;
import duomi.com.httpIvk.yiJianCloud.param.YiJianCloudResponse;
import duomi.com.utils.JSONUtils;
import duomi.dispatch.request.ComRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseSimpleHelper<T> {

	private final static Logger logger = LoggerFactory.getLogger(HttpIvkHelper.class);

	/**
	 * 将第三方服务返回消息 转换为 返回调度消息。
	 * 
	 * @param request
	 * @param baseRsp
	 * @return
	 */
	public ComResponse<T> ConvertRsp(ComRequest request, BaseResponse<T> baseRsp) {

		String baseRspStr = JSONUtils.toJSONString(baseRsp);
		logger.debug("第三方返回信息：" + baseRspStr);

		ComResponse<T> comRsp = new ComResponse<T>();
		if (baseRsp.isSuccess()) {
			comRsp = this.createSuccessRsp(request, baseRsp.getData());
		} else {
			comRsp = this.createErrorRsp(request, baseRsp);
		}
		String comRspStr = JSONUtils.toJSONString(comRsp);
		logger.debug("返回调度平台信息：" + comRspStr);
		return comRsp;
	}

	/**
	 * 将极光服务返回消息 转换为 返回调度消息。
	 *
	 * @param request
	 * @param baseRsp
	 * @return
	 */
	public ComResponse<T> ConvertRsp(ComRequest request, JiGResponse<T> baseRsp) {

		String baseRspStr = JSONUtils.toJSONString(baseRsp);
		logger.debug("极光服务第三方返回信息：" + baseRspStr);

		ComResponse<T> comRsp = new ComResponse<T>();
		if ("2000".equals(baseRsp.getCode()) || "2001".equals(baseRsp.getCode())) {
			comRsp = this.createSuccessRsp(request, baseRsp.getData());
		} else {
			comRsp = this.createErrorJiGuangRsp(request, baseRsp);
		}
		String comRspStr = JSONUtils.toJSONString(comRsp);
		logger.debug("返回调度平台信息：" + comRspStr);
		return comRsp;
	}

    /**
	 * 将百融打包接口返回的消息 转换为 返回调度消息
	 * */
	public ComResponse<T> ConvertRsp(ComRequest request , JSONObject brPackageRsp){
		String baseRspStr = JSONUtils.toJSONString(brPackageRsp);
		logger.debug("百融第三方返回信息：" + baseRspStr);

		ComResponse<T> comRsp = new ComResponse<T>();
		if("00".equals(brPackageRsp.getString("code")) || "100002".equals(brPackageRsp.getString("code"))){
			comRsp = this.createBaiRongSuccessRsp(request,(T)brPackageRsp);
		} else {
			comRsp = this.createErrorBrRsp(request,brPackageRsp);
		}

		String comRspStr = JSONUtils.toJSONString(comRsp);
		logger.debug("返回调度平台信息: "+ comRspStr);
		return comRsp;
	}
	public ComResponse<T> BizConvertRsp(ComRequest request,JSONObject brPackageRsp) {
		String baseRspStr = JSONUtils.toJSONString(brPackageRsp);
		logger.debug("百融第三方返回信息：" + baseRspStr);
		ComResponse<T> comRsp;
		if("20000".equals(brPackageRsp.getString("Code"))&& brPackageRsp.has("Result")){
			comRsp = this.createBaiRongSuccessRsp(request,(T)brPackageRsp);
			String comRspStr = JSONUtils.toJSONString(comRsp);
			logger.debug("返回调度平台信息: "+ comRspStr);
			return comRsp;
		}else if("20000".equals(brPackageRsp.getString("Code"))&& !brPackageRsp.has("Result")){
			comRsp = this.createBaiRongSuccessRsp(request,(T)brPackageRsp);
			String comRspStr = JSONUtils.toJSONString(comRsp);
			logger.debug("返回调度平台信息: "+ comRspStr);
			return comRsp;
		}else{
			comRsp = this.createErrorBrRsp(request,brPackageRsp);
			String comRspStr = JSONUtils.toJSONString(comRsp);
			logger.debug("返回调度平台信息: "+ comRspStr);
			return comRsp;
		}
	}

	/**
	 * 将易简云返回消息转换为 返回调度消息
	 * */
	public ComResponse<T> ConvertRsp(ComRequest request, YiJianCloudResponse<T> yjCloudRsp){
		String baseRspStr = JSONUtils.toJSONString(yjCloudRsp);
		logger.debug("易简云第三方返回信息：" + baseRspStr);

		ComResponse<T> comRsp = new ComResponse<T>();
		if ("0".equals(yjCloudRsp.getResult())) {
			comRsp = this.createSuccessRsp(request, yjCloudRsp.getData());
		} else {
			comRsp = this.createErrorYJCloudRsp(request, yjCloudRsp);
		}
		String comRspStr = JSONUtils.toJSONString(comRsp);
		logger.debug("返回调度平台信息：" + comRspStr);
		return comRsp;
	}

	/**
	 * 将白骑士接口返回的信息 转换为 返回调度信息
	 * */
	public ComResponse<T> ConvertRsp(ComRequest request, WightKnightResponse<T> wkRsp){
		String baseRspStr = JSONUtils.toJSONString(wkRsp);
		logger.debug("白骑士接口第三方返回信息：" + baseRspStr);

		ComResponse<T> comRsp = new ComResponse<T>();

		if ("BQS000".equals(wkRsp.getResultCode())){
			//成功
			if(wkRsp.getStrategySet() != null){
				comRsp = this.createSuccessRsp(request,wkRsp.getStrategySet());
			}else if(wkRsp.getResultData() != null){
				comRsp = this.createSuccessRsp(request,(T)wkRsp.getResultData());
			}

		}else{
			//失败
			comRsp = this.createErrorWkRsp(request,wkRsp);
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

	/**百融创建正确返回*/
	public ComResponse<T> createBaiRongSuccessRsp(ComRequest request,T json){
		ComResponse<T> output = new ComResponse<T>();
		output.setResData(json);
		output.setAppNo(request.getAppNo());
		output.setMobile(request.getMobile());
		output.setTradStat(ComRspConstants.TRADSTAT_SUCCESS);

		return output;
	}


	public ComResponse<T> createErrorRsp(ComRequest request, BaseResponse baseRsp) {
		ComResponse<T> output = new ComResponse<T>();
		output.setTradStat(ComRspConstants.TRADSTAT_FAIL);
		output.setDataStat(ComRspConstants.dataStat_NODATA);
		output.setCode(baseRsp.getCode());
		output.setCodeDesc(baseRsp.getErrorDesc());
		return output;
	}
	/**
	 * 极光接口创建错误返回
	 * */
	public ComResponse<T> createErrorJiGuangRsp(ComRequest request , JiGResponse baseRsp){
		ComResponse<T> output = new ComResponse<T>();
		output.setTradStat(ComRspConstants.TRADSTAT_FAIL);
		output.setDataStat(ComRspConstants.dataStat_NODATA);
		output.setCode(baseRsp.getCode());
		output.setCodeDesc(baseRsp.getMessage());
		return output;
	}

	/**
	 * 白骑士接口创建错误返回
	 * */
	public ComResponse<T> createErrorWkRsp(ComRequest request , WightKnightResponse wkRsp){
		ComResponse<T> output = new ComResponse<T>();
		output.setTradStat(ComRspConstants.TRADSTAT_FAIL);
		output.setDataStat(ComRspConstants.dataStat_NODATA);
		output.setCode(wkRsp.getResultCode());
		output.setCodeDesc(wkRsp.getResultDesc());
		return output;
	}

	/**
	 * 易简云接口创建错误返回
	 * */
	public ComResponse<T> createErrorYJCloudRsp(ComRequest request , YiJianCloudResponse yjCloudRsp){
		ComResponse<T> output = new ComResponse<T>();
		output.setTradStat(ComRspConstants.TRADSTAT_FAIL);
		output.setDataStat(ComRspConstants.dataStat_NODATA);
		output.setCode(yjCloudRsp.getResult());
		output.setCodeDesc(yjCloudRsp.getMessage());
		return output;
	}

	/**
	 * 百融接口创建错误返回
	 * */
	public ComResponse<T> createErrorBrRsp(ComRequest request , JSONObject brRsp){
		ComResponse<T> output = new ComResponse<T>();
		output.setTradStat(ComRspConstants.TRADSTAT_FAIL);
		output.setDataStat(ComRspConstants.dataStat_NODATA);
		output.setCode(brRsp.getString("Code"));
		output.setCodeDesc("参看接口说明");
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

	private ComResponse<T> iniErrorRsp(ComRequest request, ComResponse<T> rsp) {
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

	/**
	 * 天眼查解析返回值
	 * @param request 请求通用参数对象
	 * @param respStr 返回报文
	 * @return 通用的返回结果对象
	 */
	public static ComResponse tycConvertRsp(ComRequest request,String respStr) {
		logger.info("开始处理天眼查返回报文{}" + respStr);
		ComResponse response = new ComResponse();
		//将返回报文转为json对象
		com.alibaba.fastjson.JSONObject respJson = com.alibaba.fastjson.JSONObject.parseObject(respStr);

		//解析json对象，获取码值和描述信息
		String code = respJson.getString("error_code");
		String msg = respJson.getString("reason");
		com.alibaba.fastjson.JSONObject result = respJson.getJSONObject("result");

		//请求成功
		if(TianyanchaRespCodeEnum.SUCC.getCode().equals(code) ){
			//返回了数据,处理状态为‘成功’
			if(result != null && result.size() > 0){
				response.setResData(result);
				response.setTradStat(ComRspConstants.TRADSTAT_SUCCESS);
			}else{//没有返回数据
				response.setTradStat(ComRspConstants.TRADSTAT_FAIL);
				response.setDataStat(ComRspConstants.dataStat_NODATA);
				msg = "请求成功但是返回数据为空,天眼查返回数据异常";
			}

		//返回的结果为"无数据",处理状态为‘成功’
		}else if(TianyanchaRespCodeEnum.NO_DATA.getCode().equals(code)){
			response.setTradStat(ComRspConstants.TRADSTAT_SUCCESS);
			//其它为失败
		}else{
			response.setTradStat(ComRspConstants.TRADSTAT_FAIL);
			response.setDataStat(ComRspConstants.dataStat_NODATA);
		}
		response.setAppNo(request.getAppNo());
		response.setMobile(request.getMobile());
		response.setCode(code);
		response.setCodeDesc(StringUtils.isEmpty(msg) ? TianyanchaRespCodeEnum.getCodeDesc(code) : msg);
		logger.info("处理天眼查返回报文结束，处理结果{}" + com.alibaba.fastjson.JSONObject.toJSONString(response));

		return response;
	}
}
