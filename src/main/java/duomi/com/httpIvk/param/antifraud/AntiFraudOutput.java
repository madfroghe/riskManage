package duomi.com.httpIvk.param.antifraud;

import duomi.com.httpIvk.param.BaseResponse;

public class AntiFraudOutput extends BaseResponse<AntiFraudResult> {
	private AntiFraudResult data;

	public AntiFraudResult getData() {
		return data;
	}

	public void setData(AntiFraudResult data) {
		this.data = data;
	}
	
}
