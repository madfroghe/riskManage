package duomi.com.httpIvk.param.mobiledetail;

import java.util.List;

/**
 * 账单信息
 * 
 * @author Administrator
 *
 */
public class MbDetailBillInfo {
	private String bill_cycle;// 账单周期。YYYY-MM
	private String bill_fee;// 账单费用。整形数字精确到分
	private String bill_discount;// 减免。整形数字精确到分
	private String bill_total;// 费用合计。整形数字精确到分
	private List<MbDetailBillRecord> bill_record;// 账单记录

	public String getBill_cycle() {
		return bill_cycle;
	}

	public void setBill_cycle(String bill_cycle) {
		this.bill_cycle = bill_cycle;
	}

	public String getBill_fee() {
		return bill_fee;
	}

	public void setBill_fee(String bill_fee) {
		this.bill_fee = bill_fee;
	}

	public String getBill_discount() {
		return bill_discount;
	}

	public void setBill_discount(String bill_discount) {
		this.bill_discount = bill_discount;
	}

	public String getBill_total() {
		return bill_total;
	}

	public void setBill_total(String bill_total) {
		this.bill_total = bill_total;
	}

	public List<MbDetailBillRecord> getBill_record() {
		return bill_record;
	}

	public void setBill_record(List<MbDetailBillRecord> bill_record) {
		this.bill_record = bill_record;
	}

}
