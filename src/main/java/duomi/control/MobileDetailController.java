package duomi.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import duomi.dbMap.bean.MobileDetailRecordPo;
import duomi.dbMap.bean.SaveTaskInfoPo;
import duomi.dbMap.mapper.MobileDetailRecordPoMapper;
import duomi.dbMap.mapper.SaveTaskInfoPoMapper;
import duomi.dispatch.response.result.*;
import duomi.mongodb.bean.TaskInfoMgBean;
import duomi.mongodb.dao.Impl.TaskInfoDaoImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.mobiledetail.MBDetailRequest;
import duomi.com.utils.JSONUtils;
import duomi.dispatch.factory.ResponseSimpleHelper;
import duomi.dispatch.request.MobileCBNotifyData;
import duomi.dispatch.request.MobileCallBackRequest;
import duomi.dispatch.request.MobileDetailRequest;
import duomi.dispatch.request.MobileQueryDetailRequest;
import duomi.dispatch.response.ComResponse;
import duomi.dispatch.response.MobileCallBackResponse;
import duomi.services.TDMobileDetailService;

@Controller
public class MobileDetailController {

	private final static Logger logger = LoggerFactory.getLogger(MobileDetailController.class);

	private final static String defaulErrorCode = "E999999";

	@Autowired
	private TDMobileDetailService service;

	@Autowired
	private SaveTaskInfoPoMapper taskInfoDao;

	@Autowired
	private TaskInfoDaoImpl taskInfoMongdbDao;

	@Autowired
	private MobileDetailRecordPoMapper mobileDetailRecordDao;

	@RequestMapping(value = "/startMobileTesk", method = RequestMethod.POST)
	@ResponseBody
	public MobileDetailResponse startMobileTesk(MobileDetailRequest request) {
		MobileDetailResponse rsp = new MobileDetailResponse();
		try {
			rsp = service.startTesk(request);
		} catch (HttpBizException e) {
			rsp = this.dealException(e);
		} catch (Exception e) {
			rsp = this.dealException(e);
		}
		return rsp;
	}

	@RequestMapping(value = "/sendMobileTesk", method = RequestMethod.POST)
	@ResponseBody
	public MobileDetailResponse sendMobileTesk(MobileDetailRequest request) {
		MobileDetailResponse rsp = new MobileDetailResponse();
		try {
			rsp = service.sendTesk(request);
		} catch (HttpBizException e) {
			rsp = this.dealException(e);
		} catch (Exception e) {
			rsp = this.dealException(e);
		}

		return rsp;
	}

	@RequestMapping(value = "/mobileCallBack", method = RequestMethod.POST)
	public void mobileCallBack(MobileCallBackRequest request, HttpServletResponse response) throws Exception {

		String notify_data = request.getNotify_data();
		logger.info("--通话详单回调请求----" + notify_data);
		String task_id = null;
		MobileCBNotifyData notifydata=null;
		if (StringUtils.isNotBlank(notify_data)) {
			notifydata = JSONUtils.toBean(notify_data, MobileCBNotifyData.class);
			task_id = notifydata.getTask_id();
		}

		if (StringUtils.isBlank(task_id)) {
			logger.error("--接受通话详单回调异常：未传入任务编号----");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		MBDetailRequest mbreq = new MBDetailRequest();
		mbreq.setTask_id(task_id);

		// 收到回调请求后从同盾获取数据
		// code 为0 才抓取成功
		if (notifydata != null && "0".equals(notifydata.getCode())) {
			try{
				String contactDetail = service.getContactDetail(mbreq); //获取联系人信息

				logger.info("--联系人信息回调返回成功----" + contactDetail);
			}catch (Exception e){
				logger.error(e.getMessage());
			}

			service.getMobileDetail(mbreq);   //获取通话详单
		}

		MobileCallBackResponse rsp = new MobileCallBackResponse();
		rsp.setCode("success");
		rsp.setMsg("回调成功");
		String rspStr = JSONUtils.toJSONString(rsp);
		logger.info("--通话详单回调返回成功----" + rspStr);
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out = response.getWriter();
		out.write(rspStr);
		out.flush();
		out.close();
		return;
	}

	@RequestMapping(value = "/saveTaskInfo", method = RequestMethod.POST)
	@ResponseBody
	public String saveTaskInfo(SaveTaskInfoPo taskInfoPo){
		int returnNum = 0 ;

		//保存数据到mongdb
		TaskInfoMgBean taskInfoMgBean = new TaskInfoMgBean();
		taskInfoMgBean.setTaskId(taskInfoPo.getTaskId());
		taskInfoMgBean.setName(taskInfoPo.getName());
		taskInfoMgBean.setPhone(taskInfoPo.getPhone());
		try {
			taskInfoMgBean.setReqTime(formatDate(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		taskInfoMongdbDao.add(taskInfoMgBean);


		//保存数据到Mysql
		try{
			returnNum = taskInfoDao.insert(taskInfoPo);
			if(returnNum == 1){
				return "success";
			}else{
				return "fail";
			}
		}catch (Exception e){
			logger.error("任务Id" + taskInfoPo.getTaskId() + "保存数据失败" + e.getMessage());
			return "fail";
		}
	}

	@RequestMapping(value = "/queryReqTimeByPhone" , method = RequestMethod.GET)
	@ResponseBody
	public String queryReqTimeByPhone(String mobile){
		logger.info("--------------------通过电话查找认证时间:mobile:"+mobile);
		if(mobile == null){
			return "电话号码不能为空";
		}

		Map<String,Object> paramMap = new HashMap();
		paramMap.put("mobile",mobile);

		MobileDetailRecordPo latelyRecord = mobileDetailRecordDao.queryLatelyTaskInfoByMap(paramMap);

		if(latelyRecord != null){
			Date recordDate = latelyRecord.getCreateTime();
			Date nowDate = new Date();
			int betweenDay = 0;
			try {
				betweenDay = daysBetween(recordDate,nowDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if(betweenDay > 30){
				logger.info("--------------有数据大于30天,返回APP端的值--------------:no");
				return "no";
			}else{
				logger.info("--------------有数据小于30天,返回APP端的值--------------:yes");
				return "yes";
			}
		}
		logger.info("------------------无数据,返回APP端的值--------------:no");
		return "no";
	}

	@RequestMapping(value = "/queryMobileDetail", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<MobileDetailResult> queryMobileDetail(MobileQueryDetailRequest request) {

		MobileDetailResult resut = new MobileDetailResult();
		ComResponse<MobileDetailResult> rsp = new ComResponse<MobileDetailResult>();
		try {
			resut = service.queryMobileDetail(request);
			rsp.setResData(resut);
			rsp = new ResponseSimpleHelper<MobileDetailResult>().iniSuccessRsp(request, rsp);
		} catch (HttpBizException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			rsp = new ResponseSimpleHelper<MobileDetailResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			rsp = new ResponseSimpleHelper<MobileDetailResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}

	@RequestMapping(value = "/queryMobileDetail2" , method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<MobileDetailWithCountResult> queryMobileDetail2(MobileQueryDetailRequest request){
		MobileDetailWithCountResult result;
		ComResponse<MobileDetailWithCountResult> rsp = new ComResponse<MobileDetailWithCountResult>();

		try {
			result = service.queryMobileDetailWithCount(request);
			rsp.setResData(result);
			rsp = new ResponseSimpleHelper<MobileDetailWithCountResult>().iniSuccessRsp(request, rsp);
		} catch (HttpBizException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			rsp = new ResponseSimpleHelper<MobileDetailWithCountResult>().createComErrorRsp(request,null,e);
		} catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			rsp = new ResponseSimpleHelper<MobileDetailWithCountResult>().createComErrorRsp(request, null);
		}

		return rsp;
	}

	@RequestMapping(value = "/queryMoveDetailByMobile" , method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<MoveDetailResponse> queryMoveDetailByMobile(MobileQueryDetailRequest request){
		MoveDetailResponse result;
		ComResponse<MoveDetailResponse> rsp = new ComResponse<MoveDetailResponse>();

		try {
			result = service.queryMoveDetailByMobile(request);
			rsp.setResData(result);
			rsp = new ResponseSimpleHelper<MoveDetailResponse>().iniSuccessRsp(request,rsp);
		} catch (HttpBizException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			rsp = new ResponseSimpleHelper<MoveDetailResponse>().createComErrorRsp(request,null,e);
		} catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			rsp = new ResponseSimpleHelper<MoveDetailResponse>().createComErrorRsp(request, null);
		}

		return rsp;
	}

	@RequestMapping(value = "/queryCostDetailByMobile" , method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<CostDetailResponse> queryCostDetailByMobile(MobileQueryDetailRequest request){
		CostDetailResponse result;
		ComResponse<CostDetailResponse> rsp = new ComResponse<CostDetailResponse>();

		try {
			result = service.queryCostDetailByMobile(request);
			rsp.setResData(result);
			rsp = new ResponseSimpleHelper<CostDetailResponse>().iniSuccessRsp(request,rsp);
		} catch (HttpBizException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			rsp = new ResponseSimpleHelper<CostDetailResponse>().createComErrorRsp(request,null,e);
		} catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			rsp = new ResponseSimpleHelper<CostDetailResponse>().createComErrorRsp(request, null);
		}

		return rsp;
	}

	@RequestMapping(value = "/queryReportDetail" , method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<ReportDetailResponse> queryReportDetailByMobile(MobileQueryDetailRequest request){
		ReportDetailResponse result;
		ComResponse<ReportDetailResponse> rsp = new ComResponse<ReportDetailResponse>();

		try {
			result = service.queryReportDetailByMobile(request);
			rsp.setResData(result);
			rsp = new ResponseSimpleHelper<ReportDetailResponse>().iniSuccessRsp(request,rsp);
		} catch (HttpBizException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			rsp = new ResponseSimpleHelper<ReportDetailResponse>().createComErrorRsp(request,null,e);
		} catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			rsp = new ResponseSimpleHelper<ReportDetailResponse>().createComErrorRsp(request, null);
		}

		return rsp;
	}






	private void testk(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String bodyStr = this.getBodyData(request);
		System.out.println(bodyStr);
		return;
	}

	private String getBodyData(HttpServletRequest request) {
		StringBuffer data = new StringBuffer();
		String line = null;
		BufferedReader reader = null;
		try {
			reader = request.getReader();
			while (null != (line = reader.readLine()))
				data.append(line);
		} catch (IOException e) {
		} finally {
		}
		return data.toString();
	}

	/**
	 * 处理异常返回消息
	 *
	 * @return
	 */
	private MobileDetailResponse dealException(HttpBizException e) {
		MobileDetailResponse rsp = new MobileDetailResponse();
		e.printStackTrace();
		logger.error(e.getMessage(), e);
		rsp.setCode(e.getCode() != null ? e.getCode() : this.defaulErrorCode);
		rsp.setMsg(e.getMessage());
		return rsp;
	}

	/**
	 * 处理异常返回消息
	 *
	 * @return
	 */
	private MobileDetailResponse dealException(Exception e) {
		MobileDetailResponse rsp = new MobileDetailResponse();
		e.printStackTrace();
		logger.error(e.getMessage(), e);
		rsp.setCode(this.defaulErrorCode);
		rsp.setMsg(e.getMessage());
		return rsp;
	}

	/**
	 * 转换时间格式
	 * */
	public static Date formatDate(Date time) throws Exception {
		Date d = null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = sd.format(time);
		d = sd.parse(timeStr);
		Date newDate = d;
		return newDate;
	}

	public static int daysBetween(Date smdate,Date bdate) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		smdate=sdf.parse(sdf.format(smdate));
		bdate=sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}
}
