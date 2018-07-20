package duomi.com.httpIvk.param.mobiledetail;

public class MBDetailBaseInfo {
	private String user_name; // 姓名。脱敏部分用＊替代
	private String user_sex;// 性别。男、女、未知
	private String user_number;// 手机号码。11位手机号码
	private String cert_num;// 身份证号码。18位数字或者+X，脱敏部分用＊替代
	private String cert_addr;// 联系地址。脱敏部分用＊替代
	private String user_contact_no;// 联系电话。11位手机号码或者座机号码
	private String user_email;// 邮箱地址。如：123@qq.com
	private String post_code;

	public String getPost_code() {
		return post_code;
	}

	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}

	public String getUser_number() {
		return user_number;
	}

	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}

	public String getCert_num() {
		return cert_num;
	}

	public void setCert_num(String cert_num) {
		this.cert_num = cert_num;
	}

	public String getCert_addr() {
		return cert_addr;
	}

	public void setCert_addr(String cert_addr) {
		this.cert_addr = cert_addr;
	}

	public String getUser_contact_no() {
		return user_contact_no;
	}

	public void setUser_contact_no(String user_contact_no) {
		this.user_contact_no = user_contact_no;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

}
