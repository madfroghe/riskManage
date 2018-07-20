package duomi.com.httpIvk.param.antifraud;

import java.util.List;
import java.util.Set;

public class AntiFraudStrategy {
	public String riskType;
	public String strategyDecision;
	public String strategyId;
	public String strategyMode;
	public String strategyName;
	public String strategyScore;
	public List<AntiFraudRules> hitRules;
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
	public List<AntiFraudRules> getHitRules() {
		return hitRules;
	}
	public void setHitRules(List<AntiFraudRules> hitRules) {
		this.hitRules = hitRules;
	}
	
	
	
}
