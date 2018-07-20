package duomi.com.httpIvk.param.antifraud;

import java.util.List;
import java.util.Set;

public class AntiFraudResult {
	public String finalDecision;
	public String finalScore;
	public List<AntiFraudStrategy> strategySet;
	public String status;
	public String getFinalDecision() {
		return finalDecision;
	}
	public void setFinalDecision(String finalDecision) {
		this.finalDecision = finalDecision;
	}
	public String getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<AntiFraudStrategy> getStrategySet() {
		return strategySet;
	}
	public void setStrategySet(List<AntiFraudStrategy> strategySet) {
		this.strategySet = strategySet;
	}
	
	
	
}
