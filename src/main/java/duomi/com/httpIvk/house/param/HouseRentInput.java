package duomi.com.httpIvk.house.param;

public class HouseRentInput extends HouseBaseInput {
	private String city; // 城市，例：上海
	private String district;// 区域名，例：长宁
	private String name;// 小区名
	private int houseType;// 房型，如1：一室，2：二室
	private int periodCount;// 最近多少期数据，周期:月

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHouseType() {
		return houseType;
	}

	public void setHouseType(int houseType) {
		this.houseType = houseType;
	}

	public int getPeriodCount() {
		return periodCount;
	}

	public void setPeriodCount(int periodCount) {
		this.periodCount = periodCount;
	}

}
