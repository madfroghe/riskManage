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
import duomi.com.httpIvk.param.register.CensusRegisterResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CensusRegisterPo;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.mapper.CensusRegisterPoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.CensusRegisterRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.CensusRegisterService;
import duomi.services.OutsideServiceRegistService;

@Service
public class CensusRegisterServiceImpl implements CensusRegisterService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;
	@Autowired
	private CensusRegisterPoMapper crDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<CensusRegisterResult> getCensusRegister(CensusRegisterRequest request) throws Exception {
		ComResponse<CensusRegisterResult> output = null;

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

	private ComResponse<CensusRegisterResult> getInfoFromOutside(CensusRegisterRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);
		// 发送外部服务
		BaseResponse<CensusRegisterResult> output = httpservice.getCensusRegister(request);

		// 信用评分 返回数据保存入库
		if (output.getData() != null) {
			CensusRegisterPo csPo = new CensusRegisterPo();
			BeanUtils.copyProperties(output.getData(), csPo);
			// csPo.setAppNo(request.getAppNo());
			csPo.setMobile(request.getMobile());
			crDao.insertSelective(csPo);
		}

		//保存进mongdb
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("data",output);
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_CENSUSREGISTER);

		// 返回消息转换
		ComResponse<CensusRegisterResult> rsp = new ResponseSimpleHelper<CensusRegisterResult>().ConvertRsp(request,
				output);

		return rsp;
	}

	private ComResponse<CensusRegisterResult> getInfoFromLocal(CensusRegisterRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<CensusRegisterResult> gson = new GsonUtils<CensusRegisterResult>();
		BaseResponse<CensusRegisterResult> output = (BaseResponse<CensusRegisterResult>) gson.fromJson(retMsg,
				BaseResponse.class, CensusRegisterResult.class);

		ComResponse<CensusRegisterResult> rsp = new ResponseSimpleHelper<CensusRegisterResult>().ConvertRsp(request,
				output);
		return rsp;

		/*
		 * Map<String, Object> params = new HashMap<String, Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); CensusRegisterPo po =
		 * crDao.selectByAppNo(params); CensusRegisterResult result = null; if
		 * (po != null) { result = new CensusRegisterResult();
		 * BeanUtils.copyProperties(po, result); }
		 * 
		 * ComResponse<CensusRegisterResult> output =
		 * (ComResponse<CensusRegisterResult>) new
		 * ResponseSimpleHelper<CensusRegisterResult>()
		 * .createSuccessRsp(request, result);
		 * 
		 * return output;
		 */
	}

}
