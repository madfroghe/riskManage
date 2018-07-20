package duomi.services.impl;

import java.util.Map;

import duomi.com.constants.MongoDbCollectionConstants;
import duomi.mongodb.dao.Impl.MongodbBaseDao2Impl;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.com.httpIvk.param.creditGuard.CreditGuardResponse;
import duomi.com.httpIvk.param.creditGuard.CreditGuardResult;
import duomi.com.httpIvk.services.TongDDataHttpService;
import duomi.com.utils.JSONUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.CreditGuardRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.CreditGuardService;
import duomi.services.OutsideServiceRegistService;

@Service
public class CreditGuardServiceImpl implements CreditGuardService {
	private final static Logger logger = LoggerFactory.getLogger(HttpIvkHelper.class);
	@Autowired
	private TongDDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<CreditGuardResult> getCreditGuard(CreditGuardRequest request) throws Exception {
		ComResponse<CreditGuardResult> output = null;

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

	private ComResponse<CreditGuardResult> getInfoFromOutside(CreditGuardRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务 BackCardThreeELementOutput
		CreditGuardResponse output = httpservice.getCreditGuard(request);
		String retMsg = JSONUtils.toJSONString(output);
		logger.debug("第三方返回信息：" + retMsg);

		CreditGuardResult result = output.getResult_desc();

		// 外部数据保存入库
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("data",output);
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_TD_CREDITGUARD);

		// 返回消息转换
		ComResponse<CreditGuardResult> rsp = new ResponseSimpleHelper<CreditGuardResult>().createSuccessRsp(request,
				result);
		String comRspStr = JSONUtils.toJSONString(rsp);
		logger.debug("返回调度平台信息：" + comRspStr);
		return rsp;
	}

	private ComResponse<CreditGuardResult> getInfoFromLocal(CreditGuardRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {
		String retMsg = cspStatPo.getRetMessage();

		logger.debug("第三方返回信息：" + retMsg);

		CreditGuardResponse output = JSONUtils.toBean(retMsg, CreditGuardResponse.class);
		CreditGuardResult result = output.getResult_desc();

		// 返回消息转换
		ComResponse<CreditGuardResult> rsp = new ResponseSimpleHelper<CreditGuardResult>().createSuccessRsp(request,
				result);
		String comRspStr = JSONUtils.toJSONString(rsp);
		logger.debug("返回调度平台信息：" + comRspStr);
		return rsp;
	}

}
