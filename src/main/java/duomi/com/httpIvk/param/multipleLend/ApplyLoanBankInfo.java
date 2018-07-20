package duomi.com.httpIvk.param.multipleLend;

public class ApplyLoanBankInfo {
	private String selfnum;// 本机构的申请次数
	private String allnum;// 银行机构申请次数
	private String orgnum;// 银行机构申请机构数
	private String tot_mons;// 有申请记录月份数
	private String avg_monnum;// 平均每月申请次数(有申请月份平均)
	private String max_monnum;// 最大月申请次数
	private String min_monnum;// 最小月申请次数
	private String min_inteday;// 申请最小间隔天数
	private String max_inteday;// 申请最大间隔天数
	private String inteday;//

	public String getInteday() {
		return inteday;
	}

	public void setInteday(String inteday) {
		this.inteday = inteday;
	}

	public String getSelfnum() {
		return selfnum;
	}

	public void setSelfnum(String selfnum) {
		this.selfnum = selfnum;
	}

	public String getAllnum() {
		return allnum;
	}

	public void setAllnum(String allnum) {
		this.allnum = allnum;
	}

	public String getOrgnum() {
		return orgnum;
	}

	public void setOrgnum(String orgnum) {
		this.orgnum = orgnum;
	}

	public String getTot_mons() {
		return tot_mons;
	}

	public void setTot_mons(String tot_mons) {
		this.tot_mons = tot_mons;
	}

	public String getAvg_monnum() {
		return avg_monnum;
	}

	public void setAvg_monnum(String avg_monnum) {
		this.avg_monnum = avg_monnum;
	}

	public String getMax_monnum() {
		return max_monnum;
	}

	public void setMax_monnum(String max_monnum) {
		this.max_monnum = max_monnum;
	}

	public String getMin_monnum() {
		return min_monnum;
	}

	public void setMin_monnum(String min_monnum) {
		this.min_monnum = min_monnum;
	}

	public String getMin_inteday() {
		return min_inteday;
	}

	public void setMin_inteday(String min_inteday) {
		this.min_inteday = min_inteday;
	}

	public String getMax_inteday() {
		return max_inteday;
	}

	public void setMax_inteday(String max_inteday) {
		this.max_inteday = max_inteday;
	}

}
