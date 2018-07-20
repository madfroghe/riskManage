package duomi.com.httpIvk.param.mobiledetail;

public class DBDetailCallRecord {
	private String call_start_time;// 通话地点。如：浙江省.杭州市、海外.美国、未知
	private String call_address;// 通话地点。如：浙江省.杭州市、海外.美国、未知
	private String call_type_name;// 呼叫类型。主叫、被叫、呼转、未知
	private String call_other_number;// 对方号码
	private String call_time;// 通话时长。时长精确到秒
	private String call_land_type;// 通话类型。本地通话、国内长途、国际长途、港澳台通话、未知
	private String call_cost;// 费用小计。整形数字精确到分
	private String call_long_distance;
	private String call_roam_cost;
	private String call_discount;

	public String getCall_start_time() {
		return call_start_time;
	}

	public void setCall_start_time(String call_start_time) {
		this.call_start_time = call_start_time;
	}

	public String getCall_address() {
		return call_address;
	}

	public void setCall_address(String call_address) {
		this.call_address = call_address;
	}

	public String getCall_type_name() {
		return call_type_name;
	}

	public void setCall_type_name(String call_type_name) {
		this.call_type_name = call_type_name;
	}

	public String getCall_other_number() {
		return call_other_number;
	}

	public void setCall_other_number(String call_other_number) {
		this.call_other_number = call_other_number;
	}

	public String getCall_time() {
		return call_time;
	}

	public void setCall_time(String call_time) {
		this.call_time = call_time;
	}

	public String getCall_land_type() {
		return call_land_type;
	}

	public void setCall_land_type(String call_land_type) {
		this.call_land_type = call_land_type;
	}

	public String getCall_cost() {
		return call_cost;
	}

	public void setCall_cost(String call_cost) {
		this.call_cost = call_cost;
	}

	public String getCall_long_distance() {
		return call_long_distance;
	}

	public void setCall_long_distance(String call_long_distance) {
		this.call_long_distance = call_long_distance;
	}

	public String getCall_roam_cost() {
		return call_roam_cost;
	}

	public void setCall_roam_cost(String call_roam_cost) {
		this.call_roam_cost = call_roam_cost;
	}

	public String getCall_discount() {
		return call_discount;
	}

	public void setCall_discount(String call_discount) {
		this.call_discount = call_discount;
	}

}
