package duomi.dbMap.bean;

import java.util.Date;

public class CspInterfaceLogPo {
	private Long id;

	private Long interId;

	private String appNo;

	private String mobileNo;

	private String interNo;

	private String msgType;

	private Date createTime;

	private String message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInterId() {
		return interId;
	}

	public void setInterId(Long interId) {
		this.interId = interId;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getInterNo() {
		return interNo;
	}

	public void setInterNo(String interNo) {
		this.interNo = interNo;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}