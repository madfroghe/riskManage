package duomi.com.httpIvk.param.multipleLend;

import duomi.com.httpIvk.param.BaseRequest;

public class MultipleLendInput extends BaseRequest {
	//接口参数：电话号码
	public String mobile;
	//接口参数：时间段（据今几个月，目前只支持1,3,6,9,12,24）
	public String month; 
	//接口参数：平台类型(0 全部 1 银行 2 非银行)
	public String platform;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	
}
