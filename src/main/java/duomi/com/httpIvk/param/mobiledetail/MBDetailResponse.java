package duomi.com.httpIvk.param.mobiledetail;

public class MBDetailResponse {
	private String code; // 返回码
	private String message;// 提示信息
	private String task_id;// 任务编码。每个任务都有唯一的任务编码，如：TASKYYS1000001234567890

	private MBDetailData data; // 返回任务信息和原始数据

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

	public MBDetailData getData() {
		return data;
	}

	public void setData(MBDetailData data) {
		this.data = data;
	}

}
