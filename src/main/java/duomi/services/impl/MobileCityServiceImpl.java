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
import duomi.com.httpIvk.param.phone.MobileMostCityResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.MobileMostcityPo;
import duomi.dbMap.mapper.MobileMostcityPoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.MobileMostCityRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.MobileCityService;
import duomi.services.OutsideServiceRegistService;

@Service
public class MobileCityServiceImpl implements MobileCityService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private MobileMostcityPoMapper mmcDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<MobileMostCityResult> CheckMobileCity(MobileMostCityRequest request) throws Exception {
		ComResponse<MobileMostCityResult> output = null;
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

	private ComResponse<MobileMostCityResult> getInfoFromOutside4Check(MobileMostCityRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<MobileMostCityResult> output = httpservice.getMobileCityResult(request);

		if (output.getData() != null) {
			// 手机号指定月通话天数最多的城市
			MobileMostcityPo dbpo = new MobileMostcityPo();
			BeanUtils.copyProperties(output.getData(), dbpo);
			dbpo.setAppNo(request.getAppNo());
			mmcDao.insertSelective(dbpo);
		}
		//保存到mongodb

		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("data",output);
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_MOBILEMOSTCITY);

		ComResponse<MobileMostCityResult> rsp = new ResponseSimpleHelper<MobileMostCityResult>().ConvertRsp(request,
				output);

		return rsp;
	}

	private ComResponse<MobileMostCityResult> getInfoFromLocal4Check(MobileMostCityRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<MobileMostCityResult> gson = new GsonUtils<MobileMostCityResult>();
		BaseResponse<MobileMostCityResult> output = (BaseResponse<MobileMostCityResult>) gson.fromJson(retMsg,
				BaseResponse.class, MobileMostCityResult.class);

		ComResponse<MobileMostCityResult> rsp = new ResponseSimpleHelper<MobileMostCityResult>().ConvertRsp(request,
				output);
		return rsp;

		/*
		 * Map<String,Object> params=new HashMap<String,Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); MobileMostcityPo
		 * po=mmcDao.selectByAppNo(params); MobileMostCityResult result=null;
		 * if(po!=null){ result=new MobileMostCityResult();
		 * BeanUtils.copyProperties(po, result); }
		 * 
		 * ComResponse<MobileMostCityResult>
		 * output=(ComResponse<MobileMostCityResult>) new
		 * ResponseSimpleHelper<MobileMostCityResult>()
		 * .createSuccessRsp(request, result);
		 * 
		 * return output;
		 */
	}

}
