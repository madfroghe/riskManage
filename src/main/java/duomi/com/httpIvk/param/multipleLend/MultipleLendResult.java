package duomi.com.httpIvk.param.multipleLend;

import java.util.List;
import java.util.Set;

public class MultipleLendResult {
	//电话
	public String mobile;
	//省份
	public String province;
	//城市
	public String city;
	//查询周期
	public String cycle;
	//信贷平台注册详情
	public List<CreditPlatformRegistrationDetails> creditPlatformRegistrationDetails;
	//贷款申请详情
	public List<LoanApplicationDetails> 	loanApplicationDetails;
	//贷款放款详情
	public List<loanLenderDetails> loanLenderDetails;
	//贷款驳回详情
	public List<LoanRejectDetails> loanRejectDetails;
	//逾期平台详情查询
	public List<LoanOverdueDetails> loanOverdueDetails;
	//欠款查询
	public List<ArrearsInquiry> arrearsInquiry;
	//返回状态  	EXIST,NO_DATA
	public String status;
	//查询有数据，查询无数据
	public String statusDesc;
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public List<CreditPlatformRegistrationDetails> getCreditPlatformRegistrationDetails() {
		return creditPlatformRegistrationDetails;
	}
	public void setCreditPlatformRegistrationDetails(
			List<CreditPlatformRegistrationDetails> creditPlatformRegistrationDetails) {
		this.creditPlatformRegistrationDetails = creditPlatformRegistrationDetails;
	}
	public List<LoanApplicationDetails> getLoanApplicationDetails() {
		return loanApplicationDetails;
	}
	public void setLoanApplicationDetails(List<LoanApplicationDetails> loanApplicationDetails) {
		this.loanApplicationDetails = loanApplicationDetails;
	}
	public List<loanLenderDetails> getLoanLenderDetails() {
		return loanLenderDetails;
	}
	public void setLoanLenderDetails(List<loanLenderDetails> loanLenderDetails) {
		this.loanLenderDetails = loanLenderDetails;
	}
	public List<LoanRejectDetails> getLoanRejectDetails() {
		return loanRejectDetails;
	}
	public void setLoanRejectDetails(List<LoanRejectDetails> loanRejectDetails) {
		this.loanRejectDetails = loanRejectDetails;
	}
	public List<LoanOverdueDetails> getLoanOverdueDetails() {
		return loanOverdueDetails;
	}
	public void setLoanOverdueDetails(List<LoanOverdueDetails> loanOverdueDetails) {
		this.loanOverdueDetails = loanOverdueDetails;
	}
	public List<ArrearsInquiry> getArrearsInquiry() {
		return arrearsInquiry;
	}
	public void setArrearsInquiry(List<ArrearsInquiry> arrearsInquiry) {
		this.arrearsInquiry = arrearsInquiry;
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
	
	
	
}
