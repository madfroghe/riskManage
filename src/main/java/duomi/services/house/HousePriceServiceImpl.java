package duomi.services.house;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.house.param.CityInfoInput;
import duomi.com.httpIvk.house.param.CityRegionsResult;
import duomi.com.httpIvk.house.param.HouseBaseResponse;
import duomi.com.httpIvk.house.param.HouseListResponse;
import duomi.com.httpIvk.house.param.HousePriceResult;
import duomi.com.httpIvk.house.param.HouseRentResult;
import duomi.com.httpIvk.services.HouseHttpService;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.MD5Util;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dispatch.factory.HsResponseSimpleHelper;
import duomi.dispatch.factory.HsRspListSimpleHelper;
import duomi.dispatch.request.house.HousePriceRequest;
import duomi.dispatch.request.house.HouseRentRequest;
import duomi.dispatch.response.ComListResponse;
import duomi.dispatch.response.ComResponse;
import duomi.services.OutsideServiceRegistService;

@Service
public class HousePriceServiceImpl implements HousePriceService {
	@Autowired
	private HouseHttpService househttpSrv;

	@Autowired
	private OutsideServiceRegistService regitSrv;

	/**
	 * 获取房价网城市列表
	 */
	public ComListResponse<CityInfoInput> getCityList() throws Exception {

		HouseListResponse<CityInfoInput> citysOutput = househttpSrv.getCityList();
		ComListResponse<CityInfoInput> citysRsp = new HsRspListSimpleHelper<CityInfoInput>().ConvertRsp(null,
				citysOutput);
		return citysRsp;
	}

	/**
	 * 获取房价网城市区域列表
	 */
	public ComListResponse<CityRegionsResult> getCityRegions(String city) throws Exception {
		HouseListResponse<CityRegionsResult> regionsOutput = househttpSrv.getCityArea(city);

		ComListResponse<CityRegionsResult> regionsRsp = new HsRspListSimpleHelper<CityRegionsResult>().ConvertRsp(null,
				regionsOutput);
		return regionsRsp;
	}

	/**
	 * 获取房价网估值数据
	 */
	public ComResponse<HousePriceResult> getHousePrice(HousePriceRequest houseReq) throws Exception {

		ComResponse<HousePriceResult> output = null;
		// MD5 String
		String str = houseReq.getCity() + houseReq.getDistrict() + houseReq.getName() + houseReq.getSize();
		String md5String = MD5Util.getMD5(str);
		houseReq.setInterSerno(md5String);

		Map<String, Object> retMap = regitSrv.isRequested(houseReq);

		boolean isQuested = (Boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
		if (isQuested) {
			CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
			output = this.getFourInfoFromLocal(houseReq, cspStatPo);
		} else {
			output = this.getFourInfoFromOutside(houseReq);
		}

		return output;

	}

	private ComResponse<HousePriceResult> getFourInfoFromOutside(HousePriceRequest houseReq) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(houseReq);

		HouseBaseResponse<HousePriceResult> houseResult = househttpSrv.getHousePrice(houseReq);

		ComResponse<HousePriceResult> output = (ComResponse<HousePriceResult>) new HsResponseSimpleHelper<HousePriceResult>()
				.createSuccessRsp(houseReq, houseResult.getResult());
		return output;

	}

	private ComResponse<HousePriceResult> getFourInfoFromLocal(HousePriceRequest houseReq,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<HousePriceResult> gson = new GsonUtils<HousePriceResult>();
		HouseBaseResponse<HousePriceResult> output = (HouseBaseResponse<HousePriceResult>) gson.fromJson(retMsg,
				HouseBaseResponse.class, HousePriceResult.class);

		ComResponse<HousePriceResult> rsp = new HsResponseSimpleHelper<HousePriceResult>().ConvertRsp(houseReq, output);

		return rsp;
	}

	/**
	 * 小区租金
	 */
	public ComListResponse<HouseRentResult> getHouseRent(HouseRentRequest houseReq) throws Exception {
		ComListResponse<HouseRentResult> output = null;
		// MD5 String
		String str = houseReq.getCity() + houseReq.getDistrict() + houseReq.getName() + houseReq.getHouseType()
				+ houseReq.getSize();
		String md5String = MD5Util.getMD5(str);
		houseReq.setInterSerno(md5String);

		Map<String, Object> retMap = regitSrv.isRequested(houseReq);
		boolean isQuested = (Boolean) retMap.get(PubConstants.CSP_STAT_ISEXISTS);
		if (isQuested) {
			CspInterfaceStatPoWithBLOBs cspStatPo = (CspInterfaceStatPoWithBLOBs) retMap.get(PubConstants.CSP_STAT_PO);
			output = this.getFourInfoFromLocal4Rent(houseReq, cspStatPo);
		} else {
			output = this.getFourInfoFromOutside4Rent(houseReq);
		}

		return output;
	}

	private ComListResponse<HouseRentResult> getFourInfoFromOutside4Rent(HouseRentRequest houseReq) throws Exception {
		// 接受到调度请求后，登记外部服务查询状态表
		regitSrv.insertCspStus(houseReq);

		HouseListResponse<HouseRentResult> houseResult = househttpSrv.getHouseRent(houseReq);

		ComListResponse<HouseRentResult> output = (ComListResponse<HouseRentResult>) new HsRspListSimpleHelper<HouseRentResult>()
				.createSuccessRsp(houseReq, houseResult.getResult());
		return output;
	}

	private ComListResponse<HouseRentResult> getFourInfoFromLocal4Rent(HouseRentRequest houseReq,
			CspInterfaceStatPoWithBLOBs cspStatPo) {

		String retMsg = cspStatPo.getRetMessage();

		GsonUtils<HouseRentResult> gson = new GsonUtils<HouseRentResult>();
		HouseListResponse<HouseRentResult> output = (HouseListResponse<HouseRentResult>) gson.fromJson(retMsg,
				HouseListResponse.class, HouseRentResult.class);

		ComListResponse<HouseRentResult> rsp = new HsRspListSimpleHelper<HouseRentResult>().ConvertRsp(houseReq,
				output);

		return rsp;
	}

}
