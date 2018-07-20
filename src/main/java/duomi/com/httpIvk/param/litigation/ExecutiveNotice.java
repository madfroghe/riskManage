package duomi.com.httpIvk.param.litigation;

public class ExecutiveNotice {
	private String caseNO; // 案号
	private String identificationNO; // 身份证号
	private String executionTarget; // 执行标的
	private String recordTime; // 立案时间
	private String name; // 被执行人姓名
	private String court; // 法院名称

	public String getCaseNO() {
		return caseNO;
	}

	public void setCaseNO(String caseNO) {
		this.caseNO = caseNO;
	}

	public String getIdentificationNO() {
		return identificationNO;
	}

	public void setIdentificationNO(String identificationNO) {
		this.identificationNO = identificationNO;
	}

	public String getExecutionTarget() {
		return executionTarget;
	}

	public void setExecutionTarget(String executionTarget) {
		this.executionTarget = executionTarget;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

}
