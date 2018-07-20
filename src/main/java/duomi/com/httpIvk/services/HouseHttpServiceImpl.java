package duomi.com.httpIvk.services;

import java.util.List;

import duomi.dbMap.bean.FJWAuthorizationPo;
import duomi.dbMap.mapper.FJWAuthorizationPoMapper;
import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duomi.com.constants.HouseRspConstants;
import duomi.com.constants.PubConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.GenData.GenTestData;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.com.httpIvk.helper.HttpUtil;
import duomi.com.httpIvk.house.param.CityInfoInput;
import duomi.com.httpIvk.house.param.CityRegionsInput;
import duomi.com.httpIvk.house.param.CityRegionsResult;
import duomi.com.httpIvk.house.param.HouseBaseInput;
import duomi.com.httpIvk.house.param.HouseBaseResponse;
import duomi.com.httpIvk.house.param.HouseInfoInput;
import duomi.com.httpIvk.house.param.HouseListResponse;
import duomi.com.httpIvk.house.param.HousePriceResult;
import duomi.com.httpIvk.house.param.HouseRentInput;
import duomi.com.httpIvk.house.param.HouseRentResult;
import duomi.com.httpIvk.house.param.HouseToken;
import duomi.com.httpIvk.house.param.HouseUseReq;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.JSONUtils;
import duomi.dispatch.request.house.HousePriceRequest;
import duomi.dispatch.request.house.HouseRentRequest;
import duomi.services.OutsideServiceRegistService;

@Service
public class HouseHttpServiceImpl implements HouseHttpService {

	private final static Logger logger = LoggerFactory.getLogger(HttpIvkHelper.class);

	public static final String HOUSE_URL = "http://open.fangjia.com";

	public static final String url_token = "/accessToken"; // 授权接口

	public static final String url_city = "/city/list"; // 城市列表查询

	public static final String url_regions = "/city/regions"; // 城市区域查询接口

	public static final String url_houseprice = "/property/evaluate"; // 估价接口

	public static final String url_houserent = "/property/rent"; // 小区租金接口

	@Autowired
	private OutsideServiceRegistService regitSrv;

	@Autowired
	private FJWAuthorizationPoMapper fjwAuthorizationPoMapper;

	public String getTokenFromWeb() throws Exception {
//		String apiUrl = this.HOUSE_URL + this.url_token;
//		HouseUseReq houseUser = new HouseUseReq();
//
//		List<NameValuePair> list = JSONUtils.toNameValuePairList(houseUser);
//		String reqstr = JSONUtils.toJSONString(houseUser);
//		logger.info("房价网授权请求报文：" + reqstr);
//
//		String retJson = HttpUtil.post(apiUrl, list);
//		logger.info("房价网授权返回报文：" + retJson + "\n,请求报文:" + reqstr);
//
//		GsonUtils<HouseToken> gson = new GsonUtils<HouseToken>();
//		HouseBaseResponse<HouseToken> output = (HouseBaseResponse<HouseToken>) gson.fromJson(retJson,
//				HouseBaseResponse.class, HouseToken.class);
//		String token = null;
//
//		if (output == null) {
//			logger.error("房价网授权交易失败");
//			throw new HttpBizException("房价网授权交易失败");
//		}
//		if (HouseRspConstants.CODE_SUCCESS.equals(output.getCode())) {
//			token = output.getResult().getToken();
//		} else {
//			logger.error("房价错误代码:" + output.getCode() + "错误描述：" + output.getMsg() + ",请求报文：" + reqstr);
//			throw new HttpBizException(output.getCode(), output.getMsg());
//		}

		FJWAuthorizationPo fjwAuthorizationPo = fjwAuthorizationPoMapper.getFJWAuthorizationPoByType("01");
        String token = fjwAuthorizationPo.getToken();
		return token;
	}

	public HouseListResponse<CityInfoInput> getCityList() throws Exception {
		String apiUrl = this.HOUSE_URL + this.url_city;
		HouseBaseInput houseBase = new HouseBaseInput();
		String token = this.getTokey();
		houseBase.setToken(token);

		List<NameValuePair> list = JSONUtils.toNameValuePairList(houseBase);
		String reqstr = JSONUtils.toJSONString(houseBase);
		logger.info("房价城市列表请求报文：" + reqstr);

		String retJson = HttpUtil.post(apiUrl, list);
		logger.info("房价城市列表返回报文：" + retJson + "\n,请求报文:" + reqstr);

		GsonUtils<CityInfoInput> gson = new GsonUtils<CityInfoInput>();
		HouseListResponse<CityInfoInput> output = (HouseListResponse<CityInfoInput>) gson.fromJson(retJson,
				HouseListResponse.class, CityInfoInput.class);

		if (output == null) {
			logger.error("房价城市列表交易失败");
			throw new HttpBizException("房价城市列表交易失败");
		} else if (!HouseRspConstants.CODE_SUCCESS.equals(output.getCode())) {
			logger.error("房价错误代码:" + output.getCode() + "错误描述：" + output.getMsg() + ",请求报文：" + reqstr);
			throw new HttpBizException(output.getCode(), output.getMsg());
		}
		return output;
	}

	public HouseListResponse<CityRegionsResult> getCityArea(String city) throws Exception {

		String apiUrl = this.HOUSE_URL + this.url_regions;
		CityRegionsInput cityRegionsRsp = new CityRegionsInput();
		cityRegionsRsp.setCity(city);
		String token = this.getTokey();
		cityRegionsRsp.setToken(token);

		List<NameValuePair> list = JSONUtils.toNameValuePairList(cityRegionsRsp);
		String reqstr = JSONUtils.toJSONString(cityRegionsRsp);
		logger.info("房价城市区域列表请求报文：" + reqstr);

		String retJson = HttpUtil.post(apiUrl, list);
		logger.info("房价城市区域列表返回报文：" + retJson + "\n,请求报文:" + reqstr);

		GsonUtils<CityRegionsResult> gson = new GsonUtils<CityRegionsResult>();
		HouseListResponse<CityRegionsResult> output = (HouseListResponse<CityRegionsResult>) gson.fromJson(retJson,
				HouseListResponse.class, CityRegionsResult.class);

		if (output == null) {
			logger.error("房价城市列表交易失败");
			throw new HttpBizException("房价城市列表交易失败");
		} else if (!HouseRspConstants.CODE_SUCCESS.equals(output.getCode())) {
			logger.error("房价错误代码:" + output.getCode() + "错误描述：" + output.getMsg() + ",请求报文：" + reqstr);
			throw new HttpBizException(output.getCode(), output.getMsg());
		}
		return output;
	}

	/**
	 * 房屋估价接口
	 */
	public HouseBaseResponse<HousePriceResult> getHousePrice(HousePriceRequest houseReq) throws Exception {

		String apiUrl = this.HOUSE_URL + this.url_houseprice;
		HouseBaseResponse<HousePriceResult> output = null;
		// 更新登记表状态为发送，
		regitSrv.updateCspStusBefore(houseReq);

		String mobile = houseReq.getMobile();
		String appNo = houseReq.getAppNo();

		if (PubConstants.ZBADATA_SWITCH_ON.equals(PubConstants.ZBADATA_SWITCH)) {
			HouseInfoInput houseInfo = new HouseInfoInput();
			BeanUtils.copyProperties(houseReq, houseInfo);

			String token = this.getTokey();
			houseInfo.setToken(token);

			List<NameValuePair> list = JSONUtils.toNameValuePairList(houseInfo);
			String reqstr = JSONUtils.toJSONString(houseInfo);
			logger.info("工单编号：" + appNo + ",手机号:" + mobile + ",房价估值接口请求报文：" + reqstr);

			String retJson = HttpUtil.post(apiUrl, list);
			logger.info("工单编号：" + appNo + ",手机号:" + mobile + ",房价估值接口返回报文：" + retJson + "\n,房价估值请求报文:" + reqstr);

			GsonUtils<HousePriceResult> gson = new GsonUtils<HousePriceResult>();
			output = (HouseBaseResponse<HousePriceResult>) gson.fromJson(retJson, HouseBaseResponse.class,
					HousePriceResult.class);

			if (output == null) {
				logger.error("工单编号：" + appNo + ",手机号:" + mobile + "房价估值接口交易失败");
				throw new HttpBizException("房价估值接口交易失败");
			} else if (!HouseRspConstants.CODE_SUCCESS.equals(output.getCode())) {
				logger.error("工单编号：" + appNo + ",手机号:" + mobile + "房价错误代码:" + output.getCode() + "错误描述："
						+ output.getMsg() + ",请求报文：" + reqstr);
				throw new HttpBizException(output.getCode(), output.getMsg());
			}
		} else {
			GenTestData<HousePriceResult> gtd = new GenTestData<HousePriceResult>();
			output = gtd.genHouseData4test(HousePriceResult.class);
		}
		String rspStr = JSONUtils.toJSONString(output);
		// rspStr = duomi.com.utils.StringUtils.decodeUnicode(rspStr);
		// 更新登记表状态为收到服务
		regitSrv.updateCspStusAfter(houseReq, rspStr);

		return output;
	}

	/**
	 * 获取房屋租金
	 */
	public HouseListResponse<HouseRentResult> getHouseRent(HouseRentRequest houseReq) throws Exception {

		String apiUrl = this.HOUSE_URL + this.url_houserent;
		HouseListResponse<HouseRentResult> output = null;
		// 更新登记表状态为发送，
		regitSrv.updateCspStusBefore(houseReq);
		String mobile = houseReq.getMobile();
		String appNo = houseReq.getAppNo();

		HouseRentInput houseRent = new HouseRentInput();
		BeanUtils.copyProperties(houseReq, houseRent);

		String token = this.getTokey();
		houseRent.setToken(token);

		List<NameValuePair> list = JSONUtils.toNameValuePairList(houseRent);
		String reqstr = JSONUtils.toJSONString(houseRent);
		logger.info("工单编号：" + appNo + ",手机号:" + mobile + "小区租金接口接口请求报文：" + reqstr);

		String retJson = HttpUtil.post(apiUrl, list);
		logger.info("工单编号：" + appNo + ",手机号:" + mobile + "小区租金接口返回报文：" + retJson + "\n,小区租金请求报文:" + reqstr);

		GsonUtils<HouseRentResult> gson = new GsonUtils<HouseRentResult>();
		output = (HouseListResponse<HouseRentResult>) gson.fromJson(retJson, HouseListResponse.class,
				HouseRentResult.class);

		if (output == null) {
			logger.error("工单编号：" + appNo + ",手机号:" + mobile + "小区租金接口交易失败");
			throw new HttpBizException("小区租金接口交易失败");
		} else if (!HouseRspConstants.CODE_SUCCESS.equals(output.getCode())) {
			logger.error("工单编号：" + appNo + ",手机号:" + mobile + "房价错误代码:" + output.getCode() + "错误描述：" + output.getMsg()
					+ ",请求报文：" + reqstr);
			throw new HttpBizException(output.getCode(), output.getMsg());
		}
		String rspStr = JSONUtils.toJSONString(output);
		// 更新登记表状态为收到服务\\
		// rspStr = duomi.com.utils.StringUtils.decodeUnicode(rspStr);
		regitSrv.updateCspStusAfter(houseReq, retJson);

		return output;
	}

	/**
	 * 获取tokey
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getTokey() throws Exception {
		String tokey = this.getTokenFromWeb();
		return tokey;
	}

	public static void main(String[] args) {
		try {
			String a = new HouseHttpServiceImpl().getTokenFromWeb();
			System.out.println(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
