package duomi.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPInputStream;

import duomi.com.constants.MongoDbCollectionConstants;
import duomi.com.utils.GsonUtils;
import duomi.dbMap.bean.*;
import duomi.dbMap.mapper.CallDetailPoMapper;
import duomi.dbMap.mapper.TDReportDetailPoMapper;
import duomi.dispatch.response.result.*;
import duomi.mongodb.dao.Impl.MongodbBaseDao2Impl;
import duomi.mongodb.dao.Impl.TDCallDetailMGDaoImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duomi.com.constants.MobileRspCodeConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.helper.HttpUtil;
import duomi.com.httpIvk.param.mobiledetail.DBDetailCallRecord;
import duomi.com.httpIvk.param.mobiledetail.MBDetailRequest;
import duomi.com.httpIvk.param.mobiledetail.MBResetCodeRequest;
import duomi.com.httpIvk.param.mobiledetail.MbLoginRequest;
import duomi.com.httpIvk.param.mobiledetail.MbLoginResponse;
import duomi.com.httpIvk.param.mobiledetail.MbSendVerifRequest;
import duomi.com.httpIvk.param.mobiledetail.MbTeskRequest;
import duomi.com.httpIvk.param.mobiledetail.MbTeskResponse;
import duomi.com.utils.JSONUtils;
import duomi.dbMap.mapper.MobileDetailRecordPoMapper;
import duomi.dispatch.request.MobileDetailRequest;
import duomi.dispatch.request.MobileQueryDetailRequest;
import duomi.services.TDMobileDetailService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class TDMobileDetailServiceImpl implements TDMobileDetailService {

    private final static Logger logger = LoggerFactory.getLogger(TDMobileDetailServiceImpl.class);
    private static final String partner_code = "yijiejr";// 合作方标识
    private static final String partner_key = "504ab0a4300642b8a96c91951a19e513";// 合作方密钥

    /*
     * private static final String User_name = "1756711@qq.com";// 用户 private
     * static final String user_pass = "jczE99";// 密码
     */
    // https://api.shujumohe.com/octopus/task.unify.create/v3?partner_code=合作方标识&partner_key=合作方密钥
    private static final String Task_URL = "https://api.shujumohe.com/octopus/task.unify.create/v3?"; // 创建任务地址

    // https://api.shujumohe.com/octopus/yys.unify.acquire/v3?partner_code=合作方标识&partner_key=合作方密钥
    private static final String Login_URL = "https://api.shujumohe.com/octopus/yys.unify.acquire/v3?";// 登录验证

    // https://api.shujumohe.com/octopus/task.unify.query/v3?partner_code=合作方标识&partner_key=合作方密钥
    private static final String Detail_URL = "https://api.shujumohe.com/octopus/task.unify.query/v3?";// 查询任务结果

    private static final String Report_URL = "https://api.shujumohe.com/octopus/report.task.query/v2?";// 数据魔盒-任务报告接口

    // https://api.shujumohe.com/octopus/task.unify.retry/v3?partner_code=合作方标识&partner_key=合作方密钥
    private static final String ResetCode_URL = "https://api.shujumohe.com/octopus/task.unify.retry/v3?"; // 重试验证码



    // 首次调用登录验证的task_stage是"INIT"
    private static final String TASK_STAGE_INIT = "INIT";// 请求阶段，

    // 请求类型。提交是"submit"，轮询是"query"。首先用"submit"提交手机号码和服务密码，然后用"query"查询，直到返回码不是100。
    private static final String REQUEST_TYPE_SUBMIT = "submit";// 请求类型
    private static final String REQUEST_TYPE_QUERY = "query";// 请求类型

    /**
     * 手机通话详情
     */
    @Autowired
    private MobileDetailRecordPoMapper MobileDetailDao;

    @Autowired
    private Mapper dozsermapper;

    @Autowired
    private CallDetailPoMapper callDetailDao;

    @Autowired
    private TDCallDetailMGDaoImpl tdCallDetailMGDao;

    @Autowired
    private MobileDetailRecordPoMapper mobileDetailRecordDao;

    @Autowired
    private MongodbBaseDao2Impl mongodbBaseDao2;

    @Autowired
    private TDReportDetailPoMapper tdReportDetailPoDao;

    /**
     * 初始化url
     *
     * @param url
     * @return
     */
    public String createUrl(String url) {
        String urlstr = url + "partner_code=" + partner_code + "&partner_key=" + partner_key;
        return urlstr;
    }

    /**
     * 开始处理通话详单
     *
     * @throws HttpBizException
     */
    public MobileDetailResponse startTesk(MobileDetailRequest req) throws HttpBizException {
        // 1.创建任务编号
        MbTeskResponse taskrsp = this.createTask(req);
        MobileDetailResponse mbRsp = new MobileDetailResponse();
        int code = taskrsp.getCode();// 返回码
        if (MobileRspCodeConstants.TASK_CODE_0 == code) { // 创建任务成功
            String taskid = taskrsp.getTask_id();// 任务编号
            req.setTask_id(taskid);
            MbLoginResponse loginRsp = this.login(req);
            while (true) {
                int loginCode = loginRsp.getCode();
                String loginCodeStr = String.valueOf(loginCode);
                String msg = loginRsp.getMessage();
                mbRsp.setCode(loginCodeStr);
                mbRsp.setMsg(msg);
                mbRsp.setTask_id(taskid);
                if (loginRsp.getData() != null)
                    mbRsp.setNext_stage(loginRsp.getData().getNext_stage());
                sleep(5000);

                switch (loginCode) {
                    case 100:// code为100时重复调用当前阶段的query查询
                        loginRsp = this.queryTaskStatus(req);
                        continue;
                    case 137: // 任务已成功提交
                        mbRsp.setCode("000000");
                        mbRsp.setCode("成功");
                        return mbRsp;
                    case 2007:
                        mbRsp.setCode("000000");
                        mbRsp.setCode("成功");
                        return mbRsp;
                    default:
                        return mbRsp;
                }
            }

        } else {
            throw new HttpBizException(String.valueOf(code), taskrsp.getMessage());
        }
    }

    /**
     * 提交短信验证码
     *
     * @throws HttpBizException
     */
    public MobileDetailResponse sendTesk(MobileDetailRequest req) throws HttpBizException {
        String taskid = req.getTask_id();
        if (StringUtils.isBlank(taskid)) {
            throw new HttpBizException("未传入任务编号");
        }

        MobileDetailResponse mbRsp = new MobileDetailResponse();

        MbLoginResponse loginRsp = this.sendVerificationCode(req);

        while (true) {
            int loginCode = loginRsp.getCode();
            String loginCodeStr = String.valueOf(loginCode);
            String msg = loginRsp.getMessage();
            mbRsp.setCode(loginCodeStr);
            mbRsp.setMsg(msg);
            mbRsp.setTask_id(taskid);
            if (loginRsp.getData() != null)
                mbRsp.setNext_stage(loginRsp.getData().getNext_stage());
            sleep(5000);

            switch (loginCode) {
                case 100:// code为100时重复调用当前阶段的query查询
                    loginRsp = this.queryTaskStatus(req);
                    continue;
                case 137: // 任务已成功提交
                    mbRsp.setCode("000000");
                    mbRsp.setMsg("任务已成功提交");
                    return mbRsp;
                case 2007:
                    mbRsp.setCode("000000");
                    mbRsp.setMsg("任务已成功提交");
                    return mbRsp;
                case 124:
                    MbLoginResponse rsp = this.ResetCode(req);
                    if (rsp != null) {
                        mbRsp.setResetCode(String.valueOf(rsp.getCode()));
                        mbRsp.setResetMsg(rsp.getMessage());
                        mbRsp.setTask_id(taskid);
                        if (rsp.getData() != null)
                            mbRsp.setNext_stage(rsp.getData().getNext_stage());
                    }
                    mbRsp.setMsg("手机短信验证码错误或过期，已重新发送短信，稍后请重新输入");
                    return mbRsp;
                default:
                    return mbRsp;
            }
        }

    }

    // 设置线程睡眠时间
    private static void sleep(int l) {
        try {
            Thread.sleep(l);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建任务
     */
    public MbTeskResponse createTask(MobileDetailRequest req) {

        MbTeskRequest teskReq = new MbTeskRequest();
        teskReq.setReal_name(req.getName()); // 姓名
        teskReq.setUser_mobile(req.getMobile());// 手机
        teskReq.setIdentity_code(req.getIdcard());// 身份证
        teskReq.setReal_name(req.getName());

        String url = this.createUrl(this.Task_URL);
        String reqJson = JSONUtils.toJSONString(teskReq);
        logger.info("通话详单，创建任务请求消息：" + reqJson);
        List<NameValuePair> list = JSONUtils.toNameValuePairList(teskReq);
        String jsonStr = HttpUtil.post(url, list);
        logger.info("通话详单，创建任务返回消息：" + jsonStr);
        MbTeskResponse taskRsp = JSONUtils.toBean(jsonStr, MbTeskResponse.class);
        return taskRsp;
    }

    /**
     * 发起登录
     */
    public MbLoginResponse login(MobileDetailRequest req) {

        MbLoginRequest loginReq = new MbLoginRequest();
        loginReq.setTask_id(req.getTask_id());
        loginReq.setUser_name(req.getMobile()); // 手机号
        loginReq.setUser_pass(req.getMbpasswd()); // 手机服务密码
        loginReq.setTask_stage(this.TASK_STAGE_INIT);// 请求阶段，首次调用登录验证的task_stage是"INIT"
        loginReq.setRequest_type(this.REQUEST_TYPE_SUBMIT);// 必填请求类型。提交是"submit"，轮询是"query"。首先用"submit"提交手机号码和服务密码，然后用"query"查询，直到返回码不是100。

        String url = this.createUrl(this.Login_URL);
        String reqJson = JSONUtils.toJSONString(loginReq);
        logger.info("通话详单，发起登录请求消息：" + reqJson);
        List<NameValuePair> list = JSONUtils.toNameValuePairList(loginReq);
        String jsonStr = HttpUtil.post(url, list);
        logger.info("通话详单，发起登录返回消息：" + jsonStr);
        MbLoginResponse loginRsp = JSONUtils.toBean(jsonStr, MbLoginResponse.class);
        return loginRsp;
    }

    /**
     * 查询任务状态
     */
    public MbLoginResponse queryTaskStatus(MobileDetailRequest req) {
        MbLoginRequest loginReq = new MbLoginRequest();
        loginReq.setTask_id(req.getTask_id());
        loginReq.setUser_name(req.getMobile()); // 手机号
        loginReq.setUser_pass(req.getMbpasswd()); // 手机服务密码

        loginReq.setTask_stage(this.TASK_STAGE_INIT);// 请求阶段，首次调用登录验证的task_stage是"INIT"
        loginReq.setRequest_type(this.REQUEST_TYPE_QUERY);// 必填请求类型。提交是"submit"，轮询是"query"。首先用"submit"提交手机号码和服务密码，然后用"query"查询，直到返回码不是100。

        String url = this.createUrl(this.Login_URL);
        String reqJson = JSONUtils.toJSONString(loginReq);
        logger.info("通话详单，查询任务状态请求消息：" + reqJson);
        List<NameValuePair> list = JSONUtils.toNameValuePairList(loginReq);
        String jsonStr = HttpUtil.post(url, list);
        logger.info("通话详单，查询任务状态返回消息：" + jsonStr);
        MbLoginResponse loginRsp = JSONUtils.toBean(jsonStr, MbLoginResponse.class);
        return loginRsp;
    }

    /**
     * 重置验证码
     *
     * @param req
     * @return
     */
    public MbLoginResponse ResetCode(MobileDetailRequest req) {

        MBResetCodeRequest resetReq = new MBResetCodeRequest();
        resetReq.setTask_id(req.getTask_id());

        String url = this.createUrl(this.ResetCode_URL);
        String reqJson = JSONUtils.toJSONString(resetReq);
        logger.info("通话详单，重置验证码请求消息：" + reqJson);
        List<NameValuePair> list = JSONUtils.toNameValuePairList(resetReq);
        String jsonStr = HttpUtil.post(url, list);
        logger.info("通话详单，重置验证码返回消息：" + jsonStr);
        MbLoginResponse loginRsp = JSONUtils.toBean(jsonStr, MbLoginResponse.class);
        return loginRsp;
    }

    /**
     * 提交验证码
     */
    public MbLoginResponse sendVerificationCode(MobileDetailRequest req) {

        MbSendVerifRequest verifReq = new MbSendVerifRequest();
        verifReq.setTask_id(req.getTask_id());
        verifReq.setSms_code(req.getShortMsgCode()); // 短信验证码
        verifReq.setAuth_code(req.getPictureCode()); // 图片验证码
        verifReq.setTask_stage(req.getNext_stage()); // 任务阶段
        verifReq.setRequest_type(this.REQUEST_TYPE_SUBMIT);// 请求类型

        String url = this.createUrl(this.Login_URL);
        String reqJson = JSONUtils.toJSONString(verifReq);
        logger.info("通话详单，提交验证码请求消息：" + reqJson);
        List<NameValuePair> list = JSONUtils.toNameValuePairList(verifReq);
        String jsonStr = HttpUtil.post(url, list);
        logger.info("通话详单，提交验证码返回消息：" + jsonStr);
        MbLoginResponse loginRsp = JSONUtils.toBean(jsonStr, MbLoginResponse.class);
        return loginRsp;
    }

    /**
     * 从同盾获取通话详单
     */
    public String getMobileDetail(MBDetailRequest req) {
        String url = this.createUrl(this.Detail_URL);
        String reqJson = JSONUtils.toJSONString(req);
        String task_id = req.getTask_id();
        logger.info("通话详单" + task_id + "，查询通话详单结果请求消息：" + reqJson);
        List<NameValuePair> list = JSONUtils.toNameValuePairList(req);
        String jsonStr = HttpUtil.post(url, list);
        logger.info("通话详单" + task_id + "，查询通话详单结果返回消息：" + jsonStr);


        // 通话详单保存入库
        JSONObject resultRsp = JSONObject.fromObject(jsonStr);
        JSONObject data = resultRsp.getJSONObject("data");
        if (data != null) {
            JSONObject task_data = data.getJSONObject("task_data");
            if (task_data != null) {
                JSONArray call_infos = task_data.getJSONArray("call_info");
                if (call_infos != null) {
                    Iterator call_info_it = call_infos.iterator();
                    while (call_info_it.hasNext()) {
                        JSONObject call_info = (JSONObject) call_info_it.next();
                        JSONArray call_records = call_info.getJSONArray("call_record");
                        if (call_records != null) {
                            Iterator call_record_it = call_records.iterator();
                            while (call_record_it.hasNext()) {
                                JSONObject call_record = (JSONObject) call_record_it.next();
                                DBDetailCallRecord call_record_bean = JSONUtils.toBean(call_record,
                                        DBDetailCallRecord.class);
                                MobileDetailRecordPo mbPo = dozsermapper.map(call_record_bean,
                                        MobileDetailRecordPo.class);
                                mbPo.setMobile(data.getString("user_mobile"));
                                mbPo.setTaskId(task_id);
                                MobileDetailDao.insertSelective(mbPo);
                            }
                        }
                    }
                }

            }
        }

        //通过taskId获取刚保存的通话详单,将一些信息保存到mongdb
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("taskId", task_id);
        MobileDetailRecordPo latelyRecord = mobileDetailRecordDao.queryLatelyTaskInfoByMap(paramMap);

        JSONObject mgJson = new JSONObject();
        mgJson.put("taskId", task_id);
        mgJson.put("reqJson", reqJson);
        mgJson.put("resJson", jsonStr);
        if (latelyRecord != null) {
            mgJson.put("mobile", latelyRecord.getMobile());
            try {
                mgJson.put("time", sd.format(latelyRecord.getCreateTime()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_TD_MOBILEDETAIL);


        //将通话详单保存到Mysql数据库
        CallDetailPoWithBLOBs callDetail = new CallDetailPoWithBLOBs();
        callDetail.setTaskId(task_id);
        callDetail.setReqJson(reqJson);
        callDetail.setResJson(JSONUtils.toJSONObject(jsonStr).toString());
        if (data == null) {
            callDetail.setStatusType("02");
        } else {
            callDetail.setStatusType("01");
        }
        callDetailDao.insert(callDetail);

        return jsonStr;
    }

    /**
     * 从同盾获取联系人详情
     * */
    public String getContactDetail(MBDetailRequest req) throws Exception{
        String url = this.createUrl(this.Report_URL);
        String reqJson = JSONUtils.toJSONString(req);
        String task_id = req.getTask_id();
        logger.info("数据魔盒-魔方报告:" + task_id + "，查询魔方报告请求消息：" + reqJson);
        List<NameValuePair> list = JSONUtils.toNameValuePairList(req);
        String jsonStr = HttpUtil.post(url, list);
        logger.info("数据魔盒-魔方报告:" + task_id + "，查询魔方报告结果返回消息：" + jsonStr);

        JSONObject resultRsp = JSONObject.fromObject(jsonStr);
        String data = resultRsp.getString("data");

        if(data != null){
            //报告数据解压缩
            String dataJson = this.gunzip(data);
            JSONObject dataJsonObject = JSONUtils.toJSONObject(dataJson);

            JSONArray riskContactStats = dataJsonObject.getJSONArray("risk_contact_stats");  //风险联系人统计
            JSONArray riskContactDetail = dataJsonObject.getJSONArray("risk_contact_detail"); //风险联系人明细
            JSONArray financeContactStats = dataJsonObject.getJSONArray("finance_contact_stats"); //金融机构联系人统计
            JSONArray financeContactDetail = dataJsonObject.getJSONArray("finance_contact_detail"); //金融机构联系人明细

            JSONObject userInfo = dataJsonObject.getJSONObject("user_info");
            String userName = userInfo.getString("real_name");  //用户名
            String certNo = userInfo.getString("identity_code"); //用户身份证

            JSONObject mobileInfo = dataJsonObject.getJSONObject("mobile_info");
            String userMobile = mobileInfo.getString("user_mobile"); //用户手机号

            TDReportDetailPoWithBLOBs reportDetailPoWithBLOBs = new TDReportDetailPoWithBLOBs();
            reportDetailPoWithBLOBs.setName(userName);
            reportDetailPoWithBLOBs.setCertNo(certNo);
            reportDetailPoWithBLOBs.setMobile(userMobile);
            reportDetailPoWithBLOBs.setRiskContactStats(riskContactStats.toString());
            reportDetailPoWithBLOBs.setRiskContactDetail(riskContactDetail.toString());
            reportDetailPoWithBLOBs.setFinanceContactStats(financeContactStats.toString());
            reportDetailPoWithBLOBs.setFinanceContactDetail(financeContactDetail.toString());

            //将风险联系人统计,风险联系人明细,金融机构联系人统计,金融机构联系人明细保存进mysql
            tdReportDetailPoDao.insertSelective(reportDetailPoWithBLOBs);

            //将风险联系人统计,风险联系人明细,金融机构联系人统计,金融机构联系人明细保存进mongodb
            JSONObject mgJson = new JSONObject();
            mgJson.put("userName",userName);
            mgJson.put("certNo",certNo);
            mgJson.put("mobile",userMobile);
            mgJson.put("riskContactStats",riskContactStats);
            mgJson.put("riskContactDetail",riskContactDetail);
            mgJson.put("financeContactStats",financeContactStats);
            mgJson.put("financeContactDetail",financeContactDetail);

            mongodbBaseDao2.save(mgJson,MongoDbCollectionConstants.T_DM_CSP_TD_REPORT);

        }

        return jsonStr;
    }

    /**
     * 对外提供通话详单查询服务
     *
     * @param req
     * @return
     * @throws HttpBizException
     */
    public MobileDetailResult queryMobileDetail(MobileQueryDetailRequest req) throws HttpBizException {
        MobileDetailResult result = new MobileDetailResult();
        String mobile = req.getMobile();
        if (StringUtils.isBlank(mobile)) {
            throw new HttpBizException("手机号不能为空");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mobile", mobile);
        MobileDetailRecordPo mdPo = MobileDetailDao.queryLatelyTaskByMap(params);
        if (mdPo == null) {
            throw new HttpBizException("无此手机通话详单");
        }
        params.put("taskId", mdPo.getTaskId()); // 设置任务编号

        List<MobileDetailRecordPo> records = MobileDetailDao.querylistByMap(params);
        result.setRecords(records);
        return result;
    }

    /**
     * 对外提供通话详单查询服务,带统计
     */
    public MobileDetailWithCountResult queryMobileDetailWithCount(MobileQueryDetailRequest req) throws HttpBizException {
        MobileDetailWithCountResult result = new MobileDetailWithCountResult();
        String mobile = req.getMobile();
        if (StringUtils.isBlank(mobile)) {
            throw new HttpBizException("手机号不能为空");
        }

        List<MobileDetailRecordWithCountPo> records = MobileDetailDao.queryListByMobile(req.getMobile());
        if(records.size() != 0){
            for(MobileDetailRecordWithCountPo record : records){
                int secondTime = record.getCallTime();  //单位为秒的时间
                record.setCallTime((int)(Math.ceil(secondTime/60.0)));
            }
        }
        result.setMobileDetailRecordWithCountPos(records);

        return result;
    }


    /**
     * 对外提供运动轨迹记录(通过电话号码,查询到呼叫地址,根据地址变化,形成运动轨迹)
     */
    public MoveDetailResponse queryMoveDetailByMobile(MobileQueryDetailRequest req) throws HttpBizException {
        MoveDetailResponse result = new MoveDetailResponse();
        List<MoveDetailHasEndTime> lastRecords = new ArrayList<MoveDetailHasEndTime>();   //最终处理的结果,传递给互金的
        String mobile = req.getMobile();
        if (StringUtils.isBlank(mobile)) {
            throw new HttpBizException("手机号不能为空");
        }


        List<MoveDetail> records = MobileDetailDao.queryMoveDetailListByMobile(mobile);  //从数据库中获取轨迹数据

        lastRecords = this.doMoveRecordsFromDataBase(records);
        result.setMoveDetailList(lastRecords);

        return result;
    }

    /**
     * 对外提供每月通话记录查询
     **/
    public CostDetailResponse queryCostDetailByMobile(MobileQueryDetailRequest req) throws HttpBizException {
        CostDetailResponse result = new CostDetailResponse();

        String mobile = req.getMobile();
        if (StringUtils.isBlank(mobile)) {
            throw new HttpBizException("手机号不能为空");
        }

        List<CostDetail> records = MobileDetailDao.queryMonthCostDetailByMobile(mobile);
        result.setCostDetailList(records);

        return result;
    }

    /**
     * 对外提供报告详情查询
     * */
    public ReportDetailResponse queryReportDetailByMobile(MobileQueryDetailRequest req) throws HttpBizException {
        ReportDetailResponse result = new ReportDetailResponse();
        List<RiskContactStats> riskContactStatsList = new ArrayList<RiskContactStats>();
        List<RiskContactDetail> riskContactDetailList = new ArrayList<RiskContactDetail>();

        String mobile = req.getMobile();
        if (StringUtils.isBlank(mobile)) {
            throw new HttpBizException("手机号不能为空");
        }

        List<TDReportDetailPoWithBLOBs> tdReportDetailPoWithBLOBsList = tdReportDetailPoDao.queryReportDetailByMobile(mobile);

        if(tdReportDetailPoWithBLOBsList.size() != 0){
            TDReportDetailPoWithBLOBs tdReportDetailPoWithBLOBs = tdReportDetailPoWithBLOBsList.get((tdReportDetailPoWithBLOBsList.size()-1));
            String riskContactStats = tdReportDetailPoWithBLOBs.getRiskContactStats();  //风险联系人统计
            String riskContactDetail = tdReportDetailPoWithBLOBs.getRiskContactDetail(); //风险联系人明细


            JSONArray riskContactStatsArray  = JSONUtils.toJSONArray(riskContactStats);//风险联系人统计JSONArray
            JSONArray riskContactDetailArray = JSONUtils.toJSONArray(riskContactDetail); //风险联系人明细JSONArray


            if(riskContactStatsArray.size() != 0){
                for(int i = 0 ; i < riskContactStatsArray.size() ; i++){
                    JSONObject riskContactStatsObject = riskContactStatsArray.getJSONObject(i);
                    RiskContactStats riskContactStatsIndex = (RiskContactStats)JSONObject.toBean(riskContactStatsObject,RiskContactStats.class);
                    riskContactStatsList.add(riskContactStatsIndex);
                }
            }

            if(riskContactDetailArray.size() != 0){
                for(int i = 0 ; i < riskContactDetailArray.size() ; i++){
                    JSONObject riskContactDetailObject = riskContactDetailArray.getJSONObject(i);
                    RiskContactDetail riskContactDetailIndex = (RiskContactDetail)JSONObject.toBean(riskContactDetailObject,RiskContactDetail.class);

                    String callTime1Mth = riskContactDetailIndex.getCall_time_1month();  //一个月通话时长
                    String callTime3Mth = riskContactDetailIndex.getCall_time_3month();  //三个月通话时长
                    String active3month = riskContactDetailIndex.getCall_time_active_3month();  //三个月主叫时长
                    String passive3month = riskContactDetailIndex.getCall_time_passive_3month();  //三个月被叫时长

                    double callTime1MthDouble = Double.parseDouble(callTime1Mth);
                    String call1MthTime = String.valueOf(new BigDecimal(callTime1MthDouble/60.0).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());  //转换为以分为单位

                    double callTime3MthDouble = Double.parseDouble(callTime3Mth);
                    String call3MthTime = String.valueOf(new BigDecimal(callTime3MthDouble/60.0).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());  //转换为以分为单位

                    double active3monthDouble = Double.parseDouble(active3month);
                    String active3MthTime = String.valueOf(new BigDecimal(active3monthDouble/60.0).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());  //转换为以分为单位

                    double passive3monthDouble = Double.parseDouble(passive3month);
                    String passive3MthTime = String.valueOf(new BigDecimal(passive3monthDouble/60.0).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()); //转换为以分为单位

                    if("0.0".equals(call1MthTime)){
                        riskContactDetailIndex.setCall_time_1month("0");
                    }else{
                        riskContactDetailIndex.setCall_time_1month(String.valueOf((int)Math.ceil(Double.parseDouble(call1MthTime))));
                    }

                    if("0.0".equals(call3MthTime)){
                        riskContactDetailIndex.setCall_time_3month("0");
                    }else{
                        riskContactDetailIndex.setCall_time_3month(String.valueOf((int)Math.ceil(Double.parseDouble(call3MthTime))));
                    }

                    if("0.0".equals(active3MthTime)){
                        riskContactDetailIndex.setCall_time_active_3month("0");
                    }else{
                        riskContactDetailIndex.setCall_time_active_3month(String.valueOf((int)Math.ceil(Double.parseDouble(active3MthTime))));
                    }

                    if("0.0".equals(passive3MthTime)){
                        riskContactDetailIndex.setCall_time_passive_3month("0");
                    }else{
                        riskContactDetailIndex.setCall_time_passive_3month(String.valueOf((int)Math.ceil(Double.parseDouble(passive3MthTime))));
                    }
                    riskContactDetailList.add(riskContactDetailIndex);
                }
            }

        }

        result.setRiskContactStatsList(riskContactStatsList);
        result.setRiskContactDetailList(riskContactDetailList);
        return result;
    }

    public MobileDetailRecordPoMapper getMobileDetailDao() {
        return MobileDetailDao;
    }

    public void setMobileDetailDao(MobileDetailRecordPoMapper mobileDetailDao) {
        MobileDetailDao = mobileDetailDao;
    }

    /**
     * 处理从数据库中取出来的移动轨迹数据
     */
    private List<MoveDetailHasEndTime> doMoveRecordsFromDataBase(List<MoveDetail> records) {
        List<MoveDetailHasEndTime> lastRecords = new ArrayList<MoveDetailHasEndTime>();


        for (int i = 0; i < records.size(); i++) {
            MoveDetailHasEndTime moveDetailHasEndTime = new MoveDetailHasEndTime();

            MoveDetail moveDetail1 = records.get(i);

            moveDetailHasEndTime.setCityEndTime(moveDetail1.getCallStartTime());
            moveDetailHasEndTime.setCityAddress(moveDetail1.getCallAddress());

            for (int j = i + 1; j < records.size(); j++) {
                MoveDetail moveDetail2 = records.get(j);
                if (!moveDetail1.getCallAddress().equals(moveDetail2.getCallAddress())) {
                    moveDetailHasEndTime.setCityStartTime(moveDetail2.getCallStartTime());
                    i = j - 1;
                    lastRecords.add(moveDetailHasEndTime);
                    break;
                }

                if (j == (records.size() - 1)) {
                    moveDetailHasEndTime.setCityStartTime(moveDetail2.getCallStartTime());
                    lastRecords.add(moveDetailHasEndTime);
                    i = j - 1;
                }
            }
        }
        return lastRecords;
    }


    /**
     * 数据魔盒报告解压缩
     * */
    private String gunzip(String data){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {

            // 对返回数据BASE64解码
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(data);
            in = new ByteArrayInputStream(compressed); ginzip = new GZIPInputStream(in);
            // 解码后对数据gzip解压缩
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            // 最后对数据进行utf-8转码
            decompressed = out.toString("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return decompressed;
    }
}
