package duomi.com.httpIvk.param.mobiledetail;

import net.sf.json.JSONObject;

public class MBDetailData {
	private String channel_type;// 渠道类型。运营商是 YYS
	private String channel_code;// 渠道编码。运营商是 100000
	private String channel_src;// 渠道数据源。如：中国移动;
	private String channel_attr;// 渠道属性。如：浙江
	private String real_name;// 真实姓名。如：张三;
	private String identity_code;// 身份证号码。如：3301021234567890123
	private String user_mobile;// 手机号码。如：13912345678
	private String created_time;// 任务创建时间。如：2016-10-30 12:55:55
	private String user_name;// 用户名，即手机号码，如：13912345678
	private MBDetailTaskData task_data;// 原始数据，请查看 运营商数据字段 部分
	private MbDetailLostData lost_data;// 缺失数据信息
	private String task_id; // 任务编码。每个任务都有唯一的任务编码，如：TASKYYS1000001234567890
	private JSONObject point_info;

	public String getChannel_type() {
		return channel_type;
	}

	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}

	public String getChannel_code() {
		return channel_code;
	}

	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}

	public String getChannel_src() {
		return channel_src;
	}

	public void setChannel_src(String channel_src) {
		this.channel_src = channel_src;
	}

	public String getChannel_attr() {
		return channel_attr;
	}

	public void setChannel_attr(String channel_attr) {
		this.channel_attr = channel_attr;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getIdentity_code() {
		return identity_code;
	}

	public void setIdentity_code(String identity_code) {
		this.identity_code = identity_code;
	}

	public String getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public MBDetailTaskData getTask_data() {
		return task_data;
	}

	public void setTask_data(MBDetailTaskData task_data) {
		this.task_data = task_data;
	}

	public MbDetailLostData getLost_data() {
		return lost_data;
	}

	public void setLost_data(MbDetailLostData lost_data) {
		this.lost_data = lost_data;
	}

	public JSONObject getPoint_info() {
		return point_info;
	}

	public void setPoint_info(JSONObject point_info) {
		this.point_info = point_info;
	}
	
	

}
