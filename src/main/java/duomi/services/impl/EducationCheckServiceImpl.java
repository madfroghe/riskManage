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
import duomi.com.httpIvk.param.education.EducationCheckResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.EducationCheckPo;
import duomi.dbMap.mapper.EducationCheckPoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.EducationCheckquest;
import duomi.dispatch.response.ComResponse;
import duomi.services.EducationCheckService;
import duomi.services.OutsideServiceRegistService;

@Service
public class EducationCheckServiceImpl implements EducationCheckService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private EducationCheckPoMapper edctDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<EducationCheckResult> CheckEducation(EducationCheckquest request) throws Exception {
		ComResponse<EducationCheckResult> output = null;

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

	private ComResponse<EducationCheckResult> getInfoFromOutside(EducationCheckquest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<EducationCheckResult> output = httpservice.checkEducation(request);

		if (output.getData() != null) {
			// 学历查询 返回数据保存入库
			EducationCheckPo edctPo = new EducationCheckPo();
			BeanUtils.copyProperties(output.getData(), edctPo);
			edctPo.setAppNo(request.getAppNo());
			edctDao.insertSelective(edctPo);
		}

		//保存到mongdb
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("data",output);
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_EDUCATION);


		// 返回消息转换
		ComResponse<EducationCheckResult> rsp = new ResponseSimpleHelper<EducationCheckResult>().ConvertRsp(request,
				output);

		return rsp;
	}

	private ComResponse<EducationCheckResult> getInfoFromLocal(EducationCheckquest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<EducationCheckResult> gson = new GsonUtils<EducationCheckResult>();
		BaseResponse<EducationCheckResult> output = (BaseResponse<EducationCheckResult>) gson.fromJson(retMsg,
				BaseResponse.class, EducationCheckResult.class);

		ComResponse<EducationCheckResult> rsp = new ResponseSimpleHelper<EducationCheckResult>().ConvertRsp(request,
				output);
		return rsp;

		/*
		 * Map<String,Object> params=new HashMap<String,Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); EducationCheckPo
		 * po=edctDao.selectByAppNo(params); EducationCheckResult result=null;
		 * if(po!=null){ result=new EducationCheckResult();
		 * BeanUtils.copyProperties(po, result); }
		 * ComResponse<EducationCheckResult>
		 * output=(ComResponse<EducationCheckResult>) new
		 * ResponseSimpleHelper<EducationCheckResult>()
		 * .createSuccessRsp(request, result);
		 * 
		 * return output;
		 */
	}

}
