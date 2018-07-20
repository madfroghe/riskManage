package duomi.control.house;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.constants.InterFaceConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.house.param.CityInfoInput;
import duomi.com.httpIvk.house.param.CityRegionsResult;
import duomi.com.httpIvk.house.param.HousePriceResult;
import duomi.com.httpIvk.house.param.HouseRentResult;
import duomi.com.utils.DateUtils;
import duomi.dispatch.factory.HsResponseSimpleHelper;
import duomi.dispatch.factory.HsRspListSimpleHelper;
import duomi.dispatch.request.ComRequest;
import duomi.dispatch.request.house.HouseCityRequest;
import duomi.dispatch.request.house.HousePriceRequest;
import duomi.dispatch.request.house.HouseRentRequest;
import duomi.dispatch.response.ComListResponse;
import duomi.dispatch.response.ComResponse;
import duomi.services.house.HousePriceService;

@Controller
public class HousePriceController {
	@Autowired
	private HousePriceService houseSrv;

	@RequestMapping(value = "/getCity", method = RequestMethod.POST)
	@ResponseBody
	public ComListResponse<CityInfoInput> getCityList(ComRequest request) {
		ComListResponse<CityInfoInput> rsp = null;
		request.setInterNo(InterFaceConstants.FJ_CITY_NO);
		request.setInterName(InterFaceConstants.FJ_CITY_NAME);
		request.setInterType(InterFaceConstants.FJ_CITY_TYPE);
		try {
			rsp = houseSrv.getCityList();
		} catch (HttpBizException e) {
			e.printStackTrace();
			rsp = new HsRspListSimpleHelper<CityInfoInput>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			rsp = new HsRspListSimpleHelper<CityInfoInput>().createComErrorRsp(request, null);
		}
		return rsp;
	}

	@RequestMapping(value = "/getCityRegions", method = RequestMethod.POST)
	@ResponseBody
	public ComListResponse<CityRegionsResult> getCityRegions(HouseCityRequest request) {
		ComListResponse<CityRegionsResult> rsp = null;
		request.setInterNo(InterFaceConstants.FJ_CITYREGIONS_NO);
		request.setInterName(InterFaceConstants.FJ_CITYREGIONS_NAME);
		request.setInterType(InterFaceConstants.FJ_CITYREGIONS_TYPE);
		String city = request.getCity();
		try {
			rsp = houseSrv.getCityRegions(city);

		} catch (HttpBizException e) {
			e.printStackTrace();
			rsp = new HsRspListSimpleHelper<CityRegionsResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			rsp = new HsRspListSimpleHelper<CityRegionsResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}

	/**
	 * 房屋估价
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getHousePrice", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<HousePriceResult> getHousePrice(HousePriceRequest request) {
		ComResponse<HousePriceResult> rsp = null;
		request.setInterNo(InterFaceConstants.FJ_HOUSEPRICE_NO);
		request.setInterName(InterFaceConstants.FJ_HOUSEPRICE_NAME);
		request.setInterType(InterFaceConstants.FJ_HOUSEPRICE_TYPE);
		try {
			rsp = houseSrv.getHousePrice(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			rsp = new HsResponseSimpleHelper<HousePriceResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			rsp = new HsResponseSimpleHelper<HousePriceResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}

	/**
	 * 小区租金
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getHouseRent", method = RequestMethod.POST)
	@ResponseBody
	public ComListResponse<HouseRentResult> getHouseRent(HouseRentRequest request) {
		ComListResponse<HouseRentResult> rsp = null;
		request.setInterNo(InterFaceConstants.FJ_HOUSERENT_NO);
		request.setInterName(InterFaceConstants.FJ_HOUSERENT_NAME);
		request.setInterType(InterFaceConstants.FJ_HOUSERENT_TYPE);
		Integer houseType = request.getHouseType();
		BigDecimal size = request.getSize();

		try {
			if ((houseType == null || houseType == 0) && (size == null || size.doubleValue() == 0)) {
				throw new HttpBizException("房型与面积必传其一");
			} else if ((houseType == null || houseType == 0) && (size != null && size.doubleValue() != 0)) {
				double size_bd = size.doubleValue();
				request.setHouseType(this.convertArea(size_bd));
			}
			request.setPeriodCount(12 * 5);// 最近5年数据
			rsp = houseSrv.getHouseRent(request);
		} catch (HttpBizException e) {
			e.printStackTrace();
			rsp = new HsRspListSimpleHelper<HouseRentResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			rsp = new HsRspListSimpleHelper<HouseRentResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
	
	/**
	 * 最近一期小区租金
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getCurrentHouseRent", method = RequestMethod.POST)
	@ResponseBody
	public ComResponse<HouseRentResult> getCurrentHouseRent(HouseRentRequest request) {
		ComResponse<HouseRentResult> rsp = new ComResponse<HouseRentResult>();
		request.setInterNo(InterFaceConstants.FJ_HOUSERENT_NO);
		request.setInterName(InterFaceConstants.FJ_HOUSERENT_NAME);
		request.setInterType(InterFaceConstants.FJ_HOUSERENT_TYPE);
		Integer houseType = request.getHouseType();
		BigDecimal size = request.getSize();

		try {
			if ((houseType == null || houseType == 0) && (size == null || size.doubleValue() == 0)) {
				throw new HttpBizException("房型与面积必传其一");
			} else if ((houseType == null || houseType == 0) && (size != null && size.doubleValue() != 0)) {
				double size_bd = size.doubleValue();
				request.setHouseType(this.convertArea(size_bd));
			}
			request.setPeriodCount(12 * 5);// 最近5年数据
			ComListResponse<HouseRentResult> rsplist = houseSrv.getHouseRent(request);
			HouseRentResult current_result = new HouseRentResult(); // 最近一个月房租
			
			List<HouseRentResult> results = rsplist.getResData();
			if (results != null) {
				// 返回决策
				int count = 0;
				for (HouseRentResult hrResults : results) {
					if (count == 0) {
						current_result = hrResults;
					}
					count = count + 1;
					current_result = hrResults;
					String datetmp1 = hrResults.getDate(); // 循环中当前月
					String datetmp2 = current_result.getDate();
					if (this.compareDate(datetmp1, datetmp2)) {
						current_result = hrResults;
					}
				}
			}
			rsp.setResData(current_result);
			rsp=new HsResponseSimpleHelper<HouseRentResult>().iniSuccessRsp(request, rsp);
		} catch (HttpBizException e) {
			e.printStackTrace();
			rsp = new HsResponseSimpleHelper<HouseRentResult>().createComErrorRsp(request, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			rsp = new HsResponseSimpleHelper<HouseRentResult>().createComErrorRsp(request, null);
		}
		return rsp;
	}
	
	/**
	 * 比较日期 大于等于 返回true 小于返回 false
	 * 
	 * @param data1
	 *            yyyy-MM
	 * @param data2
	 *            yyyy-MM
	 * @return
	 */
	private boolean compareDate(String date1, String date2) {
		if (StringUtils.isBlank(date1) || StringUtils.isBlank(date2)) {
			return false;
		} else if (date1.length() == 7 && date2.length() == 7) {
			String appendstr = "-01";
			date1 = date1 + appendstr;
			date2 = date1 + appendstr;
			Date date_1 = DateUtils.getDateFormat(date1, "yyyy-MM-dd");
			Date date_2 = DateUtils.getDateFormat(date1, "yyyy-MM-dd");

			int ret = DateUtils.compareDate(date_1, date_2, Calendar.DATE);
			if (ret >= 0) {
				return true;
			} else {
				return false;
			}
		} else if (date1.length() == 6 && date2.length() == 6) {
			String appendstr = "01";
			date1 = date1 + appendstr;
			date2 = date1 + appendstr;
			Date date_1 = DateUtils.getDateFormat(date1, "yyyyMMdd");
			Date date_2 = DateUtils.getDateFormat(date1, "yyyyMMdd");

			int ret = DateUtils.compareDate(date_1, date_2, Calendar.DATE);
			if (ret >= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * 根据面积转换房屋类型 （150以上--4室， 100至150--三室,70至100 --两室 ,70以下一室)
	 * 
	 * @param houseArea
	 * @return
	 */
	private Integer convertArea(double size) {
		Integer houseType = null;
		if (size >= 150) {
			houseType = 4;
		} else if (size >= 100) {
			houseType = 3;
		} else if (size >= 70) {
			houseType = 2;
		} else if (size < 70) {
			houseType = 1;
		}
		return houseType;
	}
}
