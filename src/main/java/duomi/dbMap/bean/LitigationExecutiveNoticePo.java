package duomi.dbMap.bean;

import java.util.Date;

public class LitigationExecutiveNoticePo {
    private Long id;

    private Long queryid;

    private String caseNo;

    private String identificationNo;

    private String executionTarget;

    private String recordTime;

    private String name;

    private String court;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQueryid() {
        return queryid;
    }

    public void setQueryid(Long queryid) {
        this.queryid = queryid;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getIdentificationNo() {
        return identificationNo;
    }

    public void setIdentificationNo(String identificationNo) {
        this.identificationNo = identificationNo;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}