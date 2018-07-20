package duomi.com.httpIvk.param.travel;

import duomi.com.httpIvk.param.BaseRequest;

public class FlightInfoInput extends BaseRequest {
	public String name; // 真实姓名
	public String idCard; // 身份证号
	public String month;// 查询月份（目前只支持3、6、12）
	public String passportNo; // 护照

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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

}
