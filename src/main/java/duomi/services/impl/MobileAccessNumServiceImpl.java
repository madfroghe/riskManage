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
import duomi.com.httpIvk.param.phone.MobileAccessNumResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.MobileAccessnumPo;
import duomi.dbMap.mapper.MobileAccessnumPoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.MobileAccessNumRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.MobileAccessNumService;
import duomi.services.OutsideServiceRegistService;

@Service
public class MobileAccessNumServiceImpl implements MobileAccessNumService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private MobileAccessnumPoMapper manDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<MobileAccessNumResult> getMobileAccessNumService(MobileAccessNumRequest request)
			throws Exception {
		ComResponse<MobileAccessNumResult> output = null;
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

	private ComResponse<MobileAccessNumResult> getInfoFromOutside4Check(MobileAccessNumRequest request)
			throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<MobileAccessNumResult> output = httpservice.getMobileAccessNum(request);

		if (output.getData() != null) {
			// 手机号指定月通话天数最多的城市
			MobileAccessnumPo dbpo = new MobileAccessnumPo();
			BeanUtils.copyProperties(output.getData(), dbpo);
			dbpo.setAppNo(request.getAppNo());
			manDao.insertSelective(dbpo);
		}

		//保存进mongodb
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("data",output);
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_MOBILEACCESSNUM);

		ComResponse<MobileAccessNumResult> rsp = new ResponseSimpleHelper<MobileAccessNumResult>().ConvertRsp(request,
				output);

		return rsp;
	}

	private ComResponse<MobileAccessNumResult> getInfoFromLocal4Check(MobileAccessNumRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<MobileAccessNumResult> gson = new GsonUtils<MobileAccessNumResult>();
		BaseResponse<MobileAccessNumResult> output = (BaseResponse<MobileAccessNumResult>) gson.fromJson(retMsg,
				BaseResponse.class, MobileAccessNumResult.class);

		ComResponse<MobileAccessNumResult> rsp = new ResponseSimpleHelper<MobileAccessNumResult>().ConvertRsp(request,
				output);
		return rsp;

		/*
		 * Map<String,Object> params=new HashMap<String,Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); MobileAccessnumPo
		 * po=manDao.selectByAppNo(params); MobileAccessNumResult result=null;
		 * if(po!=null){ result=new MobileAccessNumResult();
		 * BeanUtils.copyProperties(po, result); }
		 * 
		 * ComResponse<MobileAccessNumResult>
		 * output=(ComResponse<MobileAccessNumResult>) new
		 * ResponseSimpleHelper<MobileAccessNumResult>()
		 * .createSuccessRsp(request, result);
		 * 
		 * return output;
		 */
	}

}
