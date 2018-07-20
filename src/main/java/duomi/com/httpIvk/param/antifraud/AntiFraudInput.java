package duomi.com.httpIvk.param.antifraud;

import duomi.com.httpIvk.param.BaseRequest;

public class AntiFraudInput extends BaseRequest {
	public String name;
	public String idCard;
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
	
}
