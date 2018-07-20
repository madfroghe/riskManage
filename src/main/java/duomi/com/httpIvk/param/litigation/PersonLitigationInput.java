package duomi.com.httpIvk.param.litigation;

import duomi.com.httpIvk.param.BaseRequest;

public class PersonLitigationInput extends BaseRequest {

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
