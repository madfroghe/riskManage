package duomi.dispatch.request.yijianCloud;

import duomi.dispatch.request.ComRequest;

/**
 * Created by DEllComputer on 2018/3/1.
 */
public class PersonalFlightRequest extends ComRequest {
    private String appid;  //必须 授权appId
    private String certCode; //必须 身份证号
    private String userName; //必须 姓名
    private String queryMonth; //必须 统计月数:3、6、12(代表 3 个月、6 个月、12 个月，只能为以上值)
    private String authID;  //必须 授权码

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQueryMonth() {
        return queryMonth;
    }

    public void setQueryMonth(String queryMonth) {
        this.queryMonth = queryMonth;
    }

    public String getAuthID() {
        return authID;
    }

    public void setAuthID(String authID) {
        this.authID = authID;
    }
}
