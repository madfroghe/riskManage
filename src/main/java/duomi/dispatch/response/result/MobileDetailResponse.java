package duomi.dispatch.response.result;

public class MobileDetailResponse {
	private String code;
	private String msg;
	private String resetCode; // 重置验证码返回结果
	private String resetMsg; // 重置验证码返回消息
	private String task_id; // 任务编号
	private String next_stage; // 下一阶段任务编号

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getNext_stage() {
		return next_stage;
	}

	public void setNext_stage(String next_stage) {
		this.next_stage = next_stage;
	}

	public String getResetCode() {
		return resetCode;
	}

	public void setResetCode(String resetCode) {
		this.resetCode = resetCode;
	}

	public String getResetMsg() {
		return resetMsg;
	}

	public void setResetMsg(String resetMsg) {
		this.resetMsg = resetMsg;
	}

}
