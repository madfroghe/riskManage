package duomi.com.httpIvk.param.bankCard;

public class BankCardTradeResult {
	private String mobile; // 手机号
	private String model; // 手机型号
	private String status; // 结果状态
	private String statusDesc; // 结果状态描述
	private String accountNo; // 卡号
	private AccountData accountNoInfo;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public AccountData getAccountNoInfo() {
		return accountNoInfo;
	}

	public void setAccountNoInfo(AccountData accountNoInfo) {
		this.accountNoInfo = accountNoInfo;
	}

}
