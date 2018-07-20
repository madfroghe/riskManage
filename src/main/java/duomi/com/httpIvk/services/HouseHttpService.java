package duomi.com.httpIvk.services;

import duomi.com.httpIvk.house.param.CityInfoInput;
import duomi.com.httpIvk.house.param.CityRegionsResult;
import duomi.com.httpIvk.house.param.HouseBaseResponse;
import duomi.com.httpIvk.house.param.HouseListResponse;
import duomi.com.httpIvk.house.param.HousePriceResult;
import duomi.com.httpIvk.house.param.HouseRentResult;
import duomi.dispatch.request.house.HousePriceRequest;
import duomi.dispatch.request.house.HouseRentRequest;

public interface HouseHttpService {
	public String getTokenFromWeb() throws Exception;

	public HouseListResponse<CityInfoInput> getCityList() throws Exception;

	public HouseListResponse<CityRegionsResult> getCityArea(String city) throws Exception;

	public HouseBaseResponse<HousePriceResult> getHousePrice(HousePriceRequest houseReq) throws Exception;

	public HouseListResponse<HouseRentResult> getHouseRent(HouseRentRequest houseReq) throws Exception;

}
