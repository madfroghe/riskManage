package duomi.dbMap.bean;

import java.util.Date;

public class MobileAveragefeePo {
	private Long id;

	private String appNo;

	private String mobile;

	private String consume;

	private String consumeDesc;

	private String status;

	private String statusDesc;

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

	public String getConsume() {
		return consume;
	}

	public void setConsume(String consume) {
		this.consume = consume;
	}

	public String getConsumeDesc() {
		return consumeDesc;
	}

	public void setConsumeDesc(String consumeDesc) {
		this.consumeDesc = consumeDesc;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}