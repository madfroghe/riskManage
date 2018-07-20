package duomi.dbMap.bean;

import java.util.Date;

public class PersonBlacklistDetailPo {
	private Long id;

	private Long blacklistid;

	private String appNo;

	private String mobile;

	private String overdueDays;

	private String overdueDate;

	private String legalState;

	private String type;

	private String overdueAmount;

	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBlacklistid() {
		return blacklistid;
	}

	public void setBlacklistid(Long blacklistid) {
		this.blacklistid = blacklistid;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(String overdueDays) {
		this.overdueDays = overdueDays;
	}

	public String getOverdueDate() {
		return overdueDate;
	}

	public void setOverdueDate(String overdueDate) {
		this.overdueDate = overdueDate;
	}

	public String getLegalState() {
		return legalState;
	}

	public void setLegalState(String legalState) {
		this.legalState = legalState;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(String overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}