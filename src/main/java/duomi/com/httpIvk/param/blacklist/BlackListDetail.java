package duomi.com.httpIvk.param.blacklist;

public class BlackListDetail {
	/**
	 * 数据入库时间 （天）
	 *  0-6 ； 7-29 ； 30-89 ； 90-179 ；
	 *  180-364  ；365-1000 ；>1000
	 */
	private String overdue_days;                   
	/**
	 * 逾期时间 （天）
	 * 0-6 ； 7-29 ； 30-89 ； 90-179  ；
	 * 180-364  365-1000 ；>1000
	 */
	private String overdue_date;      
	/**
	 * 法律状态(未知,已结案,执行中)  
	 */
	private String legal_state;                                                                          
	/**
	 * 黑名单类型 (借款违约,失信网名单,人法网名单)
	 */
	private String type;                    
	/** 
	 *逾期金额 （元）
	 *	0-500 ； 500-1000 ； 1000-5000  ；
	 *  5000-10000  ；10000-50000  ；
	 *  50000-100000  ；>100000
	 */
	private String overdue_amount;
	
	public String getOverdue_days() {
		return overdue_days;
	}
	public void setOverdue_days(String overdue_days) {
		this.overdue_days = overdue_days;
	}
	public String getOverdue_date() {
		return overdue_date;
	}
	public void setOverdue_date(String overdue_date) {
		this.overdue_date = overdue_date;
	}
	public String getLegal_state() {
		return legal_state;
	}
	public void setLegal_state(String legal_state) {
		this.legal_state = legal_state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOverdue_amount() {
		return overdue_amount;
	}
	public void setOverdue_amount(String overdue_amount) {
		this.overdue_amount = overdue_amount;
	}  
	
}
