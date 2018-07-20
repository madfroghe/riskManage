package duomi.com.httpIvk.param.mobiledetail;

public class MBDetailSmsRecord {
	private String msg_start_time;// 起始时间。YYYY-MM-DD HH:mm:SS或未知
	private String msg_type;// 发送方式。发送、接收、未知
	private String msg_other_num;// 对方号码
	private String msg_channel;// 信息类型。业务短信、行业短信、短信、彩信、未知
	private String msg_biz_name;// 业务类型
	private String msg_address;// 短信地区。如：浙江省.杭州市、海外.美国、未知
	private String msg_cost;// 费用小计。整形数字精确到分

	public String getMsg_start_time() {
		return msg_start_time;
	}

	public void setMsg_start_time(String msg_start_time) {
		this.msg_start_time = msg_start_time;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	public String getMsg_other_num() {
		return msg_other_num;
	}

	public void setMsg_other_num(String msg_other_num) {
		this.msg_other_num = msg_other_num;
	}

	public String getMsg_channel() {
		return msg_channel;
	}

	public void setMsg_channel(String msg_channel) {
		this.msg_channel = msg_channel;
	}

	public String getMsg_biz_name() {
		return msg_biz_name;
	}

	public void setMsg_biz_name(String msg_biz_name) {
		this.msg_biz_name = msg_biz_name;
	}

	public String getMsg_address() {
		return msg_address;
	}

	public void setMsg_address(String msg_address) {
		this.msg_address = msg_address;
	}

	public String getMsg_cost() {
		return msg_cost;
	}

	public void setMsg_cost(String msg_cost) {
		this.msg_cost = msg_cost;
	}

}
