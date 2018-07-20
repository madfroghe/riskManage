package duomi.com.httpIvk.param.mobiledetail;

public class LoginFields {
	private String name; // 参数名称。比如下一个阶段需要输入短信验证码，则data.fields.name是"sms_code"，data.fields.label是"手机验证码"，那么在等待用户输入验证码后，继续调用登录验证接口，输入参数"sms_code":"验证码"。

	private String label;// 参数标签。用于显示给用户，标签内容比如"图片验证码"或"手机验证码"

	private String type; // 参数类型。文本类型是"text"，密码类型是"password"。用于适配对应的交互形式。

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
