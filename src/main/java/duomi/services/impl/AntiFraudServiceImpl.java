package duomi.services.impl;

import java.util.Iterator;
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
import duomi.com.httpIvk.param.antifraud.AntiFraudResult;
import duomi.com.httpIvk.param.antifraud.AntiFraudRules;
import duomi.com.httpIvk.param.antifraud.AntiFraudStrategy;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.AntifraudPo;
import duomi.dbMap.bean.AntifraudStrategyPo;
import duomi.dbMap.bean.AntifraudStrategyRulesPo;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.mapper.AntifraudPoMapper;
import duomi.dbMap.mapper.AntifraudStrategyPoMapper;
import duomi.dbMap.mapper.AntifraudStrategyRulesPoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.AntiFraudRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.OutsideServiceRegistService;

@Service
public class AntiFraudServiceImpl implements duomi.services.AntiFraudService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private AntifraudPoMapper afDao;

	@Autowired
	private AntifraudStrategyPoMapper strategyDao;

	@Autowired
	private AntifraudStrategyRulesPoMapper rulesDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<AntiFraudResult> CheckAntiFraud(AntiFraudRequest request) throws Exception {
		ComResponse<AntiFraudResult> output = null;

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

	private ComResponse<AntiFraudResult> getInfoFromOutside(AntiFraudRequest request) throws Exception {

		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<AntiFraudResult> output = httpservice.checkAntiFraud(request);

		// 反欺诈信息保存入库
		this.inserAntiFraudInfo(request, output);

		// 反欺诈信息保存到mongdb
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("data",output);
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_ANTIFRAUD);

		// 返回消息转换
		ComResponse<AntiFraudResult> rsp = new ResponseSimpleHelper<AntiFraudResult>().ConvertRsp(request, output);

		return rsp;
	}

	// 反欺诈信息保存入库
	private void inserAntiFraudInfo(AntiFraudRequest request, BaseResponse<AntiFraudResult> output) {
		AntiFraudResult afresult = output.getData();
		if (afresult != null) {
			AntifraudPo dbPo = new AntifraudPo();
			if (output.getData() != null) {
				BeanUtils.copyProperties(afresult, dbPo);
			}
			dbPo.setAppNo(request.getAppNo());
			dbPo.setMobile(request.getMobile());
			afDao.insertWithoutKey(dbPo);

			// 反欺诈主键编号
			long antifraudId = dbPo.getId();

			List<AntiFraudStrategy> strategySet = afresult.getStrategySet();
			if (strategySet != null) {
				Iterator<AntiFraudStrategy> it = strategySet.iterator();
				while (it.hasNext()) {
					AntiFraudStrategy strategy = it.next();
					AntifraudStrategyPo strategyDbPo = new AntifraudStrategyPo();
					if (strategy != null) {
						BeanUtils.copyProperties(strategy, strategyDbPo);
					}
					strategyDbPo.setAntifraudId(antifraudId);
					strategyDao.insertWithoutKey(strategyDbPo);

					Long antifraud_strategy_id = strategyDbPo.getId();
					List<AntiFraudRules> ruleSet = strategy.getHitRules();
					if (ruleSet != null) {
						Iterator<AntiFraudRules> ruleit = ruleSet.iterator();
						while (ruleit.hasNext()) {
							AntiFraudRules rule = ruleit.next();
							AntifraudStrategyRulesPo ruleDbPo = new AntifraudStrategyRulesPo();
							if (rule != null) {
								BeanUtils.copyProperties(rule, ruleDbPo);
							}
							ruleDbPo.setAntifraudId(antifraudId);
							ruleDbPo.setAntifraudStrategyId(antifraud_strategy_id);

							rulesDao.insertSelective(ruleDbPo);
						}
					}

				}
			}

		}
	}

	private ComResponse<AntiFraudResult> getInfoFromLocal(AntiFraudRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		/*
		 * Map<String, Object> params = new HashMap<String, Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); AntifraudPo antifraudpo =
		 * afDao.selectByAppNo(params); AntiFraudResult result = null; if
		 * (antifraudpo != null) { result = new AntiFraudResult();
		 * BeanUtils.copyProperties(antifraudpo, result);
		 * 
		 * // 策略明细 List<AntiFraudStrategy> strategySet = new
		 * ArrayList<AntiFraudStrategy>(); Map<String, Object> params4stragety =
		 * new HashMap<String, Object>(); params4stragety.put("antifraudId",
		 * antifraudpo.getId()); List<AntifraudStrategyPo> stragetys =
		 * strategyDao.querylistByMap(params4stragety); if (stragetys != null) {
		 * for (AntifraudStrategyPo stragetytmp : stragetys) { AntiFraudStrategy
		 * stgout = new AntiFraudStrategy();
		 * BeanUtils.copyProperties(stragetytmp, stgout);
		 * 
		 * // 规则内容明细 List<AntiFraudRules> ruleSet = new
		 * ArrayList<AntiFraudRules>(); Map<String, Object> params4rule = new
		 * HashMap<String, Object>(); params4rule.put("antifraudStrategyId",
		 * stragetytmp.getId()); List<AntifraudStrategyRulesPo> rules =
		 * rulesDao.querylistByMap(params4rule); if (rules != null) { for
		 * (AntifraudStrategyRulesPo ruletmp : rules) { AntiFraudRules ruleout =
		 * new AntiFraudRules(); BeanUtils.copyProperties(ruletmp, ruleout);
		 * ruleSet.add(ruleout); } }
		 * 
		 * stgout.setHitRules(ruleSet);
		 * 
		 * strategySet.add(stgout); } }
		 * 
		 * result.setStrategySet(strategySet);
		 * 
		 * }
		 * 
		 * ComResponse<AntiFraudResult> output = (ComResponse<AntiFraudResult>)
		 * new ResponseSimpleHelper<AntiFraudResult>()
		 * .createSuccessRsp(request, result);
		 * 
		 * return output;
		 */

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<AntiFraudResult> gson = new GsonUtils<AntiFraudResult>();
		BaseResponse<AntiFraudResult> output = (BaseResponse<AntiFraudResult>) gson.fromJson(retMsg, BaseResponse.class,
				AntiFraudResult.class);

		ComResponse<AntiFraudResult> rsp = new ResponseSimpleHelper<AntiFraudResult>().ConvertRsp(request, output);
		return rsp;

	}

}
