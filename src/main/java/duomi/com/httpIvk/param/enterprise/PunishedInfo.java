package duomi.com.httpIvk.param.enterprise;

public class PunishedInfo {
	private String caseNo;         		//  案号                                                         
	private String name;         			//  被执行姓名                                                   
	private String sex;         			//  性别                                                         
	private String age;         			//  年龄                                                         
	private String cardNumber;         // 身份证号码 /工商注册号                                       
	private String identityDeparture;  // 身份证原始发地                                               
	private String filingDate;         // 立案时间                                                     
	private String executeTarget;      // 执行标的（元）                                               
	private String province;           // 省份                                                         
	private String focusNumber;       //  关注次数                                                     
	private String caseState;         //  案件状态(1-已结案,2-执行中3-全国法院被执行人查询平台已不披露)
	private String courtName;         //  执行法院                                                     
	private String type;         			//  失信类型(1-自然人,2-法人或其他组织)                          
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getIdentityDeparture() {
		return identityDeparture;
	}
	public void setIdentityDeparture(String identityDeparture) {
		this.identityDeparture = identityDeparture;
	}
	public String getFilingDate() {
		return filingDate;
	}
	public void setFilingDate(String filingDate) {
		this.filingDate = filingDate;
	}
	public String getExecuteTarget() {
		return executeTarget;
	}
	public void setExecuteTarget(String executeTarget) {
		this.executeTarget = executeTarget;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getFocusNumber() {
		return focusNumber;
	}
	public void setFocusNumber(String focusNumber) {
		this.focusNumber = focusNumber;
	}
	public String getCaseState() {
		return caseState;
	}
	public void setCaseState(String caseState) {
		this.caseState = caseState;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
}
