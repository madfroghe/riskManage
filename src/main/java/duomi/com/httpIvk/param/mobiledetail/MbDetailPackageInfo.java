package duomi.com.httpIvk.param.mobiledetail;

import net.sf.json.JSONArray;

public class MbDetailPackageInfo {
	private String brand_name; // 品牌
	private String pay_type; // 缴费类型
	private JSONArray package_detail;//

	public JSONArray getPackage_detail() {
		return package_detail;
	}

	public void setPackage_detail(JSONArray package_detail) {
		this.package_detail = package_detail;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

}
