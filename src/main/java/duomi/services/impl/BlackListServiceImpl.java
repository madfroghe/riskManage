package duomi.services.impl;

import java.util.List;
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
import duomi.com.httpIvk.param.blacklist.BlackListDetail;
import duomi.com.httpIvk.param.blacklist.BlackListResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.PersonBlacklistDetailPo;
import duomi.dbMap.bean.PersonBlacklistPo;
import duomi.dbMap.mapper.PersonBlacklistDetailPoMapper;
import duomi.dbMap.mapper.PersonBlacklistPoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.BlackListRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.BlackListService;
import duomi.services.OutsideServiceRegistService;

@Service
public class BlackListServiceImpl implements BlackListService {
	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private PersonBlacklistPoMapper blDao;

	@Autowired
	private PersonBlacklistDetailPoMapper bldtlDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<BlackListResult> CheckBlackList(BlackListRequest request) throws Exception {
		ComResponse<BlackListResult> output = null;

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

	private ComResponse<BlackListResult> getInfoFromOutside(BlackListRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<BlackListResult> output = httpservice.getBlackList(request);

		// 个人不良记录保存入库
		this.insertBlackListResult(request, output);

		//保存进mongodb;
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("data",output);
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_PERSONBLACKLIST);

		// 返回消息转换
		ComResponse<BlackListResult> rsp = new ResponseSimpleHelper<BlackListResult>().ConvertRsp(request, output);

		return rsp;
	}

	// 个人不良记录保存入库
	private void insertBlackListResult(BlackListRequest request, BaseResponse<BlackListResult> output) {
		BlackListResult result = output.getData();
		if (result != null) {
			PersonBlacklistPo blPo = new PersonBlacklistPo();
			BeanUtils.copyProperties(result, blPo);
			blPo.setAppNo(request.getAppNo());
			blPo.setMobile(request.getMobile());
			blDao.insertWithoutKey(blPo);

			long blacklistid = blPo.getId();
			List<BlackListDetail> details = result.getData();
			if (details != null) {
				for (BlackListDetail detailtmp : details) {
					PersonBlacklistDetailPo detailPo = new PersonBlacklistDetailPo();
					BeanUtils.copyProperties(detailtmp, detailPo);
					detailPo.setBlacklistid(blacklistid);
					bldtlDao.insertSelective(detailPo);
				}
			}

		}

	}

	private ComResponse<BlackListResult> getInfoFromLocal(BlackListRequest request,
														  CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<BlackListResult> gson = new GsonUtils<BlackListResult>();
		BaseResponse<BlackListResult> output = (BaseResponse<BlackListResult>) gson.fromJson(retMsg, BaseResponse.class,
				BlackListResult.class);

		ComResponse<BlackListResult> rsp = new ResponseSimpleHelper<BlackListResult>().ConvertRsp(request, output);
		return rsp;

	}

}
