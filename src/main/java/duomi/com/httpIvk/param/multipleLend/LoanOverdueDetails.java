package duomi.com.httpIvk.param.multipleLend;

public class LoanOverdueDetails {
	//平台代码
	public String code;
	//逾期次数
	public String counts;
	//逾期/欠款金额区间
	public String money;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
	
}
