package duomi.dispatch.request.yijianCloud;

import duomi.dispatch.request.ComRequest;

/**
 * Created by DEllComputer on 2018/4/27.
 */
public class LocationOrderRequest extends ComRequest {
    private String appid;  //必须 授权appId
    private String telNO;  //必须 手机号码
    private String authID;  //必须 授权码

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTelNO() {
        return telNO;
    }

    public void setTelNO(String telNO) {
        this.telNO = telNO;
    }

    public String getAuthID() {
        return authID;
    }

    public void setAuthID(String authID) {
        this.authID = authID;
    }
}
