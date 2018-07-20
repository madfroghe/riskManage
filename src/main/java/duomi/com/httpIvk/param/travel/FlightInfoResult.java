package duomi.com.httpIvk.param.travel;

public class FlightInfoResult {
	private String name;// 姓名
	private String idCard;// 身份证号
	private String status;// 结果状态
	private FlightInfoDetail flightInfoDetail;// 详细信息

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FlightInfoDetail getFlightInfoDetail() {
		return flightInfoDetail;
	}

	public void setFlightInfoDetail(FlightInfoDetail flightInfoDetail) {
		this.flightInfoDetail = flightInfoDetail;
	}

}
