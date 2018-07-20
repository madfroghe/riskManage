package duomi.com.httpIvk.param.phone;

public class MobileNumbersResult {
	private String name;// 姓名
	private String mobile;// 手机号
	private String idCard;// 身份证
	private String status;// 结果状态
	private String statusDesc;// 结果状态描述
	private String number;// 接入个数个数
	private String numberDesc;// 接入个数码描述

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumberDesc() {
		return numberDesc;
	}

	public void setNumberDesc(String numberDesc) {
		this.numberDesc = numberDesc;
	}

}
