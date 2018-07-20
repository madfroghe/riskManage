package duomi.dbMap.bean;

import java.util.Date;

public class AntifraudStrategyRulesPo {
    private Long id;

    private Long antifraudId;

    private Long antifraudStrategyId;

    private String ruleName;

    private String ruleId;

    private String score;

    private String decision;

    private String memo;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAntifraudId() {
        return antifraudId;
    }

    public void setAntifraudId(Long antifraudId) {
        this.antifraudId = antifraudId;
    }

    public Long getAntifraudStrategyId() {
        return antifraudStrategyId;
    }

    public void setAntifraudStrategyId(Long antifraudStrategyId) {
        this.antifraudStrategyId = antifraudStrategyId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}