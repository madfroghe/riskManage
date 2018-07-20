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
import duomi.com.httpIvk.param.phone.MobileStatusResult;
import duomi.com.httpIvk.param.phone.PhoneCheckResult;
import duomi.com.httpIvk.param.phone.PhoneOnlineDurationResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.MobileCheckPo;
import duomi.dbMap.bean.MobileStatusPo;
import duomi.dbMap.bean.PhoneOnlineDurationPo;
import duomi.dbMap.mapper.MobileCheckPoMapper;
import duomi.dbMap.mapper.MobileStatusPoMapper;
import duomi.dbMap.mapper.PhoneOnlineDurationPoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.Mobile3ERequest;
import duomi.dispatch.request.MobileStatusRequest;
import duomi.dispatch.request.PhoneOnlineDurationRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.MobileCheckService;
import duomi.services.OutsideServiceRegistService;

@Service
public class MobileCheckServiceImpl implements MobileCheckService {
	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private MobileCheckPoMapper mapperdao;

	@Autowired
	private PhoneOnlineDurationPoMapper onlinedao;

	@Autowired
	private MobileStatusPoMapper statusDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject mobile3EstaticJSON = new JSONObject();
	public static JSONObject onlineduraStaticJSON = new JSONObject();
	public static JSONObject mobileStatuStaticJSON = new JSONObject();

	public ComResponse<PhoneCheckResult> checkMobileBy3E(Mobile3ERequest request) throws Exception {
		ComResponse<PhoneCheckResult> output = null;
		Map<String, Object> retMap = regitSrv.isRequested(request);
		boolean isQuested = (Boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
		if (isQuested) {
			CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
			output = this.getInfoFromLocal4Check(request, cspStatPo);
		} else {
			output = this.getInfoFromOutside4Check(request);
		}
		return output;
	}

	private ComResponse<PhoneCheckResult> getInfoFromOutside4Check(Mobile3ERequest request) throws Exception {

		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<PhoneCheckResult> output = httpservice.checkPhone(request);

		// 银行卡三要素验证 返回数据保存入库
		if (output.getData() != null) {
			MobileCheckPo dbpo = new MobileCheckPo();
			BeanUtils.copyProperties(output.getData(), dbpo);
			dbpo.setAppNo(request.getAppNo());
			mapperdao.insertSelective(dbpo);
		}

		//保存进mongodb
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("data",output);
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",mobile3EstaticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_MOBILE_3E);

		ComResponse<PhoneCheckResult> rsp = new ResponseSimpleHelper<PhoneCheckResult>().ConvertRsp(request, output);

		return rsp;

	}

	private ComResponse<PhoneCheckResult> getInfoFromLocal4Check(Mobile3ERequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<PhoneCheckResult> gson = new GsonUtils<PhoneCheckResult>();
		BaseResponse<PhoneCheckResult> output = (BaseResponse<PhoneCheckResult>) gson.fromJson(retMsg,
				BaseResponse.class, PhoneCheckResult.class);

		ComResponse<PhoneCheckResult> rsp = new ResponseSimpleHelper<PhoneCheckResult>().ConvertRsp(request, output);
		return rsp;

		/*
		 * Map<String,Object> params=new HashMap<String,Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); MobileCheckPo
		 * po=mapperdao.selectByAppNo(params); PhoneCheckResult result=null;
		 * if(po!=null){ result=new PhoneCheckResult();
		 * BeanUtils.copyProperties(po, result); }
		 * 
		 * ComResponse<PhoneCheckResult> output=(ComResponse<PhoneCheckResult>)
		 * new ResponseSimpleHelper<PhoneCheckResult>()
		 * .createSuccessRsp(request, result);
		 * 
		 * return output;
		 */

	}

	// 手机号在网时长查询(三大运营商)
	public ComResponse<PhoneOnlineDurationResult> getOnlineDuration(PhoneOnlineDurationRequest request)
			throws Exception {
		ComResponse<PhoneOnlineDurationResult> output = null;
		Map<String, Object> retMap = regitSrv.isRequested(request);
		boolean isQuested = (Boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
		if (isQuested) {
			CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
			output = this.getInfoFromLocal4online(request, cspStatPo);
		} else {
			output = this.getInfoFromOutside4online(request);
		}
		return output;
	}

	private ComResponse<PhoneOnlineDurationResult> getInfoFromOutside4online(PhoneOnlineDurationRequest request)
			throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<PhoneOnlineDurationResult> output = httpservice.getPhoneOnlineDuration(request);

		// 手机号在网时长查询 返回数据保存入库
		if (output.getData() != null) {
			PhoneOnlineDurationPo dbpo = new PhoneOnlineDurationPo();
			BeanUtils.copyProperties(output.getData(), dbpo);
			dbpo.setAppNo(request.getAppNo());
			onlinedao.insertSelective(dbpo);
		}
		//保存进mongodb
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobiel",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("data",output);
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",onlineduraStaticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_ONLINEDURATION);

		ComResponse<PhoneOnlineDurationResult> rsp = new ResponseSimpleHelper<PhoneOnlineDurationResult>()
				.ConvertRsp(request, output);

		return rsp;
	}

	private ComResponse<PhoneOnlineDurationResult> getInfoFromLocal4online(PhoneOnlineDurationRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<PhoneOnlineDurationResult> gson = new GsonUtils<PhoneOnlineDurationResult>();
		BaseResponse<PhoneOnlineDurationResult> output = (BaseResponse<PhoneOnlineDurationResult>) gson.fromJson(retMsg,
				BaseResponse.class, PhoneOnlineDurationResult.class);

		ComResponse<PhoneOnlineDurationResult> rsp = new ResponseSimpleHelper<PhoneOnlineDurationResult>()
				.ConvertRsp(request, output);
		return rsp;

		/*
		 * Map<String, Object> params = new HashMap<String, Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); PhoneOnlineDurationPo po =
		 * onlinedao.selectByAppNo(params); PhoneOnlineDurationResult result =
		 * null; if (po != null) { result = new PhoneOnlineDurationResult();
		 * BeanUtils.copyProperties(po, result); }
		 * 
		 * ComResponse<PhoneOnlineDurationResult> output =
		 * (ComResponse<PhoneOnlineDurationResult>) new
		 * ResponseSimpleHelper<PhoneOnlineDurationResult>()
		 * .createSuccessRsp(request, result);
		 * 
		 * return output;
		 */
	}

	// 手机号状态查询(三大运营商)
	public ComResponse<MobileStatusResult> getMobileStatus(MobileStatusRequest request) throws Exception {
		ComResponse<MobileStatusResult> output = null;
		Map<String, Object> retMap = regitSrv.isRequested(request);
		boolean isQuested = (Boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
		if (isQuested) {
			CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
			output = this.getInfoFromLocal4status(request, cspStatPo);
		} else {
			output = this.getInfoFromOutside4status(request);
		}
		return output;
	}

	private ComResponse<MobileStatusResult> getInfoFromOutside4status(MobileStatusRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<MobileStatusResult> output = httpservice.getMobileStatus(request);

		// 手机号状态查询(三大运营商) 保存入库
		if (output.getData() != null) {
			MobileStatusPo dbpo = new MobileStatusPo();
			BeanUtils.copyProperties(output.getData(), dbpo);
			dbpo.setAppNo(request.getAppNo());
			statusDao.insertSelective(dbpo);
		}

		//保存进mongdb
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobiel",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("data",output);
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",mobileStatuStaticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_MOBILESTATUS);

		ComResponse<MobileStatusResult> rsp = new ResponseSimpleHelper<MobileStatusResult>().ConvertRsp(request,
				output);

		return rsp;
	}

	private ComResponse<MobileStatusResult> getInfoFromLocal4status(MobileStatusRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<MobileStatusResult> gson = new GsonUtils<MobileStatusResult>();
		BaseResponse<MobileStatusResult> output = (BaseResponse<MobileStatusResult>) gson.fromJson(retMsg,
				BaseResponse.class, MobileStatusResult.class);

		ComResponse<MobileStatusResult> rsp = new ResponseSimpleHelper<MobileStatusResult>().ConvertRsp(request,
				output);
		return rsp;

		/*
		 * Map<String, Object> params = new HashMap<String, Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); MobileStatusPo po =
		 * statusDao.selectByAppNo(params); MobileStatusResult result = null; if
		 * (po != null) { result = new MobileStatusResult();
		 * BeanUtils.copyProperties(po, result); }
		 * 
		 * ComResponse<MobileStatusResult> output =
		 * (ComResponse<MobileStatusResult>) new
		 * ResponseSimpleHelper<MobileStatusResult>() .createSuccessRsp(request,
		 * result);
		 * 
		 * return output;
		 */
	}

}
