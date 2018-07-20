package duomi.com.httpIvk.param.multipleLend;

public class MultipleCreditAppResult {
	private String name;// 姓名
	private String mobile;// 手机号
	private String idCard;// 身份证号

	private String status;// 查询状态
	private String statusDesc;// 查询状态描述

	private ApplyLoanInfo applyLoan;// 信贷申请数据

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

	public ApplyLoanInfo getApplyLoan() {
		return applyLoan;
	}

	public void setApplyLoan(ApplyLoanInfo applyLoan) {
		this.applyLoan = applyLoan;
	}

}
