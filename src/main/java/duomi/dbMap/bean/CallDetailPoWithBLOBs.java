package duomi.dbMap.bean;

public class CallDetailPoWithBLOBs extends CallDetailPo {
    private String reqJson;

    private String resJson;

    public String getReqJson() {
        return reqJson;
    }

    public void setReqJson(String reqJson) {
        this.reqJson = reqJson;
    }

    public String getResJson() {
        return resJson;
    }

    public void setResJson(String resJson) {
        this.resJson = resJson;
    }
}