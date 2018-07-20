package duomi.dispatch.request;

public class MobileCBNotifyTeskData {
	private String channel_type;
	private String channel_code;
	private String channel_src;
	private String channel_attr;
	private String real_name;
	private String identity_code;
	private String user_mobile;
	private String created_time;
	private String user_name;
	private Object task_data;
	private Object lost_data;

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

	public String getChannel_src() {
		return channel_src;
	}

	public void setChannel_src(String channel_src) {
		this.channel_src = channel_src;
	}

	public String getChannel_attr() {
		return channel_attr;
	}

	public void setChannel_attr(String channel_attr) {
		this.channel_attr = channel_attr;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Object getTask_data() {
		return task_data;
	}

	public void setTask_data(Object task_data) {
		this.task_data = task_data;
	}

	public Object getLost_data() {
		return lost_data;
	}

	public void setLost_data(Object lost_data) {
		this.lost_data = lost_data;
	}

}
