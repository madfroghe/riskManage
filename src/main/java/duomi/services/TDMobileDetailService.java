package duomi.services;

import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.mobiledetail.MBDetailRequest;
import duomi.dbMap.bean.MobileDetailRecordWithCountPo;
import duomi.dispatch.request.MobileDetailRequest;
import duomi.dispatch.request.MobileQueryDetailRequest;
import duomi.dispatch.response.result.*;

public interface TDMobileDetailService {
	/**
	 * 处理通话详单
	 * 
	 * @param req
	 * @return
	 */
	public MobileDetailResponse startTesk(MobileDetailRequest req) throws HttpBizException;

	/**
	 * 提交短信验证码
	 * 
	 * @param req
	 * @return
	 * @throws HttpBizException
	 */
	public MobileDetailResponse sendTesk(MobileDetailRequest req) throws HttpBizException;

	/**
	 * 从同盾获取通话详单
	 * 
	 * @param req
	 */
	public String getMobileDetail(MBDetailRequest req);

	/**
	 * 从同盾获取联系人信息
	 * */
	public String getContactDetail(MBDetailRequest req) throws Exception;

	/**
	 * 对外提供通话详单查询服务
	 * 
	 * @param req
	 * @return
	 */
	public MobileDetailResult queryMobileDetail(MobileQueryDetailRequest req) throws HttpBizException;

	/**
	 * 对外提供通话详单查询服务（带统计）
	 * */
	public MobileDetailWithCountResult queryMobileDetailWithCount(MobileQueryDetailRequest req) throws HttpBizException;

	/**
	 * 对外提供运动轨迹记录(通过电话号码,查询到呼叫地址,根据地址变化,形成运动轨迹)
	 * */
	public MoveDetailResponse queryMoveDetailByMobile(MobileQueryDetailRequest req) throws HttpBizException;

	/**
	 * 对外提供每月通话记录查询
	 * **/
	public CostDetailResponse queryCostDetailByMobile(MobileQueryDetailRequest req) throws HttpBizException;

	/**
	 * 对外提供报告详情查询
	 * */
	public ReportDetailResponse queryReportDetailByMobile(MobileQueryDetailRequest req) throws HttpBizException;
}
