package duomi.com.httpIvk.param.mobiledetail;

public class MbTeskResponse {

	private int code;// 返回码
	private String message;// 提示信息
	private String task_id;// 任务编码。每个任务都有唯一的任务编码，如：TASKYYS1000001234567890
	private String next_stage;// 下一阶段

	private MbTeskData data;// 返回创建的任务信息

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

	public MbTeskData getData() {
		return data;
	}

	public void setData(MbTeskData data) {
		this.data = data;
	}

	public String getNext_stage() {
		return next_stage;
	}

	public void setNext_stage(String next_stage) {
		this.next_stage = next_stage;
	}

}
