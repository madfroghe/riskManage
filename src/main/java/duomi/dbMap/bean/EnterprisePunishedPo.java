package duomi.dbMap.bean;

import java.math.BigDecimal;
import java.util.Date;

public class EnterprisePunishedPo {
    private Long id;

    private Long queryid;

    private String caseNo;

    private String name;

    private String sex;

    private Integer age;

    private String cardNumber;

    private String identityDeparture;

    private Date filingDate;

    private BigDecimal executeTarget;

    private String province;

    private Integer focusNumber;

    private String caseState;

    private String courtName;

    private String type;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public Date getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(Date filingDate) {
        this.filingDate = filingDate;
    }

    public BigDecimal getExecuteTarget() {
        return executeTarget;
    }

    public void setExecuteTarget(BigDecimal executeTarget) {
        this.executeTarget = executeTarget;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getFocusNumber() {
        return focusNumber;
    }

    public void setFocusNumber(Integer focusNumber) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}