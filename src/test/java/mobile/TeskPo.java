package mobile;

public class TeskPo {
	private String channel_type; //渠道类型。运营商是 YYS
	private String channel_code; //渠道编码。运营商是 100000
	private String real_name ; //真实姓名。支持中文、英文和"."，不支持其他特殊字符。
	private String identity_code; //身份证号码。18位和15位数字，末尾是数字或X。
	private String user_mobile; //手机号码，11位数字。
	private String passback_params; //透传参数。用于异步回调的透传参数，字符长度小于512
	public String getChannel_type() {
		return channel_type;
	}
	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}
	public String getChannel_code() {
		return channel_code;
	}
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getIdentity_code() {
		return identity_code;
	}
	public void setIdentity_code(String identity_code) {
		this.identity_code = identity_code;
	}
	public String getUser_mobile() {
		return user_mobile;
	}
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}
	public String getPassback_params() {
		return passback_params;
	}
	public void setPassback_params(String passback_params) {
		this.passback_params = passback_params;
	}
	
	
	
}
