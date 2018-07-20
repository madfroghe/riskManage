package duomi.com.httpIvk.house.param;

public class HouseUseReq {
	private static final String USERNAME = "18011512324";
	private static final String PASSWORD = "123456";
	private static final String APPKEY = "6D04268CE828CB17BBF263D174271353DFF74A1CCFCBE97168A876B4951E8B71";

	private String username;
	private String password;
	private String appKey;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public HouseUseReq() {
		this.username = USERNAME;
		this.password = PASSWORD;
		this.appKey = APPKEY;
	}

}
