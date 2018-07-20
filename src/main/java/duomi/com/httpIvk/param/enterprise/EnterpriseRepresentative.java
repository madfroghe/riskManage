package duomi.com.httpIvk.param.enterprise;

/**
 * 企业法定代表人信息
 * @author Administrator
 *
 */
public class EnterpriseRepresentative {
	private String name;            // 查询人姓名      
	private String entName ;        // 企业(机构)名称 
	private String entNo;           // 注册号         
	private String entType ;        // 企业(机构)类型 
	private String regcapital;      // 注册资本(万元) 
	private String currency;        // 注册资本币种   
	private String status ;         // 企业状态       
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public String getEntNo() {
		return entNo;
	}
	public void setEntNo(String entNo) {
		this.entNo = entNo;
	}
	public String getEntType() {
		return entType;
	}
	public void setEntType(String entType) {
		this.entType = entType;
	}
	public String getRegcapital() {
		return regcapital;
	}
	public void setRegcapital(String regcapital) {
		this.regcapital = regcapital;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
