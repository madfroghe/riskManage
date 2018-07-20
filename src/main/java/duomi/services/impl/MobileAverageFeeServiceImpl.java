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
import duomi.com.httpIvk.param.phone.MobileAverageFeeResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.MobileAveragefeePo;
import duomi.dbMap.mapper.MobileAveragefeePoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.MobileAverageFeeRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.MobileAverageFeeService;
import duomi.services.OutsideServiceRegistService;

@Service
public class MobileAverageFeeServiceImpl implements MobileAverageFeeService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;
	@Autowired
	private MobileAveragefeePoMapper mafDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<MobileAverageFeeResult> checkMobileAverageFee(MobileAverageFeeRequest request) throws Exception {
		ComResponse<MobileAverageFeeResult> output = null;
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

	private ComResponse<MobileAverageFeeResult> getInfoFromOutside4Check(MobileAverageFeeRequest request)
			throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<MobileAverageFeeResult> output = httpservice.getMobileAverageFee(request);

		if (output.getData() != null) {
			// 手机号指定月通话天数最多的城市
			MobileAveragefeePo dbpo = new MobileAveragefeePo();
			BeanUtils.copyProperties(output.getData(), dbpo);
			dbpo.setAppNo(request.getAppNo());
			mafDao.insertSelective(dbpo);
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
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_MOBILEAVERAGEFEE);


		ComResponse<MobileAverageFeeResult> rsp = new ResponseSimpleHelper<MobileAverageFeeResult>().ConvertRsp(request,
				output);

		return rsp;
	}

	private ComResponse<MobileAverageFeeResult> getInfoFromLocal4Check(MobileAverageFeeRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<MobileAverageFeeResult> gson = new GsonUtils<MobileAverageFeeResult>();
		BaseResponse<MobileAverageFeeResult> output = (BaseResponse<MobileAverageFeeResult>) gson.fromJson(retMsg,
				BaseResponse.class, MobileAverageFeeResult.class);

		ComResponse<MobileAverageFeeResult> rsp = new ResponseSimpleHelper<MobileAverageFeeResult>().ConvertRsp(request,
				output);
		return rsp;

		/*
		 * Map<String,Object> params=new HashMap<String,Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); MobileAveragefeePo
		 * po=mafDao.selectByAppNo(params); MobileAverageFeeResult result=null;
		 * if(po!=null){ result=new MobileAverageFeeResult();
		 * BeanUtils.copyProperties(po, result); }
		 * 
		 * ComResponse<MobileAverageFeeResult>
		 * output=(ComResponse<MobileAverageFeeResult>) new
		 * ResponseSimpleHelper<MobileAverageFeeResult>()
		 * .createSuccessRsp(request, result);
		 * 
		 * return output;
		 */
	}

}
