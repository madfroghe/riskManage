package duomi.services.impl;

import java.util.Map;

import duomi.com.constants.MongoDbCollectionConstants;
import duomi.com.utils.JSONUtils;
import duomi.mongodb.dao.Impl.MongodbBaseDao2Impl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.travel.FlightInfoResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.FlightInfoRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.FlightInfoServcie;
import duomi.services.OutsideServiceRegistService;

@Service
public class FlightInfoServcieImpl implements FlightInfoServcie {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<FlightInfoResult> getFlightInfo(FlightInfoRequest request) throws Exception {
		ComResponse<FlightInfoResult> output = null;
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

	private ComResponse<FlightInfoResult> getFourInfoFromOutside(FlightInfoRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务 BackCardThreeELementOutput
		BaseResponse<FlightInfoResult> output = httpservice.getFlightInfo(request);

		// 外部数据保存入库
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("data",output);
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_FLIGHTINFO);
		// 返回消息转换
		ComResponse<FlightInfoResult> rsp = new ResponseSimpleHelper<FlightInfoResult>().ConvertRsp(request, output);

		return rsp;
	}

	private ComResponse<FlightInfoResult> getFourInfoFromLocal(FlightInfoRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {
		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<FlightInfoResult> gson = new GsonUtils<FlightInfoResult>();
		BaseResponse<FlightInfoResult> output = (BaseResponse<FlightInfoResult>) gson.fromJson(retMsg,
				BaseResponse.class, FlightInfoResult.class);

		ComResponse<FlightInfoResult> rsp = new ResponseSimpleHelper<FlightInfoResult>().ConvertRsp(request, output);
		return rsp;
	}

}
