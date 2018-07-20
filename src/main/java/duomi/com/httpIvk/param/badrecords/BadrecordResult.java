package duomi.com.httpIvk.param.badrecords;

import java.util.List;

public class BadrecordResult {
	private String name;
	private String idCard;
	// 比对结果 在逃、前科、吸毒、涉毒
	private String comparisonResult;
	// 案发时间 案发时间(明文区间)
	private List<String> caseTime;
	// 查询数据状态 EXSIT、NO_DATA
	private String status;

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

	public String getComparisonResult() {
		return comparisonResult;
	}

	public void setComparisonResult(String comparisonResult) {
		this.comparisonResult = comparisonResult;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getCaseTime() {
		return caseTime;
	}

	public void setCaseTime(List<String> caseTime) {
		this.caseTime = caseTime;
	}

}
