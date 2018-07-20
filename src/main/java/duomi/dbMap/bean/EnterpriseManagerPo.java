package duomi.dbMap.bean;

import java.math.BigDecimal;
import java.util.Date;

public class EnterpriseManagerPo {
    private Long id;

    private Long queryid;

    private String name;

    private String entName;

    private String entNo;

    private String entType;

    private BigDecimal regcapital;

    private String currency;

    private String status;

    private String position;

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

    public BigDecimal getRegcapital() {
        return regcapital;
    }

    public void setRegcapital(BigDecimal regcapital) {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}