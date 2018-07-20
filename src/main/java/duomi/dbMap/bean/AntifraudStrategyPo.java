package duomi.dbMap.bean;

import java.util.Date;

public class AntifraudStrategyPo {
    private Long id;

    private Long antifraudId;

    private String riskType;

    private String strategyDecision;

    private String strategyId;

    private String strategyMode;

    private String strategyName;

    private String strategyScore;

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

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getStrategyDecision() {
        return strategyDecision;
    }

    public void setStrategyDecision(String strategyDecision) {
        this.strategyDecision = strategyDecision;
    }

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public String getStrategyMode() {
        return strategyMode;
    }

    public void setStrategyMode(String strategyMode) {
        this.strategyMode = strategyMode;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyScore() {
        return strategyScore;
    }

    public void setStrategyScore(String strategyScore) {
        this.strategyScore = strategyScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}