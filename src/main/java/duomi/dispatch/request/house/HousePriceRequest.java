package duomi.dispatch.request.house;

import duomi.dispatch.request.ComRequest;

public class HousePriceRequest extends ComRequest {

	private String city; // 城市，例：上海
	private String district; // 区域，例：长宁
	private String name; // 小区名
	private String buildingNumber; // 楼号
	private String unitNumber; // 室号
	private String size; // 面积，单位：平米
	private String floor; // 所在楼层
	private String maxFloor; // 总楼层
	private String room; // 室数量
	private String hall; // 厅数量
	private String toilet; // 卫数量
	private String calDate; // 估价时间
	private String propertyType; // 物业类型
	private String direction; // 朝向
	private String decoration; // 装修情况
	private String decorationCost; // 装修价格
	private String decorationAge; // 装修年限
	private String view; // 景观情况
	private String noise; // 噪音情况
	private String gardenSize; // 花园面积
	private String basementSize; // 地下室面积
	private String negativeFactor; // 厌恶因素
	private String age; // 房龄
	private String carportPrice; // 车位价
	private String facilities; // 小区配套
	private String insidePosition; // 小区配套

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

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getMaxFloor() {
		return maxFloor;
	}

	public void setMaxFloor(String maxFloor) {
		this.maxFloor = maxFloor;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getHall() {
		return hall;
	}

	public void setHall(String hall) {
		this.hall = hall;
	}

	public String getToilet() {
		return toilet;
	}

	public void setToilet(String toilet) {
		this.toilet = toilet;
	}

	public String getCalDate() {
		return calDate;
	}

	public void setCalDate(String calDate) {
		this.calDate = calDate;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDecoration() {
		return decoration;
	}

	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}

	public String getDecorationCost() {
		return decorationCost;
	}

	public void setDecorationCost(String decorationCost) {
		this.decorationCost = decorationCost;
	}

	public String getDecorationAge() {
		return decorationAge;
	}

	public void setDecorationAge(String decorationAge) {
		this.decorationAge = decorationAge;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getNoise() {
		return noise;
	}

	public void setNoise(String noise) {
		this.noise = noise;
	}

	public String getGardenSize() {
		return gardenSize;
	}

	public void setGardenSize(String gardenSize) {
		this.gardenSize = gardenSize;
	}

	public String getBasementSize() {
		return basementSize;
	}

	public void setBasementSize(String basementSize) {
		this.basementSize = basementSize;
	}

	public String getNegativeFactor() {
		return negativeFactor;
	}

	public void setNegativeFactor(String negativeFactor) {
		this.negativeFactor = negativeFactor;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCarportPrice() {
		return carportPrice;
	}

	public void setCarportPrice(String carportPrice) {
		this.carportPrice = carportPrice;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public String getInsidePosition() {
		return insidePosition;
	}

	public void setInsidePosition(String insidePosition) {
		this.insidePosition = insidePosition;
	}

}
