package duomi.com.httpIvk.param.mobiledetail;

public class MbLoginResponse {
	private int code; // 返回码。返回码是137或2007时，说明登录验证阶段已经完成。
	private String message; // 提示信息
	private String task_id; // 必填 任务编码
	private LoginData data;// 返回数据。如果需要继续下一个交互阶段，data会返回下一个阶段的参数，否则data为null。

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public LoginData getData() {
		return data;
	}

	public void setData(LoginData data) {
		this.data = data;
	}

}
