package duomi.dispatch.response;

import duomi.com.httpIvk.param.creditGuard.CreditGuardResult;

public class TDCreditGuardResponse {
	public String appNo; // 工单编号
	public String mobile; // 手机编号
	public String tradStat; // 交易状态
	public String dataStat; // 数据状态
	public String code; // 返回代码
	public String codeDesc; // 返回信息
	public CreditGuardResult resData; // 返回结果

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTradStat() {
		return tradStat;
	}

	public void setTradStat(String tradStat) {
		this.tradStat = tradStat;
	}

	public String getDataStat() {
		return dataStat;
	}

	public void setDataStat(String dataStat) {
		this.dataStat = dataStat;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public CreditGuardResult getResData() {
		return resData;
	}

	public void setResData(CreditGuardResult resData) {
		this.resData = resData;
	}

}
