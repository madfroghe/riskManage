package duomi.dispatch.request.yijianCloud;

import duomi.dispatch.request.ComRequest;

/**
 * Created by DEllComputer on 2018/4/3.
 */
public class PersonJzcxRequest extends ComRequest {

    private String appid;  //必须,授权ID
    private String userName; //必须，姓名
    private String certCode; //必须,身份证号
    private Integer pageNo;  //非必须,查询页码数
    private String range;   //非必须,每页数量(不能大于20条，默认10条)
    private String authID;  //必须，授权码

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
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

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getAuthID() {
        return authID;
    }

    public void setAuthID(String authID) {
        this.authID = authID;
    }
}
