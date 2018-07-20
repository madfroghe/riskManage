package duomi.com.httpIvk.param.overdue;

public class LoanOverdue {
	private String P_TYPE;// 平台类型（1 银行 2 非银行）
	private String PLATFORMCODE;// 平台代码
	private String REJECTIONTIME;// 驳回时间
	private String COUNTS;// 该平台的逾期数量
	private String MONEY;// 逾期金额区间
	private String PROVINCE;// 省
	private String CITY;// 市

	public String getP_TYPE() {
		return P_TYPE;
	}

	public void setP_TYPE(String p_TYPE) {
		P_TYPE = p_TYPE;
	}

	public String getPLATFORMCODE() {
		return PLATFORMCODE;
	}

	public void setPLATFORMCODE(String pLATFORMCODE) {
		PLATFORMCODE = pLATFORMCODE;
	}

	public String getREJECTIONTIME() {
		return REJECTIONTIME;
	}

	public void setREJECTIONTIME(String rEJECTIONTIME) {
		REJECTIONTIME = rEJECTIONTIME;
	}

	public String getCOUNTS() {
		return COUNTS;
	}

	public void setCOUNTS(String cOUNTS) {
		COUNTS = cOUNTS;
	}

	public String getMONEY() {
		return MONEY;
	}

	public void setMONEY(String mONEY) {
		MONEY = mONEY;
	}

	public String getPROVINCE() {
		return PROVINCE;
	}

	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

}
