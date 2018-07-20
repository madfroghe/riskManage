package duomi.com.httpIvk.param.creditGuard;

public class CreditGuardInput {
	private String account_mobile;// 手机号
	private String account_name;// 姓名
	private String id_number;// 身份证号码

	public String getAccount_mobile() {
		return account_mobile;
	}

	public void setAccount_mobile(String account_mobile) {
		this.account_mobile = account_mobile;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

}
