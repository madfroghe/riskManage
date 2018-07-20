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
import duomi.com.httpIvk.param.multipleLend.ArrearsInquiry;
import duomi.com.httpIvk.param.multipleLend.CreditPlatformRegistrationDetails;
import duomi.com.httpIvk.param.multipleLend.LoanApplicationDetails;
import duomi.com.httpIvk.param.multipleLend.LoanOverdueDetails;
import duomi.com.httpIvk.param.multipleLend.LoanRejectDetails;
import duomi.com.httpIvk.param.multipleLend.MultipleLendResult;
import duomi.com.httpIvk.param.multipleLend.loanLenderDetails;
import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.MultipleLendPo;
import duomi.dbMap.bean.MultiplelendArrearsinquiryPo;
import duomi.dbMap.bean.MultiplelendLoanappPo;
import duomi.dbMap.bean.MultiplelendLoanlenderPo;
import duomi.dbMap.bean.MultiplelendLoanrejectPo;
import duomi.dbMap.bean.MultiplelendOverduePo;
import duomi.dbMap.bean.MultiplelendRegistPo;
import duomi.dbMap.mapper.MultipleLendPoMapper;
import duomi.dbMap.mapper.MultiplelendArrearsinquiryPoMapper;
import duomi.dbMap.mapper.MultiplelendLoanappPoMapper;
import duomi.dbMap.mapper.MultiplelendLoanlenderPoMapper;
import duomi.dbMap.mapper.MultiplelendLoanrejectPoMapper;
import duomi.dbMap.mapper.MultiplelendOverduePoMapper;
import duomi.dbMap.mapper.MultiplelendRegistPoMapper;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.MultipleLendRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.MultipleLendService;
import duomi.services.OutsideServiceRegistService;

@Service
public class MultipleLendServiceImpl implements MultipleLendService {

	@Autowired
	private ZbaDataHttpService httpservice;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private MultipleLendPoMapper mltpLendDao;

	@Autowired
	private MultiplelendRegistPoMapper registDao;

	@Autowired
	private MultiplelendLoanappPoMapper appDao;

	@Autowired
	private MultiplelendLoanlenderPoMapper lendDao;

	@Autowired
	private MultiplelendLoanrejectPoMapper rejectDao;
	@Autowired
	private MultiplelendOverduePoMapper overdueDao;
	@Autowired
	private MultiplelendArrearsinquiryPoMapper arrearsDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	public static JSONObject staticJSON = new JSONObject();

	public ComResponse<MultipleLendResult> checkMultipleLend(MultipleLendRequest request) throws Exception {

		ComResponse<MultipleLendResult> output = null;

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

	private ComResponse<MultipleLendResult> getInfoFromOutside(MultipleLendRequest request) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(request);

		// 发送外部服务
		BaseResponse<MultipleLendResult> output = httpservice.checkMultipleLend(request);

		// 多重贷款信息插入数据库
		this.insertMultipleLend(request, output);

		//保存到mongodb
		JSONObject mgJson = new JSONObject();
		mgJson.put("appno",request.getAppNo());
		mgJson.put("name",request.getName());
		mgJson.put("mobile",request.getMobile());
		mgJson.put("idCard",request.getIdCard());
		mgJson.put("data",output);
		mgJson.put("interSerno",request.getInterSerno());
		mgJson.put("analyJSON", JSONUtils.analysisJson(JSONUtils.toJSONObject(output),"analysis",staticJSON));
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_MULTIPLELEND);

		// 返回消息转换
		ComResponse<MultipleLendResult> rsp = new ResponseSimpleHelper<MultipleLendResult>().ConvertRsp(request,
				output);

		return rsp;
	}

	private ComResponse<MultipleLendResult> getInfoFromLocal(MultipleLendRequest request,
			CspInterfaceStatPoWithBLOBs cspStatPo) {
		/*
		 * Map<String,Object> params=new HashMap<String,Object>();
		 * params.put("appNo", cspStatPo.getAppNo()); MultipleLendPo
		 * mltpPo=mltpLendDao.selectByAppNo(params); MultipleLendResult
		 * result=null; if(mltpPo !=null){ result=new MultipleLendResult();
		 * BeanUtils.copyProperties(mltpPo, result);
		 * 
		 * //信贷平台注册详情 Map<String,Object> params4List=new
		 * HashMap<String,Object>(); params4List.put("multiplelendId",
		 * mltpPo.getId());
		 * 
		 * List<MultiplelendRegistPo>
		 * registList=registDao.querylistByMap(params4List);
		 * List<CreditPlatformRegistrationDetails> registdtls=new
		 * ArrayList<CreditPlatformRegistrationDetails>();
		 * for(MultiplelendRegistPo registtmp:registList){
		 * CreditPlatformRegistrationDetails registdtl=new
		 * CreditPlatformRegistrationDetails();
		 * BeanUtils.copyProperties(registtmp, registdtl);
		 * registdtls.add(registdtl); }
		 * result.setCreditPlatformRegistrationDetails(registdtls);
		 * 
		 * //贷款申请详情 List<MultiplelendLoanappPo>
		 * applist=appDao.querylistByMap(params4List);
		 * List<LoanApplicationDetails> appdtls=new
		 * ArrayList<LoanApplicationDetails>(); for(MultiplelendLoanappPo
		 * apptmp:applist){ LoanApplicationDetails appdtl=new
		 * LoanApplicationDetails(); BeanUtils.copyProperties(apptmp, appdtl);
		 * appdtls.add(appdtl); } result.setLoanApplicationDetails(appdtls);
		 * 
		 * //贷款放款详情 List<MultiplelendLoanlenderPo>
		 * lenderList=lendDao.querylistByMap(params4List);
		 * List<loanLenderDetails> lenddtls=new ArrayList<loanLenderDetails>();
		 * for(MultiplelendLoanlenderPo lendtmp:lenderList){ loanLenderDetails
		 * lenddtl=new loanLenderDetails(); BeanUtils.copyProperties(lendtmp,
		 * lenddtl); lenddtls.add(lenddtl); }
		 * result.setLoanLenderDetails(lenddtls);
		 * 
		 * //贷款驳回详情] List<MultiplelendLoanrejectPo>
		 * rejectlist=rejectDao.querylistByMap(params4List);
		 * List<LoanRejectDetails> rejectdtls=new
		 * ArrayList<LoanRejectDetails>(); for(MultiplelendLoanrejectPo
		 * rejecttmp:rejectlist){ LoanRejectDetails rejectdtl=new
		 * LoanRejectDetails(); BeanUtils.copyProperties(rejecttmp, rejectdtl);
		 * rejectdtls.add(rejectdtl); } result.setLoanRejectDetails(rejectdtls);
		 * 
		 * //逾期平台详情查询 List<MultiplelendOverduePo>
		 * overdueList=overdueDao.querylistByMap(params4List);
		 * List<LoanOverdueDetails> overduedtls=new
		 * ArrayList<LoanOverdueDetails>(); for(MultiplelendOverduePo
		 * overduetmp:overdueList){ LoanOverdueDetails overduedtl=new
		 * LoanOverdueDetails(); BeanUtils.copyProperties(overduetmp,
		 * overduedtl); overduedtls.add(overduedtl); }
		 * result.setLoanOverdueDetails(overduedtls);
		 * 
		 * //欠款查询 List<MultiplelendArrearsinquiryPo>
		 * arrearsList=arrearsDao.querylistByMap(params4List);
		 * List<ArrearsInquiry> arrearsDtls=new ArrayList<ArrearsInquiry>();
		 * for(MultiplelendArrearsinquiryPo arrearstmp:arrearsList){
		 * ArrearsInquiry arrearsDtl=new ArrearsInquiry();
		 * BeanUtils.copyProperties(arrearstmp, arrearsDtl);
		 * arrearsDtls.add(arrearsDtl); } result.setArrearsInquiry(arrearsDtls);
		 * }
		 * 
		 * ComResponse<MultipleLendResult>
		 * output=(ComResponse<MultipleLendResult>) new
		 * ResponseSimpleHelper<MultipleLendResult>() .createSuccessRsp(request,
		 * result);
		 * 
		 * return output;
		 */

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<MultipleLendResult> gson = new GsonUtils<MultipleLendResult>();
		BaseResponse<MultipleLendResult> output = (BaseResponse<MultipleLendResult>) gson.fromJson(retMsg,
				BaseResponse.class, MultipleLendResult.class);

		ComResponse<MultipleLendResult> rsp = new ResponseSimpleHelper<MultipleLendResult>().ConvertRsp(request,
				output);
		return rsp;
	}

	private void insertMultipleLend(MultipleLendRequest request, BaseResponse<MultipleLendResult> output) {
		MultipleLendResult result = output.getData();
		if (result != null) {
			MultipleLendPo mlpLendPo = new MultipleLendPo();
			BeanUtils.copyProperties(result, mlpLendPo);
			mlpLendPo.setAppNo(request.getAppNo());
			mltpLendDao.insertWithoutKey(mlpLendPo);

			long multiplelendId = mlpLendPo.getId(); // 多重借款编号

			// 信贷平台注册详情
			List<CreditPlatformRegistrationDetails> regists = result.getCreditPlatformRegistrationDetails();
			if (regists != null) {
				for (CreditPlatformRegistrationDetails registtmp : regists) {
					MultiplelendRegistPo rgistPo = new MultiplelendRegistPo();
					BeanUtils.copyProperties(registtmp, rgistPo);
					rgistPo.setMultiplelendId(multiplelendId);
					registDao.insertSelective(rgistPo);
				}
			}

			// 贷款申请详情
			List<LoanApplicationDetails> appDetail = result.getLoanApplicationDetails();
			if (appDetail != null) {
				for (LoanApplicationDetails apptmp : appDetail) {
					MultiplelendLoanappPo appPo = new MultiplelendLoanappPo();
					BeanUtils.copyProperties(apptmp, appPo);
					appPo.setMultiplelendId(multiplelendId);
					appDao.insertSelective(appPo);
				}
			}

			// 贷款放款详情
			List<loanLenderDetails> leanddtl = result.getLoanLenderDetails();
			if (leanddtl != null) {
				for (loanLenderDetails lendtmp : leanddtl) {
					MultiplelendLoanlenderPo lendPo = new MultiplelendLoanlenderPo();
					BeanUtils.copyProperties(lendtmp, lendPo);
					lendPo.setMultiplelendId(multiplelendId);
					lendDao.insertSelective(lendPo);
				}
			}

			// 贷款驳回详情
			List<LoanRejectDetails> rejectdtl = result.getLoanRejectDetails();
			if (rejectdtl != null) {
				for (LoanRejectDetails rejecttmp : rejectdtl) {
					MultiplelendLoanrejectPo rejectPo = new MultiplelendLoanrejectPo();
					BeanUtils.copyProperties(rejecttmp, rejectPo);
					rejectPo.setMultiplelendId(multiplelendId);
					rejectDao.insertSelective(rejectPo);
				}
			}

			// 逾期平台详情查询
			List<LoanOverdueDetails> overdueDtl = result.getLoanOverdueDetails();
			if (overdueDtl != null) {
				for (LoanOverdueDetails overduetmp : overdueDtl) {
					MultiplelendOverduePo overduePo = new MultiplelendOverduePo();
					BeanUtils.copyProperties(overduetmp, overduePo);
					overduePo.setMultiplelendId(multiplelendId);
					overdueDao.insertSelective(overduePo);
				}
			}

			// 欠款查询
			List<ArrearsInquiry> arrears = result.getArrearsInquiry();
			if (arrears != null) {
				for (ArrearsInquiry arrearstmp : arrears) {
					MultiplelendArrearsinquiryPo arrearsPo = new MultiplelendArrearsinquiryPo();
					BeanUtils.copyProperties(arrearstmp, arrearsPo);
					arrearsPo.setMultiplelendId(multiplelendId);
					arrearsDao.insertSelective(arrearsPo);
				}
			}

		}
	}
}
