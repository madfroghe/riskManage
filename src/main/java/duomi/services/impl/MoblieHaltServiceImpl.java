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
import duomi.com.httpIvk.param.phone.MobileHaltResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.MobileHaltPo;
import duomi.dbMap.mapper.MobileHaltPoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.MobileHaltRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.MobileHaltService;
import duomi.services.OutsideServiceRegistService;

@Service
public class MoblieHaltServiceImpl implements MobileHaltService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private MobileHaltPoMapper mhDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<MobileHaltResult> CheckMoblieHalt(MobileHaltRequest request) throws Exception {
		ComResponse<MobileHaltResult> output = null;
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

	private ComResponse<MobileHaltResult> getInfoFromOutside4Check(MobileHaltRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<MobileHaltResult> output = httpservice.getHaltResult(request);

		// 手机号码最近三月停机次数查询
		if (output.getData() != null) {
			MobileHaltPo dbpo = new MobileHaltPo();
			BeanUtils.copyProperties(output.getData(), dbpo);
			dbpo.setAppNo(request.getAppNo());
			mhDao.insertSelective(dbpo);
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
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_MOBILEHALT);

		ComResponse<MobileHaltResult> rsp = new ResponseSimpleHelper<MobileHaltResult>().ConvertRsp(request, output);

		return rsp;
	}

	private ComResponse<MobileHaltResult> getInfoFromLocal4Check(MobileHaltRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<MobileHaltResult> gson = new GsonUtils<MobileHaltResult>();
		BaseResponse<MobileHaltResult> output = (BaseResponse<MobileHaltResult>) gson.fromJson(retMsg,
				BaseResponse.class, MobileHaltResult.class);

		ComResponse<MobileHaltResult> rsp = new ResponseSimpleHelper<MobileHaltResult>().ConvertRsp(request, output);
		return rsp;

		/*
		 * Map<String,Object> params=new HashMap<String,Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); MobileHaltPo
		 * po=mhDao.selectByAppNo(params); MobileHaltResult result=null;
		 * if(po!=null){ result=new MobileHaltResult();
		 * BeanUtils.copyProperties(po, result); }
		 * 
		 * ComResponse<MobileHaltResult> output=(ComResponse<MobileHaltResult>)
		 * new ResponseSimpleHelper<MobileHaltResult>()
		 * .createSuccessRsp(request, result);
		 * 
		 * return output;
		 */
	}

}
