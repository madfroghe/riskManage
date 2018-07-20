package duomi.com.httpIvk.param.enterprise;

import duomi.com.httpIvk.param.BaseRequest;

public class PersonEnterpriseInput extends BaseRequest {
	private String idCard;
	private String name;

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
