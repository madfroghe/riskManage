package duomi.com.httpIvk.param.mobiledetail;

public class MbSendVerifRequest {
	private String task_id;// 必填 任务编码;
	private String sms_code;// 动态 手机验证码
	private String auth_code;// 动态 图片验证码;
	private String task_stage;// 必填 请求阶段
	private String request_type;// 必填请求类型。提交是"submit"，轮询是"query"。比如用"submit"提交手机验证码，然后用"query"查询，直到返回码不是100。

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getSms_code() {
		return sms_code;
	}

	public void setSms_code(String sms_code) {
		this.sms_code = sms_code;
	}

	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public String getTask_stage() {
		return task_stage;
	}

	public void setTask_stage(String task_stage) {
		this.task_stage = task_stage;
	}

	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

}
