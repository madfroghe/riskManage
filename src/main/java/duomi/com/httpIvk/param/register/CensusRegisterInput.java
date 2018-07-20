package duomi.com.httpIvk.param.register;

import duomi.com.httpIvk.param.BaseRequest;

public class CensusRegisterInput extends BaseRequest {
	private String name;
	private String idCard;

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
