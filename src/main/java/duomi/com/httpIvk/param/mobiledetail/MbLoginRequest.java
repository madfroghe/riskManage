package duomi.com.httpIvk.param.mobiledetail;

public class MbLoginRequest {
	private String task_id;// 必填 任务编码
	private String task_stage;// 必填 请求阶段，首次调用登录验证的task_stage是"INIT"。
	private String user_name;// 手机号码
	private String user_pass;// 服务密码

	// 请求类型。提交是"submit"，轮询是"query"。首先用"submit"提交手机号码和服务密码，然后用"query"查询，直到返回码不是100。
	private String request_type;// 必填 请求类型

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

}
