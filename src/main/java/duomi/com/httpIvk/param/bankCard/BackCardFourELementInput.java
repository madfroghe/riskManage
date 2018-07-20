package duomi.com.httpIvk.param.bankCard;

import duomi.com.httpIvk.param.BaseRequest;

public class BackCardFourELementInput extends BaseRequest {
	
	public String mobile;
	public String name;
	public String idCard;
	public String accountNo;
	
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
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
}
