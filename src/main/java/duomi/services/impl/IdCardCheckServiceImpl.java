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
import duomi.com.httpIvk.param.idcard.IdCardCheckResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.IdcardCheckPo;
import duomi.dbMap.mapper.IdcardCheckPoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.IdCardRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.IdCardCheckService;
import duomi.services.OutsideServiceRegistService;

@Service
public class IdCardCheckServiceImpl implements IdCardCheckService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private IdcardCheckPoMapper idCardDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<IdCardCheckResult> CheckIdCard(IdCardRequest request) throws Exception {
		ComResponse<IdCardCheckResult> output = null;

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

	private ComResponse<IdCardCheckResult> getInfoFromOutside(IdCardRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<IdCardCheckResult> output = httpservice.checkIdCard(request);

		if (output.getData() != null) {
			// 返回数据保存入库
			IdcardCheckPo idCardPo = new IdcardCheckPo();
			BeanUtils.copyProperties(output.getData(), idCardPo);
			idCardPo.setAppNo(request.getAppNo());
			idCardDao.insertSelective(idCardPo);
		}

		//保存信息到mongodb
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("data",output);
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_IDCARD);
		// 返回消息转换
		ComResponse<IdCardCheckResult> rsp = new ResponseSimpleHelper<IdCardCheckResult>().ConvertRsp(request, output);

		return rsp;

	}

	private ComResponse<IdCardCheckResult> getInfoFromLocal(IdCardRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<IdCardCheckResult> gson = new GsonUtils<IdCardCheckResult>();
		BaseResponse<IdCardCheckResult> output = (BaseResponse<IdCardCheckResult>) gson.fromJson(retMsg,
				BaseResponse.class, IdCardCheckResult.class);

		ComResponse<IdCardCheckResult> rsp = new ResponseSimpleHelper<IdCardCheckResult>().ConvertRsp(request, output);
		return rsp;

		/*
		 * Map<String,Object> params=new HashMap<String,Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); IdcardCheckPo
		 * po=idCardDao.selectByAppNo(params); IdCardCheckResult result=null;
		 * if(po!=null){ result=new IdCardCheckResult();
		 * BeanUtils.copyProperties(po, result); }
		 * 
		 * ComResponse<IdCardCheckResult>
		 * output=(ComResponse<IdCardCheckResult>) new
		 * ResponseSimpleHelper<IdCardCheckResult>() .createSuccessRsp(request,
		 * result);
		 * 
		 * return output;
		 */
	}

}
