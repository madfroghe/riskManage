package duomi.com.httpIvk.param.multipleLend;

import duomi.com.httpIvk.param.BaseRequest;

public class MultipleCreditAppInput extends BaseRequest {

	private String name; // 真实姓名
	private String mobile; // 手机号
	private String idCard; // 身份证号

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

}
