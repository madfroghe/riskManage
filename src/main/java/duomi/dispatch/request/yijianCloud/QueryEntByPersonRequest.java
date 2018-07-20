package duomi.dispatch.request.yijianCloud;

import duomi.dispatch.request.ComRequest;

/**
 * Created by DEllComputer on 2018/5/9.
 */
public class QueryEntByPersonRequest extends ComRequest {

    private String appid; //必须,授权appId
    private String userName; //必须,人员姓名
    private String entityName; //必须 企业名称(需填上公司全称)
    private String authID;   //必须 授权码

    public String getAuthID() {
        return authID;
    }

    public void setAuthID(String authID) {
        this.authID = authID;
    }

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

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
