package duomi.com.httpIvk.param.creditGuard;

import net.sf.json.JSONArray;

public class CreditGuardRiskItems {
	private String rule_id;
	private int score;
	private String decision;
	private String risk_name;
	private JSONArray risk_detail;
	public String getRule_id() {
		return rule_id;
	}
	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	public String getRisk_name() {
		return risk_name;
	}
	public void setRisk_name(String risk_name) {
		this.risk_name = risk_name;
	}
	public JSONArray getRisk_detail() {
		return risk_detail;
	}
	public void setRisk_detail(JSONArray risk_detail) {
		this.risk_detail = risk_detail;
	}
	
	
	
}
