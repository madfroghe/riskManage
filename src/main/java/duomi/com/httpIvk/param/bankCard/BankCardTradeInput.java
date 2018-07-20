package duomi.com.httpIvk.param.bankCard;

import duomi.com.httpIvk.param.BaseRequest;

public class BankCardTradeInput extends BaseRequest {

	public String name;
	public String mobile;
	public String idCard;
	public String accountNo;

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

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

}
