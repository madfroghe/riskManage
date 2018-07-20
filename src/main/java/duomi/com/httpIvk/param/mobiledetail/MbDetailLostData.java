package duomi.com.httpIvk.param.mobiledetail;

public class MbDetailLostData {

	private LostDataBaseInfo base_info;// 个人信息
	private LostDataAccountInfo account_info;// 账户信息
	private LostDataCallInfo call_info;// 通话详单。如果某月份详单缺失，会返回缺失原因，否则不会返回该月份
	private LostDataSmsInfo sms_info;// 短信详单。如果某月份详单缺失，会返回缺失原因，否则不会返回该月份\
	private LostDataPaymentInfo payment_info;// 缴费信息
	private LostDataBillInfo bill_info;// 账单信息。如果某月份账单缺失，会返回缺失原因，否则不会返回该月份

	public LostDataBaseInfo getBase_info() {
		return base_info;
	}

	public void setBase_info(LostDataBaseInfo base_info) {
		this.base_info = base_info;
	}

	public LostDataAccountInfo getAccount_info() {
		return account_info;
	}

	public void setAccount_info(LostDataAccountInfo account_info) {
		this.account_info = account_info;
	}

	public LostDataCallInfo getCall_info() {
		return call_info;
	}

	public void setCall_info(LostDataCallInfo call_info) {
		this.call_info = call_info;
	}

	public LostDataSmsInfo getSms_info() {
		return sms_info;
	}

	public void setSms_info(LostDataSmsInfo sms_info) {
		this.sms_info = sms_info;
	}

	public LostDataPaymentInfo getPayment_info() {
		return payment_info;
	}

	public void setPayment_info(LostDataPaymentInfo payment_info) {
		this.payment_info = payment_info;
	}

	public LostDataBillInfo getBill_info() {
		return bill_info;
	}

	public void setBill_info(LostDataBillInfo bill_info) {
		this.bill_info = bill_info;
	}

	/**
	 * 个人信息
	 * 
	 * @author Administrator
	 *
	 */
	private class LostDataBaseInfo {
		private String reason;

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

	}

	/**
	 * 账户信息
	 * 
	 * @author Administrator
	 *
	 */
	private class LostDataAccountInfo {
		private String reason;

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

	}

	/**
	 * 通话详单。如果某月份详单缺失，会返回缺失原因，否则不会返回该月份
	 * 
	 * @author Administrator
	 *
	 */
	private class LostDataCallInfo {

		private String call_cycle;
		private String reason;

		public String getCall_cycle() {
			return call_cycle;
		}

		public void setCall_cycle(String call_cycle) {
			this.call_cycle = call_cycle;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

	}

	/**
	 * 短信详单
	 * 
	 * @author Administrator
	 *
	 */
	private class LostDataSmsInfo {
		private String msg_cycle;
		private String reason;

		public String getMsg_cycle() {
			return msg_cycle;
		}

		public void setMsg_cycle(String msg_cycle) {
			this.msg_cycle = msg_cycle;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

	}

	/**
	 * 缴费信息
	 * 
	 * @author Administrator
	 *
	 */
	private class LostDataPaymentInfo {
		private String reason;

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

	}

	/**
	 * 账单信息。如果某月份账单缺失，会返回缺失原因，否则不会返回该月份
	 * 
	 * @author Administrator
	 *
	 */
	private class LostDataBillInfo {
		private String bill_cycle;// 账单月份
		private String reason;// 缺失原因

		public String getBill_cycle() {
			return bill_cycle;
		}

		public void setBill_cycle(String bill_cycle) {
			this.bill_cycle = bill_cycle;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

	}

}
