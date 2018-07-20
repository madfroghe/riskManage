package duomi.dbMap.bean;

import java.math.BigDecimal;
import java.util.Date;

public class EnterpriseShareholderPo {
    private Long id;

    private Long queryid;

    private String name;

    private String entName;

    private String entNo;

    private String entType;

    private BigDecimal regcapital;

    private String currency;

    private BigDecimal contribution;

    private String contributionCurrency;

    private String card;

    private String status;

    private String fundedratio;

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

    public BigDecimal getContribution() {
        return contribution;
    }

    public void setContribution(BigDecimal contribution) {
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

    public String getFundedratio() {
        return fundedratio;
    }

    public void setFundedratio(String fundedratio) {
        this.fundedratio = fundedratio;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}