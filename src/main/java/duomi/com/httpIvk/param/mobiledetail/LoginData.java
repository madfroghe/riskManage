package duomi.com.httpIvk.param.mobiledetail;

import java.util.List;

public class LoginData {
	private String next_stage; // 下一个阶段。比如next_stage是"LOGIN"，那么继续调用登录验证接口，task_stage输入"LOGIN"。
	private String auth_code; // 图片验证码。BASE64编码后的图片，如果下一阶段需要输入图片验证码，则会返回data.auth_code，否则data.auth_code不会出现。
	private List<LoginFields> fields; // 下一个阶段需要输入的参数\\

	public String getNext_stage() {
		return next_stage;
	}

	public void setNext_stage(String next_stage) {
		this.next_stage = next_stage;
	}

	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public List<LoginFields> getFields() {
		return fields;
	}

	public void setFields(List<LoginFields> fields) {
		this.fields = fields;
	}

}
