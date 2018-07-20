package duomi.dbMap.bean;

import java.util.Date;

public class MobileDetailRecordPo {
    private Long id;

    private String appNo;

    private String mobile;

    private String taskId;

    private String callStartTime;

    private String callAddress;

    private String callTypeName;

    private String callOtherNumber;

    private String callTime;

    private String callLandType;

    private String callCost;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(String callStartTime) {
        this.callStartTime = callStartTime;
    }

    public String getCallAddress() {
        return callAddress;
    }

    public void setCallAddress(String callAddress) {
        this.callAddress = callAddress;
    }

    public String getCallTypeName() {
        return callTypeName;
    }

    public void setCallTypeName(String callTypeName) {
        this.callTypeName = callTypeName;
    }

    public String getCallOtherNumber() {
        return callOtherNumber;
    }

    public void setCallOtherNumber(String callOtherNumber) {
        this.callOtherNumber = callOtherNumber;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getCallLandType() {
        return callLandType;
    }

    public void setCallLandType(String callLandType) {
        this.callLandType = callLandType;
    }

    public String getCallCost() {
        return callCost;
    }

    public void setCallCost(String callCost) {
        this.callCost = callCost;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}