package duomi.dbMap.bean;

import java.util.Date;

public class BadrecordCheckPo {
	private Long id;

	private String appNo;

	private String mobile;

	private String name;

	private String idCard;

	private String comparisonResult;

	private String caseTime;

	private String status;

	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getComparisonResult() {
		return comparisonResult;
	}

	public void setComparisonResult(String comparisonResult) {
		this.comparisonResult = comparisonResult;
	}

	public String getCaseTime() {
		return caseTime;
	}

	public void setCaseTime(String caseTime) {
		this.caseTime = caseTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}