package duomi.com.httpIvk.param.mobiledetail;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MBDetailTaskData {
	private MBDetailBaseInfo base_info;// 个人信息
	private MBDetailAccountInfo account_info;// 账户信息
	private MbDetailPackageInfo package_info;// 业务信息
	private List<MBDetailCallInfo> call_info;// call_info ARRAY 通话详单。按照月份返回
	private List<MBDetailSmsInfo> sms_info;// 短信详单。按照月份返回
	private List<MbDetailBillInfo> bill_info;// 账单信息。按照月份返回
	private List<MbDetailFamilyInfo> family_info;// 暂不支持 亲情网信息
	private JSONObject point_info;
	private JSONArray payment_info;

	public JSONArray getPayment_info() {
		return payment_info;
	}

	public void setPayment_info(JSONArray payment_info) {
		this.payment_info = payment_info;
	}

	public MBDetailBaseInfo getBase_info() {
		return base_info;
	}

	public void setBase_info(MBDetailBaseInfo base_info) {
		this.base_info = base_info;
	}

	public MBDetailAccountInfo getAccount_info() {
		return account_info;
	}

	public void setAccount_info(MBDetailAccountInfo account_info) {
		this.account_info = account_info;
	}

	public MbDetailPackageInfo getPackage_info() {
		return package_info;
	}

	public void setPackage_info(MbDetailPackageInfo package_info) {
		this.package_info = package_info;
	}

	public List<MBDetailCallInfo> getCall_info() {
		return call_info;
	}

	public void setCall_info(List<MBDetailCallInfo> call_info) {
		this.call_info = call_info;
	}

	public List<MBDetailSmsInfo> getSms_info() {
		return sms_info;
	}

	public void setSms_info(List<MBDetailSmsInfo> sms_info) {
		this.sms_info = sms_info;
	}

	public List<MbDetailBillInfo> getBill_info() {
		return bill_info;
	}

	public void setBill_info(List<MbDetailBillInfo> bill_info) {
		this.bill_info = bill_info;
	}

	public List<MbDetailFamilyInfo> getFamily_info() {
		return family_info;
	}

	public void setFamily_info(List<MbDetailFamilyInfo> family_info) {
		this.family_info = family_info;
	}

	public JSONObject getPoint_info() {
		return point_info;
	}

	public void setPoint_info(JSONObject point_info) {
		this.point_info = point_info;
	}

}
