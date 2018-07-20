package duomi.com.httpIvk.param.enterprise;

import java.util.List;

public class PersonEnterpriseResult {
	private List<EnterpriseShareholder> ryposshaList; //企业股东信息
	private List<EnterpriseRepresentative> ryposfrList;  //企业法定代表人信息
	private List<EnterprisesManager> ryposperList; //企业主要管理人员信息
	private List<PunishBreakInfo> punishbreakList; //失信被执行人的详细信息
	private List<PunishedInfo> punishedList; //失信被执行人的详细信息
	private List<PersonCaseInfo> personCaseinfoList; //行政处罚历史信息
	
	private String idCard; 
	private String status;
	private String statusDesc;
	public List<EnterpriseShareholder> getRyposshaList() {
		return ryposshaList;
	}
	public void setRyposshaList(List<EnterpriseShareholder> ryposshaList) {
		this.ryposshaList = ryposshaList;
	}
	public List<EnterpriseRepresentative> getRyposfrList() {
		return ryposfrList;
	}
	public void setRyposfrList(List<EnterpriseRepresentative> ryposfrList) {
		this.ryposfrList = ryposfrList;
	}
	public List<EnterprisesManager> getRyposperList() {
		return ryposperList;
	}
	public void setRyposperList(List<EnterprisesManager> ryposperList) {
		this.ryposperList = ryposperList;
	}
	public List<PunishBreakInfo> getPunishbreakList() {
		return punishbreakList;
	}
	public void setPunishbreakList(List<PunishBreakInfo> punishbreakList) {
		this.punishbreakList = punishbreakList;
	}
	public List<PunishedInfo> getPunishedList() {
		return punishedList;
	}
	public void setPunishedList(List<PunishedInfo> punishedList) {
		this.punishedList = punishedList;
	}
	public List<PersonCaseInfo> getPersonCaseinfoList() {
		return personCaseinfoList;
	}
	public void setPersonCaseinfoList(List<PersonCaseInfo> personCaseinfoList) {
		this.personCaseinfoList = personCaseinfoList;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
}
