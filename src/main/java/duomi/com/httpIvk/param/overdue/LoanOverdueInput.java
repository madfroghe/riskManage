package duomi.com.httpIvk.param.overdue;

import duomi.com.httpIvk.param.BaseRequest;

public class LoanOverdueInput extends BaseRequest {
	private String mobile; // 电话
	private String month; // 月份

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
