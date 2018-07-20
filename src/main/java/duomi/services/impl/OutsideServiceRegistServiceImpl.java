package duomi.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duomi.com.constants.PubConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.utils.DateUtil;
import duomi.com.utils.JSONUtils;
import duomi.dbMap.bean.CspInterfaceLogPo;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.mapper.CspInterfaceLogPoMapper;
import duomi.dbMap.mapper.CspInterfaceStatPoMapper;
import duomi.dispatch.request.ComRequest;
import duomi.services.OutsideServiceRegistService;

/**
 * 外部服务查询登记 服务
 * 
 * @author Administrator
 *
 */
@Service
public class OutsideServiceRegistServiceImpl implements OutsideServiceRegistService {
	@Autowired
	private CspInterfaceStatPoMapper cspstatDao;

	@Autowired
	private CspInterfaceLogPoMapper csplogDao;

	/**
	 * 收到调度请求是 登记外部服务查询状态表
	 */
	public void insertCspStus(ComRequest request) {

		CspInterfaceStatPoWithBLOBs statPo = new CspInterfaceStatPoWithBLOBs();

		BeanUtils.copyProperties(request, statPo);

		String reqmsg = JSONUtils.toJSONString(request);
		statPo.setInMessage(reqmsg);
		statPo.setInTime(DateUtil.getNow());

		statPo.setStatus(PubConstants.OUTSIDESRV_STATUS_RECEIVEDISPATCH); // 收到调度请求
		statPo.setDataValid(PubConstants.OUTSIDESRV_DATAVALID_VALID); // 有效

		int num = cspstatDao.insertWithoutKey(statPo);
		request.setInterId(statPo.getId());

		// 将调度请求信息插入日志表
		this.insertCspLog(request, reqmsg, PubConstants.ITF_LOG_MSGTYPE_RCVDISPATCHREQ);

	}

	public void updateCspStus(CspInterfaceStatPoWithBLOBs statPo) {

	}

	/**
	 * 请求第三方服务前，更新状态以及记录流水
	 */
	public void updateCspStusBefore(ComRequest request) {
		CspInterfaceStatPoWithBLOBs statPo = new CspInterfaceStatPoWithBLOBs();
		statPo.setId(request.getInterId());
		statPo.setStatus(PubConstants.OUTSIDESRV_STATUS_SENDMSG);
		cspstatDao.updateByPrimaryKeySelective(statPo);

		String reqmsg = JSONUtils.toJSONString(request);
		// 将发送的第三方服务请求信息插入日志表
		this.insertCspLog(request, reqmsg, PubConstants.ITF_LOG_MSGTYPE_RCVDISPATCHREQ);

	}

	/**
	 * 接送到第三方服务前，更新状态以及记录流水信息
	 */
	public void updateCspStusAfter(ComRequest request, BaseResponse baseRsp) {

		String jsondata = JSONUtils.toJSONString(baseRsp);

		CspInterfaceStatPoWithBLOBs retstatPo = new CspInterfaceStatPoWithBLOBs();
		retstatPo.setId(request.getInterId());
		retstatPo.setStatus(PubConstants.OUTSIDESRV_STATUS_RECEIVEDMSG);
		retstatPo.setRetMessage(jsondata);
		retstatPo.setRetTime(DateUtil.getNow());

		cspstatDao.updateByPrimaryKeySelective(retstatPo);

		// 将接受的第三方服务返回信息插入日志表
		this.insertCspLog(request, jsondata, PubConstants.ITF_LOG_MSGTYPE_RECEIVEMSG);
	}

	/**
	 * 接送到第三方服务前，更新状态以及记录流水信息
	 */
	public void updateCspStusAfter(ComRequest request, String rspStr) {

		String jsondata = rspStr;
		String jsondata_ct = duomi.com.utils.StringUtils.decodeUnicode(jsondata);

		CspInterfaceStatPoWithBLOBs retstatPo = new CspInterfaceStatPoWithBLOBs();
		retstatPo.setId(request.getInterId());
		retstatPo.setStatus(PubConstants.OUTSIDESRV_STATUS_RECEIVEDMSG);
		retstatPo.setRetMessage(jsondata);
		retstatPo.setRetMessageCt(jsondata_ct);
		retstatPo.setRetTime(DateUtil.getNow());

		cspstatDao.updateByPrimaryKeySelective(retstatPo);

		// 将接受的第三方服务返回信息插入日志表
		this.insertCspLog(request, jsondata, PubConstants.ITF_LOG_MSGTYPE_RECEIVEMSG);
	}

	/**
	 * 接送到第三方服务错误信息时，更新状态以及记录流水信息
	 */
	public void updateCspStus4Error(ComRequest request, String rspStr) {

		String jsondata = rspStr;
		String jsondata_ct = duomi.com.utils.StringUtils.decodeUnicode(jsondata);

		CspInterfaceStatPoWithBLOBs retstatPo = new CspInterfaceStatPoWithBLOBs();
		retstatPo.setId(request.getInterId());
		retstatPo.setStatus(PubConstants.OUTSIDESRV_STATUS_ERROR);
		retstatPo.setRetMessage(jsondata);
		retstatPo.setRetMessageCt(jsondata_ct);
		retstatPo.setRetTime(DateUtil.getNow());

		cspstatDao.updateByPrimaryKeySelective(retstatPo);

		// 将接受的第三方服务返回信息插入日志表
		this.insertCspLog(request, jsondata, PubConstants.ITF_LOG_MSGTYPE_RECEIVEMSG);
	}

	public void insertCspLog(ComRequest request, String message, String msgType) {
		CspInterfaceLogPo logPo = new CspInterfaceLogPo();
		logPo.setInterId(request.getInterId());
		logPo.setInterNo(request.getInterNo());
		logPo.setMobileNo(request.getMobile());
		logPo.setAppNo(request.getAppNo());

		logPo.setMessage(message);
		logPo.setMsgType(msgType);

		csplogDao.insertSelective(logPo);
	}

	/**
	 * 校验是否已请求查询第三方服务
	 * 
	 * @throws HttpBizException
	 */
	public Map<String, Object> isRequested(ComRequest request) throws HttpBizException {
		String appNo = request.getAppNo();
		if (StringUtils.isBlank(appNo)) {
			throw new HttpBizException("工单编号appNo不能为空");
		}

		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appNo", appNo);
		params.put("interNo", request.getInterNo());
		String interSerno = request.getInterSerno();
		if (org.apache.commons.lang.StringUtils.isNotBlank(interSerno))
			params.put("interSerno", request.getInterSerno());
		params.put("mobile", request.getMobile());
		params.put("status", PubConstants.OUTSIDESRV_STATUS_RECEIVEDMSG);// 收到返回
		CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) cspstatDao.selectByMap(params);
		if (cspStatPo != null) {
			retMap.put(PubConstants.CSP_STAT_ISEXISTS, true);
			retMap.put(PubConstants.CSP_STAT_PO, cspStatPo);
		} else {
			retMap.put(PubConstants.CSP_STAT_ISEXISTS, false);
		}
		return retMap;
	}

	/**
	 * 查询外部服务查询状态表
	 * 
	 * @param request
	 * @return
	 */
	public CspInterfaceStatPoWithBLOBs selectCsp(ComRequest request) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String appNo = request.getAppNo();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appNo", appNo);
		params.put("interNo", request.getInterNo());
		String interSerno = request.getInterSerno();
		if (org.apache.commons.lang.StringUtils.isNotBlank(interSerno))
			params.put("interSerno", request.getInterSerno());
		params.put("status", PubConstants.OUTSIDESRV_STATUS_RECEIVEDMSG);// 收到返回
		CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) cspstatDao.selectByMap(params);
		return cspStatPo;
	}

}
