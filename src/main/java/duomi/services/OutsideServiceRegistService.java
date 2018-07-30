package duomi.services;

import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.param.BaseResponse;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dispatch.request.ComRequest;

import java.util.List;
import java.util.Map;

public interface OutsideServiceRegistService {

	/**
	 * 登记外部服务查询状态表
	 */
	public void insertCspStus(ComRequest request);

	/**
	 * 更新外部服务查询状态表 状态和记录请求返回消息
	 * 
	 * @param statPo
	 */
	public void updateCspStusBefore(ComRequest request);

	/**
	 * 更新外部服务查询状态表,异步获取结果情况,记录当前返回值
	 * @param request
	 * @param rspStr
	 */
	public void updateCspStusBefore(ComRequest request,String rspStr);
	/**
	 * 更新外部服务查询状态表 状态和记录请求返回消息
	 * updateCspStusAfter
	 * @param statPo
	 */
	public void updateCspStusAfter(ComRequest request, BaseResponse baseRsp);

	/**
	 * 接送到第三方服务前，更新状态以及记录流水信息
	 */
	public void updateCspStusAfter(ComRequest request, String rspStr);

	/**
	 * 异步返回结果情况更新外部服务查询状态表 状态和记录请求返回消息
	 * @param request
	 * @param rspStr
	 */
	public void updateCspStusAfterOfSync(ComRequest request, String rspStr);
	/**
	 * 接送到第三方服务错误信息时，更新状态以及记录流水信息
	 * 
	 * @param request
	 * @param rspStr
	 */
	public void updateCspStus4Error(ComRequest request, String rspStr);

	/**
	 * 异步返回结果情况,接送到第三方服务错误信息时，更新状态以及记录流水信息
	 * @param request
	 * @param rspStr
	 */
	public void updateCspStus4ErrorOfSync(ComRequest request, String rspStr);
	/**
	 * 更新外部服务查询状态表 状态和记录请求返回消息
	 * 
	 * @param statPo
	 */
	public void updateCspStus(CspInterfaceStatPoWithBLOBs statPo);

	/**
	 * 插入更新外部服务查询日志表
	 * 
	 * @param request
	 */
	public void insertCspLog(ComRequest request, String message, String msgType);

	/**
	 * 检查是否已发送第三方服务
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, Object> isRequested(ComRequest request) throws HttpBizException;

	/**
	 * 查询外部服务查询状态表
	 * 
	 * @param request
	 * @return
	 */
	public CspInterfaceStatPoWithBLOBs selectCsp(ComRequest request);

	/**
	 * 查询没有返回结果数据
	 * @return
	 */
	public List<CspInterfaceStatPoWithBLOBs> queryNoResultData();
}
