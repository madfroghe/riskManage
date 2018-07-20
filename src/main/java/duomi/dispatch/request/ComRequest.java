package duomi.dispatch.request;

public class ComRequest {
	private String appNo;

	private String mobile;
	private String name;
	private String idCard;

	private String bizFullName; //企业全称

	private String interNo; // 接口编号
	private String interType; // 接口类型
	private String interName; // 接口名称
	private String interSerno;// 用于区分一个接口调用多次的情况


	public Long interId;// 外部数据接口查询编号 登记后设置

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getInterNo() {
		return interNo;
	}

	public void setInterNo(String interNo) {
		this.interNo = interNo;
	}

	public String getInterType() {
		return interType;
	}

	public void setInterType(String interType) {
		this.interType = interType;
	}

	public String getInterName() {
		return interName;
	}

	public void setInterName(String interName) {
		this.interName = interName;
	}

	public Long getInterId() {
		return interId;
	}

	public void setInterId(Long interId) {
		this.interId = interId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

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

	public String getInterSerno() {
		return interSerno;
	}

	public void setInterSerno(String interSerno) {
		this.interSerno = interSerno;
	}

	public String getBizFullName() {
		return bizFullName;
	}

	public void setBizFullName(String bizFullName) {
		this.bizFullName = bizFullName;
	}
}
