package duomi.dispatch.request.yijianCloud;

import duomi.dispatch.request.ComRequest;

/**
 * Created by DEllComputer on 2018/4/10.
 */
public class LoanOverDueRequest extends ComRequest {

    private String appid;  //必须 授权appId
    private String telNO;  //必须 手机号码
    private String userName; //必须 姓名
    private String certCode; //必须 身份证号
    private String queryMonth; //非必须 时间段(3,6,9,12,24)单位:月;不填默认 24 个月
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
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
