package duomi.com.httpIvk.param.blacklist;

import duomi.com.httpIvk.param.BaseRequest;

public class BlackListInput extends BaseRequest {
	// private String timestamp; //公共参数：时间戳
	private String name; // 接口参数：真实姓名
	private String idCard; // 接口参数：身份证号

	/*
	 * public String getTimestamp() { return timestamp; }
	 * 
	 * public void setTimestamp(String timestamp) { this.timestamp = timestamp;
	 * }
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}
