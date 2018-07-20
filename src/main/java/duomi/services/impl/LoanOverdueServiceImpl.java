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
import duomi.com.httpIvk.param.overdue.LoanOverdueResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.LoanOverdueRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.LoanOverdueService;
import duomi.services.OutsideServiceRegistService;

@Service
public class LoanOverdueServiceImpl implements LoanOverdueService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<LoanOverdueResult> getLoanOverdue(LoanOverdueRequest request) throws Exception {
		ComResponse<LoanOverdueResult> output = null;

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

	private ComResponse<LoanOverdueResult> getInfoFromOutside(LoanOverdueRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务 BackCardThreeELementOutput
		BaseResponse<LoanOverdueResult> output = httpservice.getLoanOverdue(request);

		// 外部数据保存入库
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("data",output);
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_LOANOVERDUE);

		// 返回消息转换
		ComResponse<LoanOverdueResult> rsp = new ResponseSimpleHelper<LoanOverdueResult>().ConvertRsp(request, output);

		return rsp;
	}

	private ComResponse<LoanOverdueResult> getInfoFromLocal(LoanOverdueRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {
		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<LoanOverdueResult> gson = new GsonUtils<LoanOverdueResult>();
		BaseResponse<LoanOverdueResult> output = (BaseResponse<LoanOverdueResult>) gson.fromJson(retMsg,
				BaseResponse.class, LoanOverdueResult.class);

		ComResponse<LoanOverdueResult> rsp = new ResponseSimpleHelper<LoanOverdueResult>().ConvertRsp(request, output);
		return rsp;
	}

}
