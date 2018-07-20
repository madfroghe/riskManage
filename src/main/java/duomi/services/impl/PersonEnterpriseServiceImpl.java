package duomi.services.impl;

import java.util.ArrayList;
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
import duomi.com.httpIvk.param.enterprise.EnterpriseRepresentative;
import duomi.com.httpIvk.param.enterprise.EnterpriseShareholder;
import duomi.com.httpIvk.param.enterprise.EnterprisesManager;
import duomi.com.httpIvk.param.enterprise.PersonCaseInfo;
import duomi.com.httpIvk.param.enterprise.PersonEnterpriseResult;
import duomi.com.httpIvk.param.enterprise.PunishBreakInfo;
import duomi.com.httpIvk.param.enterprise.PunishedInfo;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.EnterpriseManagerPo;
import duomi.dbMap.bean.EnterprisePersoncasePo;
import duomi.dbMap.bean.EnterprisePunishbreakPoWithBLOBs;
import duomi.dbMap.bean.EnterprisePunishedPo;
import duomi.dbMap.bean.EnterpriseRepresentativePo;
import duomi.dbMap.bean.EnterpriseShareholderPo;
import duomi.dbMap.bean.PersonEnterprisePo;
import duomi.dbMap.mapper.EnterpriseManagerPoMapper;
import duomi.dbMap.mapper.EnterprisePersoncasePoMapper;
import duomi.dbMap.mapper.EnterprisePunishbreakPoMapper;
import duomi.dbMap.mapper.EnterprisePunishedPoMapper;
import duomi.dbMap.mapper.EnterpriseRepresentativePoMapper;
import duomi.dbMap.mapper.EnterpriseShareholderPoMapper;
import duomi.dbMap.mapper.PersonEnterprisePoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.PersonEnterpriseRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.OutsideServiceRegistService;
import duomi.services.PersonEnterpriseService;

@Service
public class PersonEnterpriseServiceImpl implements PersonEnterpriseService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private PersonEnterprisePoMapper peDao;
	@Autowired
	private EnterpriseShareholderPoMapper esDao;
	@Autowired
	private EnterpriseRepresentativePoMapper erDao;
	@Autowired
	private EnterpriseManagerPoMapper emDao;
	@Autowired
	private EnterprisePunishbreakPoMapper epbDao;
	@Autowired
	private EnterprisePunishedPoMapper epedDao;
	@Autowired
	private EnterprisePersoncasePoMapper epcDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	// 个人名下企业查询
	public ComResponse<PersonEnterpriseResult> getPersonEnterprise(PersonEnterpriseRequest request) throws Exception {
		ComResponse<PersonEnterpriseResult> output = null;

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

	private ComResponse<PersonEnterpriseResult> getInfoFromOutside(PersonEnterpriseRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<PersonEnterpriseResult> output = httpservice.getPersonEnterprise(request);

		// 个人名下企业查询 保存入库
		this.inserPersonEnterprise(request, output);

		//保存进mongodb

		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("data",output);
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_PERSONENTERPRISE);

		// 返回消息转换
		ComResponse<PersonEnterpriseResult> rsp = new ResponseSimpleHelper<PersonEnterpriseResult>().ConvertRsp(request,
				output);

		return rsp;
	}

	private void inserPersonEnterprise(PersonEnterpriseRequest request, BaseResponse<PersonEnterpriseResult> output) {
		PersonEnterpriseResult peresult = output.getData();
		if (peresult != null) {
			PersonEnterprisePo dbPo = new PersonEnterprisePo();
			BeanUtils.copyProperties(peresult, dbPo);
			dbPo.setAppNo(request.getAppNo());
			dbPo.setMobile(request.getMobile());
			peDao.insertWithoutKey(dbPo);

			// 接口查询编号
			long queryid = dbPo.getId();
			// 企业股东信息
			List<EnterpriseShareholder> esresults = peresult.getRyposshaList();
			if (esresults != null) {
				for (EnterpriseShareholder esresultmp : esresults) {
					EnterpriseShareholderPo esPo = new EnterpriseShareholderPo();
					BeanUtils.copyProperties(esresultmp, esPo);
					esPo.setQueryid(queryid);
					esDao.insertSelective(esPo);
				}
			}

			// 企业法定代表人信息
			List<EnterpriseRepresentative> erResults = peresult.getRyposfrList();
			if (erResults != null) {
				for (EnterpriseRepresentative erResulttmp : erResults) {
					EnterpriseRepresentativePo erPo = new EnterpriseRepresentativePo();
					BeanUtils.copyProperties(erResulttmp, erPo);
					erPo.setQueryid(queryid);
					erDao.insertSelective(erPo);
				}
			}

			// 企业主要管理人员信息
			List<EnterprisesManager> emResults = peresult.getRyposperList();
			if (emResults != null) {
				for (EnterprisesManager emResultTmp : emResults) {
					EnterpriseManagerPo emPo = new EnterpriseManagerPo();
					BeanUtils.copyProperties(emResultTmp, emPo);
					emPo.setQueryid(queryid);
					emDao.insertSelective(emPo);
				}
			}

			// 失信被执行人的详细信息
			List<PunishBreakInfo> epbResults = peresult.getPunishbreakList();
			if (epbResults != null) {
				for (PunishBreakInfo epbResultsTmp : epbResults) {

					EnterprisePunishbreakPoWithBLOBs epbPo = new EnterprisePunishbreakPoWithBLOBs();
					BeanUtils.copyProperties(epbResultsTmp, epbPo);
					epbPo.setQueryid(queryid);
					epbDao.insertSelective(epbPo);
				}
			}

			// 失信被执行人的详细信息
			List<PunishedInfo> epedResults = peresult.getPunishedList();
			if (epedResults != null) {
				for (PunishedInfo epedResultTmp : epedResults) {
					EnterprisePunishedPo epedPo = new EnterprisePunishedPo();
					BeanUtils.copyProperties(epedResultTmp, epedPo);
					epedPo.setQueryid(queryid);
					epedDao.insertSelective(epedPo);
				}
			}

			// 行政处罚历史信息
			List<PersonCaseInfo> epcResults = peresult.getPersonCaseinfoList();
			if (epcResults != null) {
				for (PersonCaseInfo epcResultTmp : epcResults) {
					EnterprisePersoncasePo epcPo = new EnterprisePersoncasePo();
					BeanUtils.copyProperties(epcResultTmp, epcPo);
					epcPo.setQueryid(queryid);
					epcDao.insertSelective(epcPo);
				}
			}

		}
	}

	private ComResponse<PersonEnterpriseResult> getInfoFromLocal(PersonEnterpriseRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<PersonEnterpriseResult> gson = new GsonUtils<PersonEnterpriseResult>();
		BaseResponse<PersonEnterpriseResult> output = (BaseResponse<PersonEnterpriseResult>) gson.fromJson(retMsg,
				BaseResponse.class, PersonEnterpriseResult.class);

		ComResponse<PersonEnterpriseResult> rsp = new ResponseSimpleHelper<PersonEnterpriseResult>().ConvertRsp(request,
				output);
		return rsp;

		/*
		 * Map<String,Object> params=new HashMap<String,Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); PersonEnterprisePo
		 * pePo=peDao.selectByAppNo(params); PersonEnterpriseResult result=null;
		 * if(pePo!=null){ result=new PersonEnterpriseResult();
		 * BeanUtils.copyProperties(pePo, result);
		 * 
		 * long queryid=pePo.getId(); Map<String,Object> params4list=new
		 * HashMap<String,Object>(); params4list.put("queryid", queryid);
		 * 
		 * //企业股东信息 List<EnterpriseShareholderPo>
		 * esList=esDao.querylistByMap(params4list); List<EnterpriseShareholder>
		 * esResults =null; if(esList!=null){ esResults=new
		 * ArrayList<EnterpriseShareholder>(); for(EnterpriseShareholderPo
		 * es:esList){ EnterpriseShareholder esResult=new
		 * EnterpriseShareholder(); BeanUtils.copyProperties(es, esResult);
		 * esResults.add(esResult); } } result.setRyposshaList(esResults);
		 * 
		 * //企业法定代表人信息 List<EnterpriseRepresentativePo>
		 * erList=erDao.querylistByMap(params4list);
		 * List<EnterpriseRepresentative> erResults=null; //企业法定代表人信息
		 * if(erList!=null){ erResults=new
		 * ArrayList<EnterpriseRepresentative>();
		 * 
		 * for(EnterpriseRepresentativePo er:erList){ EnterpriseRepresentative
		 * erResult=new EnterpriseRepresentative(); BeanUtils.copyProperties(er,
		 * erResult); erResults.add(erResult); } }
		 * result.setRyposfrList(erResults);
		 * 
		 * 
		 * //企业主要管理人员信息 List<EnterpriseManagerPo>
		 * emList=emDao.querylistByMap(params4list); List<EnterprisesManager>
		 * emResults=null; if(emList!=null){ emResults=new
		 * ArrayList<EnterprisesManager>(); for(EnterpriseManagerPo em:emList){
		 * EnterprisesManager emResult=new EnterprisesManager();
		 * BeanUtils.copyProperties(em, emResult); emResults.add(emResult); } }
		 * result.setRyposperList(emResults);
		 * 
		 * //失信被执行人的详细信息 List<EnterprisePunishbreakPoWithBLOBs>
		 * epbList=epbDao.querylistByMap(params4list); List<PunishBreakInfo>
		 * epbResults=null; //失信被执行人的详细信息 if(epbList!=null){ epbResults=new
		 * ArrayList<PunishBreakInfo>(); for(EnterprisePunishbreakPoWithBLOBs
		 * epb:epbList){ PunishBreakInfo epbResult=new PunishBreakInfo();
		 * BeanUtils.copyProperties(epb, epbResult); epbResults.add(epbResult);
		 * } } result.setPunishbreakList(epbResults);
		 * 
		 * //失信被执行人的详细信息 List<EnterprisePunishedPo>
		 * epedList=epedDao.querylistByMap(params4list); List<PunishedInfo>
		 * epedResults =null; //失信被执行人的详细信息 if(epedList!=null){ epedResults=new
		 * ArrayList<PunishedInfo>(); for(EnterprisePunishedPo eped:epedList){
		 * PunishedInfo epedResult=new PunishedInfo();
		 * BeanUtils.copyProperties(eped, epedResult);
		 * epedResults.add(epedResult); } } result.setPunishedList(epedResults);
		 * 
		 * //行政处罚历史信息 List<EnterprisePersoncasePo>
		 * epcList=epcDao.querylistByMap(params4list); List<PersonCaseInfo>
		 * epcResults=null; if(epcList!=null){ epcResults=new
		 * ArrayList<PersonCaseInfo>(); for(EnterprisePersoncasePo epc:epcList){
		 * PersonCaseInfo epcResult=new PersonCaseInfo();
		 * BeanUtils.copyProperties(epc, epcResult); epcResults.add(epcResult);
		 * } } result.setPersonCaseinfoList(epcResults); }
		 * 
		 * ComResponse<PersonEnterpriseResult>
		 * output=(ComResponse<PersonEnterpriseResult>) new
		 * ResponseSimpleHelper<PersonEnterpriseResult>()
		 * .createSuccessRsp(request, result);
		 * 
		 * return output;
		 */
	}

	public static void main(String[] args) {
		List<EnterpriseShareholder> esresults = null;
		List<EnterpriseShareholderPo> esPos = new ArrayList<EnterpriseShareholderPo>();
		for (EnterpriseShareholder esresultmp : esresults) {
			EnterpriseShareholderPo esPo = new EnterpriseShareholderPo();
			BeanUtils.copyProperties(esresultmp, esPo);
			esPos.add(esPo);
		}
	}

}
