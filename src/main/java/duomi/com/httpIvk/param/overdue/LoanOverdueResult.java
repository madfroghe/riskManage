package duomi.com.httpIvk.param.overdue;

import java.util.List;

public class LoanOverdueResult {
	private String status;// 结果状态 EXIST、NO_DATA
	private String statusDesc;// 结果状态描述
	private String mobile;// 手机号
	private List<LoanOverdue> list;// 数据列表

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

	public List<LoanOverdue> getList() {
		return list;
	}

	public void setList(List<LoanOverdue> list) {
		this.list = list;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
