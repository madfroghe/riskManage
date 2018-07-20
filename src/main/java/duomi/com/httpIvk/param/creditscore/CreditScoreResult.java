package duomi.com.httpIvk.param.creditscore;

public class CreditScoreResult {
	private String name;
	private String mobile;
	private String idCard;
	// 信用评分 	分值范围：300~850
	private String score;
	// 查询结果状态 	EXIST、NO_DATA
	private String status;
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
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
