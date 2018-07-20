package duomi.com.httpIvk.param.creditGuard;

import net.sf.json.JSONObject;

public class CreditGuardResponse {
	private boolean success; // 是否调用成功
	private String id;// 进件ID
	private String reason_code;// 调用失败时的错误码
	private String reason_desc;// 错误详情描述
	private String nextService;// 下一步
	private JSONObject supplementInfo;// 补充详情
	private CreditGuardResult result_desc; // 结果详情

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReason_code() {
		return reason_code;
	}

	public void setReason_code(String reason_code) {
		this.reason_code = reason_code;
	}

	public String getReason_desc() {
		return reason_desc;
	}

	public void setReason_desc(String reason_desc) {
		this.reason_desc = reason_desc;
	}

	public String getNextService() {
		return nextService;
	}

	public void setNextService(String nextService) {
		this.nextService = nextService;
	}

	public JSONObject getSupplementInfo() {
		return supplementInfo;
	}

	public void setSupplementInfo(JSONObject supplementInfo) {
		this.supplementInfo = supplementInfo;
	}

	public CreditGuardResult getResult_desc() {
		return result_desc;
	}

	public void setResult_desc(CreditGuardResult result_desc) {
		this.result_desc = result_desc;
	}

}
