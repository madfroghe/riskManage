package duomi.dispatch.request;

public class MobileCallBackRequest {
	private String notify_event;
	private String notify_type;
	private String notify_time;
	private String sign;
	private String passback_params;
	private String notify_data;

	public String getNotify_event() {
		return notify_event;
	}

	public void setNotify_event(String notify_event) {
		this.notify_event = notify_event;
	}

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPassback_params() {
		return passback_params;
	}

	public void setPassback_params(String passback_params) {
		this.passback_params = passback_params;
	}

	public String getNotify_data() {
		return notify_data;
	}

	public void setNotify_data(String notify_data) {
		this.notify_data = notify_data;
	}

}
