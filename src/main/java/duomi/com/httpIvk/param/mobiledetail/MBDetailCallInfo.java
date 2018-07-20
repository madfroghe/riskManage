package duomi.com.httpIvk.param.mobiledetail;

import java.util.List;

public class MBDetailCallInfo {
	private String call_info;// 通话周期。YYYY-MM
	private String total_call_time;// 总通话时长。时长精确到秒
	private String total_call_count;// 总通话次数。整形数字
	private String total_fee;// 费用合计。整形数字精确到分
	private List<DBDetailCallRecord> call_record;// 通话记录

	public String getCall_info() {
		return call_info;
	}

	public void setCall_info(String call_info) {
		this.call_info = call_info;
	}

	public String getTotal_call_time() {
		return total_call_time;
	}

	public void setTotal_call_time(String total_call_time) {
		this.total_call_time = total_call_time;
	}

	public String getTotal_call_count() {
		return total_call_count;
	}

	public void setTotal_call_count(String total_call_count) {
		this.total_call_count = total_call_count;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public List<DBDetailCallRecord> getCall_record() {
		return call_record;
	}

	public void setCall_record(List<DBDetailCallRecord> call_record) {
		this.call_record = call_record;
	}

}
