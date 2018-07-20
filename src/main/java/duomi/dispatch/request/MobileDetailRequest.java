package duomi.dispatch.request;

public class MobileDetailRequest {
	private String name;
	private String mobile;
	private String idcard;
	private String mbpasswd;// 手机服务密码
	private String shortMsgCode; // 短信验证码
	private String pictureCode; // 图片验证码
	private String task_id;// 任务编号
	private String next_stage;// 下一阶段

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getShortMsgCode() {
		return shortMsgCode;
	}

	public void setShortMsgCode(String shortMsgCode) {
		this.shortMsgCode = shortMsgCode;
	}

	public String getPictureCode() {
		return pictureCode;
	}

	public void setPictureCode(String pictureCode) {
		this.pictureCode = pictureCode;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getMbpasswd() {
		return mbpasswd;
	}

	public void setMbpasswd(String mbpasswd) {
		this.mbpasswd = mbpasswd;
	}

	public String getNext_stage() {
		return next_stage;
	}

	public void setNext_stage(String next_stage) {
		this.next_stage = next_stage;
	}

}
