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
import duomi.com.httpIvk.param.badrecords.BadrecordResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.mapper.BadrecordCheckPoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.BadrecordRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.BadRecordService;
import duomi.services.OutsideServiceRegistService;

@Service
public class BadRecordServiceImpl implements BadRecordService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private BadrecordCheckPoMapper badRecordDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<BadrecordResult> CheckBadrecord(BadrecordRequest request) throws Exception {
		ComResponse<BadrecordResult> output = null;

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

	private ComResponse<BadrecordResult> getInfoFromOutside(BadrecordRequest request) throws Exception {

		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<BadrecordResult> output = httpservice.checkBadrecord(request);

		// 不良信息查询验证 返回数据保存入库
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("data",output);
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_BADRECORD);

		// 返回消息转换
		ComResponse<BadrecordResult> rsp = new ResponseSimpleHelper<BadrecordResult>().ConvertRsp(request, output);

		return rsp;

	}

	private ComResponse<BadrecordResult> getInfoFromLocal(BadrecordRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		/*
		 * Map<String, Object> params = new HashMap<String, Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); BadrecordCheckPo po =
		 * badRecordDao.selectByAppNo(params); BadrecordResult result = null; if
		 * (po != null) { result = new BadrecordResult();
		 * BeanUtils.copyProperties(po, result); } ComResponse<BadrecordResult>
		 * output = (ComResponse<BadrecordResult>) new
		 * ResponseSimpleHelper<BadrecordResult>() .createSuccessRsp(request,
		 * result); return output;
		 */

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<BadrecordResult> gson = new GsonUtils<BadrecordResult>();
		BaseResponse<BadrecordResult> output = (BaseResponse<BadrecordResult>) gson.fromJson(retMsg, BaseResponse.class,
				BadrecordResult.class);

		ComResponse<BadrecordResult> rsp = new ResponseSimpleHelper<BadrecordResult>().ConvertRsp(request, output);
		return rsp;

	}

}
