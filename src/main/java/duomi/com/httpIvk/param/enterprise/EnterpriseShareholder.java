package duomi.com.httpIvk.param.enterprise;

/**
 * 企业股东信息
 * @author Administrator
 *
 */
public class EnterpriseShareholder {
	private String name;           //查询人姓名      
	private String entName;        //企业(机构)名称  
	private String entNo;         // 企业注册号      
	private String entType;       // 企业(机构)类型  
	private String regcapital;    // 注册资本(万元)  
	private String currency;      // 注册资本币种    
	private String contribution;  // 认缴出资额      
	private String contributionCurrency; //    认缴出资额(币种)
	private String card;          // 身份证号        
	private String status;        // 企业状态        
	private String fundedRatio;   // 出资比例  
	
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
	public String getContribution() {
		return contribution;
	}
	public void setContribution(String contribution) {
		this.contribution = contribution;
	}
	public String getContributionCurrency() {
		return contributionCurrency;
	}
	public void setContributionCurrency(String contributionCurrency) {
		this.contributionCurrency = contributionCurrency;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFundedRatio() {
		return fundedRatio;
	}
	public void setFundedRatio(String fundedRatio) {
		this.fundedRatio = fundedRatio;
	}
	
	

}
