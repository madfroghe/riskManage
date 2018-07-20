package duomi.services.house;

import duomi.com.httpIvk.house.param.CityInfoInput;
import duomi.com.httpIvk.house.param.CityRegionsResult;
import duomi.com.httpIvk.house.param.HousePriceResult;
import duomi.com.httpIvk.house.param.HouseRentResult;
import duomi.dispatch.request.house.HousePriceRequest;
import duomi.dispatch.request.house.HouseRentRequest;
import duomi.dispatch.response.ComListResponse;
import duomi.dispatch.response.ComResponse;

public interface HousePriceService {
	public ComListResponse<CityInfoInput> getCityList() throws Exception;

	public ComListResponse<CityRegionsResult> getCityRegions(String city) throws Exception;

	public ComResponse<HousePriceResult> getHousePrice(HousePriceRequest houseReq) throws Exception;

	public ComListResponse<HouseRentResult> getHouseRent(HouseRentRequest houseReq) throws Exception;

}
