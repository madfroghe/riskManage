package duomi.com.httpIvk.param.mobiledetail;

import java.util.List;

public class MBDetailSmsInfo {
	private String msg_cycle;// 短信周期。YYYY-MM
	private String total_msg_count;// 总短信次数。整形数字
	private String total_msg_cost;// 费用合计。整形数字精确到分
	private List<MBDetailSmsRecord> sms_record;// 短信记录

	public String getMsg_cycle() {
		return msg_cycle;
	}

	public void setMsg_cycle(String msg_cycle) {
		this.msg_cycle = msg_cycle;
	}

	public String getTotal_msg_count() {
		return total_msg_count;
	}

	public void setTotal_msg_count(String total_msg_count) {
		this.total_msg_count = total_msg_count;
	}

	public String getTotal_msg_cost() {
		return total_msg_cost;
	}

	public void setTotal_msg_cost(String total_msg_cost) {
		this.total_msg_cost = total_msg_cost;
	}

	public List<MBDetailSmsRecord> getSms_record() {
		return sms_record;
	}

	public void setSms_record(List<MBDetailSmsRecord> sms_record) {
		this.sms_record = sms_record;
	}

}
