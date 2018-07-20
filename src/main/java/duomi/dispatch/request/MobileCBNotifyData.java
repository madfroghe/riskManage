package duomi.dispatch.request;

public class MobileCBNotifyData {
	private String code;
	private String message;
	private String task_id;
	private MobileCBNotifyTeskData data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
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

	public MobileCBNotifyTeskData getData() {
		return data;
	}

	public void setData(MobileCBNotifyTeskData data) {
		this.data = data;
	}

}
