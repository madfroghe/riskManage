package duomi.com.httpIvk.param.blacklist;

import java.util.List;

public class BlackListResult {
	private String name;           //姓名                
	private String idCard;         //身份证号            
	private String status;         //查询状态            
	private String statusDesc;     //状态描述            
	private List<BlackListDetail> data;           //查询数据，黑名单信息
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
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
	public List<BlackListDetail> getData() {
		return data;
	}
	public void setData(List<BlackListDetail> data) {
		this.data = data;
	}

	
}
