package duomi.dispatch.request.yijianCloud;

import duomi.dispatch.request.ComRequest;

/**
 * Created by DEllComputer on 2018/5/11.
 */
public class HousePlaceCheckRequest extends ComRequest {
    private String appid;  //必须 授权appId
    private String latitude; //必须 纬度(精确到小数点六位)
    private String longitude; //必须 经度(精确到小数点六位)
    private String telNO;    //必须 手机号码
    private String authID;  //必须 授权码

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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
