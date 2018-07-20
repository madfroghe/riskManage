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
import duomi.com.httpIvk.param.litigation.CourtNotice;
import duomi.com.httpIvk.param.litigation.DiscreditNotice;
import duomi.com.httpIvk.param.litigation.ExecutiveNotice;
import duomi.com.httpIvk.param.litigation.JudgmentDoc;
import duomi.com.httpIvk.param.litigation.Notice;
import duomi.com.httpIvk.param.litigation.PersonLitigationResult;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.LitigationCourtNoticePo;
import duomi.dbMap.bean.LitigationDiscreditNoticePo;
import duomi.dbMap.bean.LitigationExecutiveNoticePo;
import duomi.dbMap.bean.LitigationJudgmentDocPo;
import duomi.dbMap.bean.PersonLitigationPo;
import duomi.dbMap.mapper.LitigationCourtNoticePoMapper;
import duomi.dbMap.mapper.LitigationDiscreditNoticePoMapper;
import duomi.dbMap.mapper.LitigationExecutiveNoticePoMapper;
import duomi.dbMap.mapper.LitigationJudgmentDocPoMapper;
import duomi.dbMap.mapper.PersonLitigationPoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.PersonLitigationRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.OutsideServiceRegistService;
import duomi.services.PersonLitigationService;

@Service
public class PersonLitigationServiceImpl implements PersonLitigationService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private PersonLitigationPoMapper plDao;
	@Autowired
	private LitigationCourtNoticePoMapper plcnDao; // 法院公告
	@Autowired
	private LitigationJudgmentDocPoMapper pljdDao;// 裁判文书
	@Autowired
	private LitigationExecutiveNoticePoMapper plenDao;// 执行公告
	@Autowired
	private LitigationDiscreditNoticePoMapper pldnDao;// 失信公告

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public static final String NoticeType_court = "01"; // 公告类型：01-法院公告
	public static final String NoticeType_JudgmentDoc = "02"; // 公告类型：02-裁判文书
	public static final String NoticeType_Executive = "03"; // 公告类型：03-执行公告
	public static final String NoticeType_Discredit = "04"; // 公告类型：04-失信公告

	public ComResponse<PersonLitigationResult> getPersonEnterprise(PersonLitigationRequest request) throws Exception {
		ComResponse<PersonLitigationResult> output = null;

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

	private ComResponse<PersonLitigationResult> getInfoFromOutside(PersonLitigationRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<PersonLitigationResult> output = httpservice.getPersonLitigation(request);

		// 个人名下企业查询 保存入库
		this.inserPersonLitigation(request, output);

		//保存进mongodb
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("data",output);
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_PERSONLITIGATION);

		// 返回消息转换
		ComResponse<PersonLitigationResult> rsp = new ResponseSimpleHelper<PersonLitigationResult>().ConvertRsp(request,
				output);

		return rsp;
	}

	private void inserPersonLitigation(PersonLitigationRequest request, BaseResponse<PersonLitigationResult> output) {
		PersonLitigationResult result = output.getData();
		if (result != null) {

			// 法院公告（fygg）
			Notice<CourtNotice> fygg = result.getFygg();
			if (fygg != null) {

				PersonLitigationPo dbPo = new PersonLitigationPo();
				BeanUtils.copyProperties(fygg, dbPo);
				dbPo.setAppNo(request.getAppNo());
				dbPo.setMobile(request.getMobile());
				dbPo.setNoticeType(this.NoticeType_court); // 法院公告
				plDao.insertWithoutKey(dbPo);

				// 接口查询编号
				long queryid = dbPo.getId();

				// 法院公告（fygg）
				List<CourtNotice> courtList = fygg.getPageData();
				if (courtList != null) {
					for (CourtNotice cn : courtList) {
						LitigationCourtNoticePo courtPo = new LitigationCourtNoticePo();
						BeanUtils.copyProperties(cn, courtPo);
						courtPo.setQueryid(queryid);
						plcnDao.insertSelective(courtPo);
					}
				}
			}

			// // 裁判文书
			Notice<JudgmentDoc> cpws = result.getCpws();
			if (cpws != null) {
				PersonLitigationPo dbPo = new PersonLitigationPo();
				BeanUtils.copyProperties(cpws, dbPo);
				dbPo.setAppNo(request.getAppNo());
				dbPo.setMobile(request.getMobile());
				dbPo.setNoticeType(this.NoticeType_JudgmentDoc); // 裁判文书
				plDao.insertWithoutKey(dbPo);
				// 接口查询编号
				long queryid = dbPo.getId();

				// 裁判文书
				List<JudgmentDoc> judgList = cpws.getPageData();
				if (judgList != null) {
					for (JudgmentDoc jd : judgList) {
						LitigationJudgmentDocPo jdPo = new LitigationJudgmentDocPo();
						BeanUtils.copyProperties(jd, jdPo);
						jdPo.setResume(jd.getDesc());
						jdPo.setQueryid(queryid);
						pljdDao.insertSelective(jdPo);
					}
				}

			}

			// 执行公告（zxgg）
			Notice<ExecutiveNotice> zxgg = result.getZxgg();
			if (zxgg != null) {

				PersonLitigationPo dbPo = new PersonLitigationPo();
				BeanUtils.copyProperties(zxgg, dbPo);
				dbPo.setAppNo(request.getAppNo());
				dbPo.setMobile(request.getMobile());
				dbPo.setNoticeType(this.NoticeType_Executive); // 执行公告
				plDao.insertWithoutKey(dbPo);
				// 接口查询编号
				long queryid = dbPo.getId();

				// 执行公告（fygg）
				List<ExecutiveNotice> execList = zxgg.getPageData();
				if (execList != null) {
					for (ExecutiveNotice exec : execList) {
						LitigationExecutiveNoticePo execPo = new LitigationExecutiveNoticePo();
						BeanUtils.copyProperties(exec, execPo);
						execPo.setQueryid(queryid);
						plenDao.insertSelective(execPo);
					}
				}

			}

			// 失信公告（sxgg）
			Notice<DiscreditNotice> sxgg = result.getSxgg();
			if (sxgg != null) {
				PersonLitigationPo dbPo = new PersonLitigationPo();
				BeanUtils.copyProperties(sxgg, dbPo);
				dbPo.setAppNo(request.getAppNo());
				dbPo.setMobile(request.getMobile());
				dbPo.setNoticeType(this.NoticeType_Discredit); // 失信公告
				plDao.insertWithoutKey(dbPo);
				// 接口查询编号
				long queryid = dbPo.getId();

				// 失信公告（sxgg）
				List<DiscreditNotice> discreditList = sxgg.getPageData();
				if (discreditList != null) {
					for (DiscreditNotice dn : discreditList) {
						LitigationDiscreditNoticePo dnPo = new LitigationDiscreditNoticePo();
						BeanUtils.copyProperties(dn, dnPo);
						dnPo.setQueryid(queryid);
						pldnDao.insertSelective(dnPo);
					}
				}

			}

		}
	}

	private ComResponse<PersonLitigationResult> getInfoFromLocal(PersonLitigationRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<PersonLitigationResult> gson = new GsonUtils<PersonLitigationResult>();
		BaseResponse<PersonLitigationResult> output = (BaseResponse<PersonLitigationResult>) gson.fromJson(retMsg,
				BaseResponse.class, PersonLitigationResult.class);

		ComResponse<PersonLitigationResult> rsp = new ResponseSimpleHelper<PersonLitigationResult>().ConvertRsp(request,
				output);
		return rsp;

		/*
		 * Map<String, Object> params = new HashMap<String, Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); List<PersonLitigationPo>
		 * plPolist = plDao.querylistByMap(params); PersonLitigationResult
		 * result = new PersonLitigationResult();
		 * 
		 * Notice<CourtNotice> plcnResults = null; Notice<JudgmentDoc>
		 * pljdResults = null; Notice<ExecutiveNotice> plenResults = null;
		 * Notice<DiscreditNotice> pldnResults = null; if (plPolist != null) {
		 * plcnResults = new Notice<CourtNotice>();// 法院公告 pljdResults = new
		 * Notice<JudgmentDoc>();// 裁判文书 plenResults = new
		 * Notice<ExecutiveNotice>(); // 执行公告 pldnResults = new
		 * Notice<DiscreditNotice>(); // 失信公告
		 * 
		 * List<CourtNotice> cnList = new ArrayList<CourtNotice>(); //// 法院公告
		 * List<JudgmentDoc> jdList = new ArrayList<JudgmentDoc>(); //// 裁判文书
		 * List<ExecutiveNotice> enList = new ArrayList<ExecutiveNotice>(); ////
		 * 执行公告 List<DiscreditNotice> dnList = new ArrayList<DiscreditNotice>();
		 * //// 失信公告 if (plPolist != null) { for (PersonLitigationPo plPo :
		 * plPolist) { long queryid = plPo.getId(); Map<String, Object>
		 * params4list = new HashMap<String, Object>();
		 * params4list.put("queryid", queryid);
		 * 
		 * String noticeType = plPo.getNoticeType(); if
		 * (this.NoticeType_court.equals(noticeType)) { // 法院公告
		 * BeanUtils.copyProperties(plPo, plcnResults);
		 * 
		 * List<LitigationCourtNoticePo> plcnPolist =
		 * plcnDao.querylistByMap(params4list); if (plcnPolist != null) { for
		 * (LitigationCourtNoticePo plcnPo : plcnPolist) { CourtNotice cnResult
		 * = new CourtNotice(); BeanUtils.copyProperties(plcnPo, cnResult);
		 * cnList.add(cnResult); } }
		 * 
		 * } else if (this.NoticeType_JudgmentDoc.equals(noticeType)) {// 裁判文书
		 * BeanUtils.copyProperties(plPo, pljdResults);
		 * 
		 * List<LitigationJudgmentDocPo> pljdPoList =
		 * pljdDao.querylistByMap(params4list); if (pljdPoList != null) { for
		 * (LitigationJudgmentDocPo pljdPo : pljdPoList) { JudgmentDoc jdResult
		 * = new JudgmentDoc(); BeanUtils.copyProperties(pljdPo, jdResult);
		 * jdResult.setDesc(pljdPo.getResume()); // 简介 jdList.add(jdResult); } }
		 * } else if (this.NoticeType_Executive.equals(noticeType)) {// 执行公告
		 * BeanUtils.copyProperties(plPo, plenResults);
		 * 
		 * List<LitigationExecutiveNoticePo> plenPolist =
		 * plenDao.querylistByMap(params4list); if (plenPolist != null) { for
		 * (LitigationExecutiveNoticePo pljdPo : plenPolist) { ExecutiveNotice
		 * jdResult = new ExecutiveNotice(); BeanUtils.copyProperties(pljdPo,
		 * jdResult); enList.add(jdResult); } }
		 * 
		 * } else if (this.NoticeType_Discredit.equals(noticeType)) {//
		 * 公告类型：04-失信公告 BeanUtils.copyProperties(plPo, pldnResults);
		 * 
		 * List<LitigationDiscreditNoticePo> pldnPoList =
		 * pldnDao.querylistByMap(params4list); if (pldnPoList != null) { for
		 * (LitigationDiscreditNoticePo pldnPo : pldnPoList) { DiscreditNotice
		 * dnResult = new DiscreditNotice(); BeanUtils.copyProperties(pldnPo,
		 * dnResult); dnList.add(dnResult); } } } } }
		 * 
		 * plcnResults.setPageData(cnList); pljdResults.setPageData(jdList);
		 * plenResults.setPageData(enList); pldnResults.setPageData(dnList); }
		 * 
		 * result.setFygg(plcnResults); result.setCpws(pljdResults);
		 * result.setZxgg(plenResults); result.setSxgg(pldnResults);
		 * 
		 * ComResponse<PersonLitigationResult> output =
		 * (ComResponse<PersonLitigationResult>) new
		 * ResponseSimpleHelper<PersonLitigationResult>()
		 * .createSuccessRsp(request, result);
		 * 
		 * return output;
		 */
	}

}
