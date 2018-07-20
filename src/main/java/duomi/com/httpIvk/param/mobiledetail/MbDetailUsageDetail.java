package duomi.com.httpIvk.param.mobiledetail;

public class MbDetailUsageDetail {
	private String item_name;// 使用项目
	private String item_usage;// 使用量。整形数字
	private String user_number;// 用户号码
	private String item_last_balance;// 上月剩余。整形数字
	private String item_package_amount;// 套餐用量。整形数字

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_usage() {
		return item_usage;
	}

	public void setItem_usage(String item_usage) {
		this.item_usage = item_usage;
	}

	public String getUser_number() {
		return user_number;
	}

	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}

	public String getItem_last_balance() {
		return item_last_balance;
	}

	public void setItem_last_balance(String item_last_balance) {
		this.item_last_balance = item_last_balance;
	}

	public String getItem_package_amount() {
		return item_package_amount;
	}

	public void setItem_package_amount(String item_package_amount) {
		this.item_package_amount = item_package_amount;
	}

}
