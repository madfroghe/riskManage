package duomi.com.httpIvk.param.mobiledetail;

public class MBDetailAccountInfo {

	private String account_balance;// 账户余额。整形数字精确到分
	private String current_fee;// 当前话费。整形数字精确到分
	private String credit_level;// 账户星级。0-5或未知
	private String mobile_status;// 账户状态。正常、欠费、停机、销户、未激活、未知
	private String net_time;// 入网时间。YYYY-MM-DD或未知
	private String net_age;// 网龄。整形数字精确到月或未知
	private String real_info;// 实名制信息。已登记、未登记、未知
	private String credit_point;// 积分。整形数字

	private String prom_available;//
	private String credit_score;//
	private String balance_unavailable;//
	private String balance_available;//
	private String sim_card;//
	private String land_level;//
	private String prepay_unavailable;//
	private String prepay_available;//
	private String prom_unavailable;//
	private String credit_effective_time;//
	private String puk_code;//
	private String roam_state;//

	public String getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(String account_balance) {
		this.account_balance = account_balance;
	}

	public String getCurrent_fee() {
		return current_fee;
	}

	public void setCurrent_fee(String current_fee) {
		this.current_fee = current_fee;
	}

	public String getCredit_level() {
		return credit_level;
	}

	public void setCredit_level(String credit_level) {
		this.credit_level = credit_level;
	}

	public String getMobile_status() {
		return mobile_status;
	}

	public void setMobile_status(String mobile_status) {
		this.mobile_status = mobile_status;
	}

	public String getNet_time() {
		return net_time;
	}

	public void setNet_time(String net_time) {
		this.net_time = net_time;
	}

	public String getNet_age() {
		return net_age;
	}

	public void setNet_age(String net_age) {
		this.net_age = net_age;
	}

	public String getReal_info() {
		return real_info;
	}

	public void setReal_info(String real_info) {
		this.real_info = real_info;
	}

	public String getCredit_point() {
		return credit_point;
	}

	public void setCredit_point(String credit_point) {
		this.credit_point = credit_point;
	}

	public String getProm_available() {
		return prom_available;
	}

	public void setProm_available(String prom_available) {
		this.prom_available = prom_available;
	}

	public String getCredit_score() {
		return credit_score;
	}

	public void setCredit_score(String credit_score) {
		this.credit_score = credit_score;
	}

	public String getBalance_unavailable() {
		return balance_unavailable;
	}

	public void setBalance_unavailable(String balance_unavailable) {
		this.balance_unavailable = balance_unavailable;
	}

	public String getBalance_available() {
		return balance_available;
	}

	public void setBalance_available(String balance_available) {
		this.balance_available = balance_available;
	}

	public String getSim_card() {
		return sim_card;
	}

	public void setSim_card(String sim_card) {
		this.sim_card = sim_card;
	}

	public String getLand_level() {
		return land_level;
	}

	public void setLand_level(String land_level) {
		this.land_level = land_level;
	}

	public String getPrepay_unavailable() {
		return prepay_unavailable;
	}

	public void setPrepay_unavailable(String prepay_unavailable) {
		this.prepay_unavailable = prepay_unavailable;
	}

	public String getPrepay_available() {
		return prepay_available;
	}

	public void setPrepay_available(String prepay_available) {
		this.prepay_available = prepay_available;
	}

	public String getProm_unavailable() {
		return prom_unavailable;
	}

	public void setProm_unavailable(String prom_unavailable) {
		this.prom_unavailable = prom_unavailable;
	}

	public String getCredit_effective_time() {
		return credit_effective_time;
	}

	public void setCredit_effective_time(String credit_effective_time) {
		this.credit_effective_time = credit_effective_time;
	}

	public String getPuk_code() {
		return puk_code;
	}

	public void setPuk_code(String puk_code) {
		this.puk_code = puk_code;
	}

	public String getRoam_state() {
		return roam_state;
	}

	public void setRoam_state(String roam_state) {
		this.roam_state = roam_state;
	}

}
