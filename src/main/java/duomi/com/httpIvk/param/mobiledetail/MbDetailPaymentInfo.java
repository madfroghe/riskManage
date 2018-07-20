package duomi.com.httpIvk.param.mobiledetail;

public class MbDetailPaymentInfo {
	private String pay_date;// 日期。YYYY-MM-DD或未知
	private String pay_fee;// 金额。整形数字精确到分;
	private String pay_channel;// 缴费渠道;
	private String pay_type;// 缴费方式。线上充值、线下充值、现金充值、银行充值、第三方支付、其他充值、未知

	public String getPay_date() {
		return pay_date;
	}

	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}

	public String getPay_fee() {
		return pay_fee;
	}

	public void setPay_fee(String pay_fee) {
		this.pay_fee = pay_fee;
	}

	public String getPay_channel() {
		return pay_channel;
	}

	public void setPay_channel(String pay_channel) {
		this.pay_channel = pay_channel;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

}
