package duomi.com.httpIvk.house.param;

import java.util.List;

public class HouseListResponse<T> {
	public String code;
	public String msg;
	public List<T> result;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

}
