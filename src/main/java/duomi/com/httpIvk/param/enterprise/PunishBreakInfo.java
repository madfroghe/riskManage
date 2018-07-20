package duomi.com.httpIvk.param.enterprise;

/**
 * 失信被执行人的详细信息
 * @author Administrator
 *
 */
public class PunishBreakInfo {
	private String caseNo;         				//  案号                                        
	private String name;         					//  被执行人姓名                                
	private String type;         					//  失信类型(1-自然人,2-法人或其他组织)         
	private String sex;         					//  性别                                        
	private String age;         					//  年龄                                        
	private String cardNumber;        	 //   身份证号码 /组织机构代码证号                
	private String entNo;         				//  企业注册号*                                 
	private String identityDeparture;    //   身份证原始发地                              
	private String head;         					//  法定代表人/负责人姓名                       
	private String filingDate;         		//  立案时间                                    
	private String publishDate;         	//  公布时间                                    
	private String executeCourt;         //   执行法院                                    
	private String province;        		 //   省份                                        
	private String executeDocumentnumber;  // 执行文号                                    
	private String gistunit;         			//  做出执行依据单位                            
	private String duty;        					 // 生效法律文书确定的义务      （数据长度超过2000长度）                    
	private String disrupttypename;        //  失信被执行人为具体情形     （数据长度超过2000长度）                    
	private String performance;        		 //  被执行人的履情况    (1-全部未履行,2-部分未履行,3-失信记录已退出)      （数据长度超过2000长度）             
	private String performedpar;         	//   已履行    （数据长度超过2000长度）               
	private String unperformpart;        	 //  未履行   （数据长度超过2000长度）                         
	private String focusNumber;        		 // 关注次数
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getEntNo() {
		return entNo;
	}
	public void setEntNo(String entNo) {
		this.entNo = entNo;
	}
	public String getIdentityDeparture() {
		return identityDeparture;
	}
	public void setIdentityDeparture(String identityDeparture) {
		this.identityDeparture = identityDeparture;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getFilingDate() {
		return filingDate;
	}
	public void setFilingDate(String filingDate) {
		this.filingDate = filingDate;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getExecuteCourt() {
		return executeCourt;
	}
	public void setExecuteCourt(String executeCourt) {
		this.executeCourt = executeCourt;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getExecuteDocumentnumber() {
		return executeDocumentnumber;
	}
	public void setExecuteDocumentnumber(String executeDocumentnumber) {
		this.executeDocumentnumber = executeDocumentnumber;
	}
	public String getGistunit() {
		return gistunit;
	}
	public void setGistunit(String gistunit) {
		this.gistunit = gistunit;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getDisrupttypename() {
		return disrupttypename;
	}
	public void setDisrupttypename(String disrupttypename) {
		this.disrupttypename = disrupttypename;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	public String getPerformedpar() {
		return performedpar;
	}
	public void setPerformedpar(String performedpar) {
		this.performedpar = performedpar;
	}
	public String getUnperformpart() {
		return unperformpart;
	}
	public void setUnperformpart(String unperformpart) {
		this.unperformpart = unperformpart;
	}
	public String getFocusNumber() {
		return focusNumber;
	}
	public void setFocusNumber(String focusNumber) {
		this.focusNumber = focusNumber;
	}
	
	
	                                                                              
}
