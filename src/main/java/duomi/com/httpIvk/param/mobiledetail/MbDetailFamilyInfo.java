package duomi.com.httpIvk.param.mobiledetail;

public class MbDetailFamilyInfo {
	private String member_number;// 成员号码。11位手机号码
	private String member_cornet;// 成员短号。3位号码;
	private String member_type;// 成员类型

	public String getMember_number() {
		return member_number;
	}

	public void setMember_number(String member_number) {
		this.member_number = member_number;
	}

	public String getMember_cornet() {
		return member_cornet;
	}

	public void setMember_cornet(String member_cornet) {
		this.member_cornet = member_cornet;
	}

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}

}
