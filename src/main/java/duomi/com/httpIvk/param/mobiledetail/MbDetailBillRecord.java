package duomi.com.httpIvk.param.mobiledetail;

public class MbDetailBillRecord {
	private String fee_name;// 费用名称
	private String fee_category;// 费用类别
	private String fee_amount;// 金额。整形数字精确到分
	private String user_number;// 用户号码。多个号码用逗号分隔

	public String getFee_name() {
		return fee_name;
	}

	public void setFee_name(String fee_name) {
		this.fee_name = fee_name;
	}

	public String getFee_category() {
		return fee_category;
	}

	public void setFee_category(String fee_category) {
		this.fee_category = fee_category;
	}

	public String getFee_amount() {
		return fee_amount;
	}

	public void setFee_amount(String fee_amount) {
		this.fee_amount = fee_amount;
	}

	public String getUser_number() {
		return user_number;
	}

	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}

}
