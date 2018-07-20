package duomi.com.httpIvk.param.phone;

public class MobileStatusResult {
	private String mobile;
	/** /手机号状态 返回共8种状态：0,1,2,3,4,5,6,7
	 *  0： 在网状态正常；
		1：在网状态停机；
		2：预销号；
		3：已销号/未启用；
		4：在网但不可用；
		5：号码状态有误；
		6：认证通过；
		7：认证未通过。
		*/
	private String mobileState;
	private String status;
	private String statusDesc;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMobileState() {
		return mobileState;
	}
	public void setMobileState(String mobileState) {
		this.mobileState = mobileState;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	
}
