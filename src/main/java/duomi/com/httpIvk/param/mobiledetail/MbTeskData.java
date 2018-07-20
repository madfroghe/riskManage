package duomi.com.httpIvk.param.mobiledetail;

public class MbTeskData {
	private String channel_type;// 渠道类型。运营商是 YYS
	private String channel_code;// 渠道编码。运营商是 100000
	private String real_name;// 真实姓名。如：张三
	private String identity_code;// 身份证号码。
	private String user_mobile;// 手机号码
	private String created_time;// 任务创建时间

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

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

}
