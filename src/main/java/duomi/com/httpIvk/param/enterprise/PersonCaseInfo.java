package duomi.com.httpIvk.param.enterprise;

public class PersonCaseInfo {
	private String caseTime;         	//   案发时间                                                                                                                           
	private String caseReason;    		//   案由                                                                                                                               
	private String caseVal;         	//   案值                                                                                                                               
	private String caseType;        	 //  案件类型                                                                                                                           
	private String caseResult;         //  案件结果                                                                                                                           
	private String cardNO;         		//   证件号                                                                                                                             
	private String name;         			//   当时人                                                                                                                             
	private String illegFact;         //   主要违法事实                                                                                                                       
	private String exesort;        	 //    执行类别（1.已执行2.部分已执行3.正常执行4.未执行5.部分未执行6.强制执行7.分期（延期）执行）                                         
	private String penam;        		 //    处罚金额(万元)                                                                                                                     
	private String penauth;         	//   作出行政处罚决定机关名称                                                                                                           
	private String penbasis;         //    处罚依据                                                                                                                           
	private String pendecissdate;   //     处罚决定书签发日期                                                                                                                 
	private String pendecno;         //    处罚决定文书                                                                                                                       
	private String penexest;         //    处罚执行情况                                                                                                                       
	private String penresult;         //   处罚结果                                                                                                                           
	private String pentype;         	//   处罚种类（1.警告2.罚款3.没收违法所得、没收非法财物4.责令停产停业5.暂扣或者吊销许可证 6.行政拘留7.法律、法规规定的其他行政处罚方式）
	public String getCaseTime() {
		return caseTime;
	}
	public void setCaseTime(String caseTime) {
		this.caseTime = caseTime;
	}
	public String getCaseReason() {
		return caseReason;
	}
	public void setCaseReason(String caseReason) {
		this.caseReason = caseReason;
	}
	public String getCaseVal() {
		return caseVal;
	}
	public void setCaseVal(String caseVal) {
		this.caseVal = caseVal;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getCaseResult() {
		return caseResult;
	}
	public void setCaseResult(String caseResult) {
		this.caseResult = caseResult;
	}
	public String getCardNO() {
		return cardNO;
	}
	public void setCardNO(String cardNO) {
		this.cardNO = cardNO;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIllegFact() {
		return illegFact;
	}
	public void setIllegFact(String illegFact) {
		this.illegFact = illegFact;
	}
	public String getExesort() {
		return exesort;
	}
	public void setExesort(String exesort) {
		this.exesort = exesort;
	}
	public String getPenam() {
		return penam;
	}
	public void setPenam(String penam) {
		this.penam = penam;
	}
	public String getPenauth() {
		return penauth;
	}
	public void setPenauth(String penauth) {
		this.penauth = penauth;
	}
	public String getPenbasis() {
		return penbasis;
	}
	public void setPenbasis(String penbasis) {
		this.penbasis = penbasis;
	}
	public String getPendecissdate() {
		return pendecissdate;
	}
	public void setPendecissdate(String pendecissdate) {
		this.pendecissdate = pendecissdate;
	}
	public String getPendecno() {
		return pendecno;
	}
	public void setPendecno(String pendecno) {
		this.pendecno = pendecno;
	}
	public String getPenexest() {
		return penexest;
	}
	public void setPenexest(String penexest) {
		this.penexest = penexest;
	}
	public String getPenresult() {
		return penresult;
	}
	public void setPenresult(String penresult) {
		this.penresult = penresult;
	}
	public String getPentype() {
		return pentype;
	}
	public void setPentype(String pentype) {
		this.pentype = pentype;
	}

}
