package duomi.dispatch.request;

public class EducationCheckquest extends ComRequest {
	//真实姓名
	private String name;
	//身份证号
	private String idCard;
	
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
