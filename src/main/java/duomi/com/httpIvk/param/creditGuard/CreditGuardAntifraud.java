package duomi.com.httpIvk.param.creditGuard;

import java.util.List;

import net.sf.json.JSONArray;

public class CreditGuardAntifraud {
	private String final_score;//
	private String final_decision;
	private List<CreditGuardRiskItems> risk_items;
	private JSONArray output_fields;
	public String getFinal_score() {
		return final_score;
	}
	public void setFinal_score(String final_score) {
		this.final_score = final_score;
	}
	public String getFinal_decision() {
		return final_decision;
	}
	public void setFinal_decision(String final_decision) {
		this.final_decision = final_decision;
	}
	public List<CreditGuardRiskItems> getRisk_items() {
		return risk_items;
	}
	public void setRisk_items(List<CreditGuardRiskItems> risk_items) {
		this.risk_items = risk_items;
	}
	public JSONArray getOutput_fields() {
		return output_fields;
	}
	public void setOutput_fields(JSONArray output_fields) {
		this.output_fields = output_fields;
	}
	
	
}
