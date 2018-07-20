package duomi.services.impl;

import java.util.Map;

import duomi.com.constants.MongoDbCollectionConstants;
import duomi.com.utils.JSONUtils;
import duomi.mongodb.dao.Impl.MongodbBaseDao2Impl;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.bankCard.BackCardFourELementResult;
import duomi.com.httpIvk.param.bankCard.BackCardThreeELementResult;
import duomi.com.httpIvk.param.bankCard.BankCardTradeResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.BankcardFourPo;
import duomi.dbMap.bean.BankcardThreePo;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.mapper.BankcardFourPoMapper;
import duomi.dbMap.mapper.BankcardThreePoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.BackCard3ERequest;
import duomi.dispatch.request.BackCard4ERequest;
import duomi.dispatch.request.BankCardTradeRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.BankCardService;
import duomi.services.OutsideServiceRegistService;

@Service
public class BankCardServiceImpl implements BankCardService {
	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private BankcardThreePoMapper bankCard3Edao;

	@Autowired
	private BankcardFourPoMapper bankCard4Edao;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject bank3StaticJSON = new JSONObject();
	public static JSONObject bank4StaticJSON = new JSONObject();
	public static JSONObject bankInfoStaticJSON = new JSONObject();

	/**
	 * 银行卡三要素验证
	 * 
	 * @throws Exception
	 */
	public ComResponse<BackCardThreeELementResult> CheckBankCard3Element(BackCard3ERequest request) throws Exception {
		ComResponse<BackCardThreeELementResult> output = null;

		Map<String, Object> retMap = regitSrv.isRequested(request);
		boolean isQuested = (Boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
		if (isQuested) {
			CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
			output = this.getInfoFromLocal(request, cspStatPo);
		} else {
			output = this.getInfoFromOutside(request);
		}

		return output;
	}

	/**
	 * 银行卡四要素验证
	 * 
	 * @throws Exception
	 */
	public ComResponse<BackCardFourELementResult> CheckBankCard4Element(BackCard4ERequest request) throws Exception {
		ComResponse<BackCardFourELementResult> output = null;
		Map<String, Object> retMap = regitSrv.isRequested(request);
		boolean isQuested = (Boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
		if (isQuested) {
			CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
			output = this.getFourInfoFromLocal(request, cspStatPo);
		} else {
			output = this.getFourInfoFromOutside(request);
		}

		return output;
	}

	/**
	 * 发送第三方服务获取相关数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ComResponse<BackCardFourELementResult> getFourInfoFromOutside(BackCard4ERequest request) throws Exception {

		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<BackCardFourELementResult> output = httpservice.CheckBankCard4Element(request);

		// 银行卡四要素验证 返回数据保存入库
		if (output.getData() != null) {
			BankcardFourPo bankcardpo = new BankcardFourPo();
			BeanUtils.copyProperties(output.getData(), bankcardpo);
			bankcardpo.setAppNo(request.getAppNo());
			bankCard4Edao.insertSelective(bankcardpo);
		}

		// 银行卡四要素保存入mongdb
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("data",output);
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",bank4StaticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_BANKCARD_4E);


		// 返回消息转换
		ComResponse<BackCardFourELementResult> rsp = new ResponseSimpleHelper<BackCardFourELementResult>()
				.ConvertRsp(request, output);

		return rsp;

	}

	/**
	 * 从本地数据库获取相关信息
	 * 
	 * @param request
	 * @return
	 */
	private ComResponse<BackCardFourELementResult> getFourInfoFromLocal(BackCard4ERequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		/*
		 * Map<String, Object> params = new HashMap<String, Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); BankcardFourPo po =
		 * bankCard4Edao.selectByAppNo(params); BackCardFourELementResult result
		 * = null; if (po != null) { result = new BackCardFourELementResult();
		 * BeanUtils.copyProperties(po, result); }
		 * 
		 * ComResponse<BackCardFourELementResult> output =
		 * (ComResponse<BackCardFourELementResult>) new
		 * ResponseSimpleHelper<BackCardFourELementResult>()
		 * .createSuccessRsp(request, result); return output;
		 */

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<BackCardFourELementResult> gson = new GsonUtils<BackCardFourELementResult>();
		BaseResponse<BackCardFourELementResult> output = (BaseResponse<BackCardFourELementResult>) gson.fromJson(retMsg,
				BaseResponse.class, BackCardFourELementResult.class);

		ComResponse<BackCardFourELementResult> rsp = new ResponseSimpleHelper<BackCardFourELementResult>()
				.ConvertRsp(request, output);
		return rsp;

	}

	/**
	 * 发送第三方服务获取相关数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ComResponse<BackCardThreeELementResult> getInfoFromOutside(BackCard3ERequest request) throws Exception {

		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务 BackCardThreeELementOutput
		BaseResponse<BackCardThreeELementResult> output = httpservice.CheckBankCard3Element(request);

		// 银行卡三要素验证 返回数据保存入库
		if (output.getData() != null) {
			BankcardThreePo bankcardpo = new BankcardThreePo();
			BeanUtils.copyProperties(output.getData(), bankcardpo);
			bankcardpo.setAppNo(request.getAppNo());
			bankCard3Edao.insertSelective(bankcardpo);
		}

		// 银行卡三要素验证 返回数据保存入mongdb
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("data",output);
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",bank3StaticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_ZB_BANKCARD_3E);

		// 返回消息转换
		ComResponse<BackCardThreeELementResult> rsp = new ResponseSimpleHelper<BackCardThreeELementResult>()
				.ConvertRsp(request, output);

		return rsp;
	}

	/**
	 * 从本地数据库获取相关信息
	 * 
	 * @param request
	 * @return
	 */
	private ComResponse<BackCardThreeELementResult> getInfoFromLocal(BackCard3ERequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		/*
		 * Map<String, Object> params = new HashMap<String, Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); BankcardThreePo po =
		 * bankCard3Edao.selectByAppNo(params); BackCardThreeELementResult
		 * result = null; if (po != null) { result = new
		 * BackCardThreeELementResult(); BeanUtils.copyProperties(po, result); }
		 * 
		 * ComResponse<BackCardThreeELementResult> output = new
		 * ResponseSimpleHelper<BackCardThreeELementResult>()
		 * .createSuccessRsp(request, result);
		 * 
		 * return output;
		 */

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<BackCardThreeELementResult> gson = new GsonUtils<BackCardThreeELementResult>();
		BaseResponse<BackCardThreeELementResult> output = (BaseResponse<BackCardThreeELementResult>) gson
				.fromJson(retMsg, BaseResponse.class, BackCardThreeELementResult.class);

		ComResponse<BackCardThreeELementResult> rsp = new ResponseSimpleHelper<BackCardThreeELementResult>()
				.ConvertRsp(request, output);
		return rsp;
	}

	// 获取银行卡信息
	public ComResponse<BankCardTradeResult> getBackCardTrade(BankCardTradeRequest request) throws Exception {

		ComResponse<BankCardTradeResult> output = null;
		Map<String, Object> retMap = regitSrv.isRequested(request);
		boolean isQuested = (Boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
		if (isQuested) {
			CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
			output = this.getFourInfoFromLocal(request, cspStatPo);
		} else {
			output = this.getFourInfoFromOutside(request);
		}

		return output;

	}

	/**
	 * 从第三方获取银行卡信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ComResponse<BankCardTradeResult> getFourInfoFromOutside(BankCardTradeRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务 BackCardThreeELementOutput
		BaseResponse<BankCardTradeResult> output = httpservice.getBankCardTrade(request);

		/*
		 * if (output.getData() != null) { BankcardThreePo bankcardpo = new
		 * BankcardThreePo(); BeanUtils.copyProperties(output.getData(),
		 * bankcardpo); bankcardpo.setAppNo(request.getAppNo());
		 * bankCard3Edao.insertSelective(bankcardpo); }
		 */

		// 银行卡信息保存入mongdb
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("data",output);
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",bankInfoStaticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_BACKCARDTRADE);

		// 返回消息转换
		ComResponse<BankCardTradeResult> rsp = new ResponseSimpleHelper<BankCardTradeResult>().ConvertRsp(request,
				output);

		return rsp;
	}

	/**
	 * 从本地获取银行卡信息
	 * 
	 * @param request
	 * @param cspStatPo
	 * @return
	 */
	private ComResponse<BankCardTradeResult> getFourInfoFromLocal(BankCardTradeRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<BankCardTradeResult> gson = new GsonUtils<BankCardTradeResult>();
		BaseResponse<BankCardTradeResult> output = (BaseResponse<BankCardTradeResult>) gson.fromJson(retMsg,
				BaseResponse.class, BankCardTradeResult.class);

		ComResponse<BankCardTradeResult> rsp = new ResponseSimpleHelper<BankCardTradeResult>().ConvertRsp(request,
				output);

		return rsp;
	}

}
