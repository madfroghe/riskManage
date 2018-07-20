package duomi.dispatch.request;

public class BlackListRequest extends ComRequest {
	// private String timestamp;
	private String name;
	private String idCard;

	/*
	 * public String getTimestamp() { return timestamp; } public void
	 * setTimestamp(String timestamp) { this.timestamp = timestamp; }
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
