package duomi.dispatch.request.yijianCloud;

import duomi.dispatch.request.ComRequest;

/**
 * Created by DEllComputer on 2018/3/13.
 */
public class ConsumptionPreferenceRequest extends ComRequest {

    private String appid;  //必须,授权ID
    private String passport; //必须,身份证号或者护照号
    private String authID; //必须,授权码

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAuthID() {
        return authID;
    }

    public void setAuthID(String authID) {
        this.authID = authID;
    }
}