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
import duomi.com.httpIvk.param.creditscore.CreditScoreResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CreditScorePo;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.mapper.CreditScorePoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.CreditScoreRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.CreditScoreService;
import duomi.services.OutsideServiceRegistService;

@Service
public class CreditScoreServiceImpl implements CreditScoreService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private CreditScorePoMapper creditScoreDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<CreditScoreResult> getCreditScore(CreditScoreRequest request) throws Exception {

		ComResponse<CreditScoreResult> output = null;

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

	private ComResponse<CreditScoreResult> getInfoFromOutside(CreditScoreRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<CreditScoreResult> output = httpservice.getCreditScore(request);

		// 信用评分 返回数据保存入库
		if (output.getData() != null) {
			CreditScorePo csPo = new CreditScorePo();
			BeanUtils.copyProperties(output.getData(), csPo);
			csPo.setAppNo(request.getAppNo());
			csPo.setMobile(request.getMobile());
			creditScoreDao.insertSelective(csPo);
		}

		//保存进mongdb;
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("data",output);
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_CREDITSCORE);


		// 返回消息转换
		ComResponse<CreditScoreResult> rsp = new ResponseSimpleHelper<CreditScoreResult>().ConvertRsp(request, output);

		return rsp;
	}

	private ComResponse<CreditScoreResult> getInfoFromLocal(CreditScoreRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<CreditScoreResult> gson = new GsonUtils<CreditScoreResult>();
		BaseResponse<CreditScoreResult> output = (BaseResponse<CreditScoreResult>) gson.fromJson(retMsg,
				BaseResponse.class, CreditScoreResult.class);

		ComResponse<CreditScoreResult> rsp = new ResponseSimpleHelper<CreditScoreResult>().ConvertRsp(request, output);
		return rsp;

		/*
		 * Map<String,Object> params=new HashMap<String,Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); CreditScorePo
		 * po=creditScoreDao.selectByAppNo(params); CreditScoreResult
		 * result=null; if(po !=null){ result=new CreditScoreResult();
		 * BeanUtils.copyProperties(po, result); }
		 * 
		 * ComResponse<CreditScoreResult>
		 * output=(ComResponse<CreditScoreResult>) new
		 * ResponseSimpleHelper<CreditScoreResult>() .createSuccessRsp(request,
		 * result);
		 * 
		 * return output;
		 */
	}

}
