package duomi.dbMap.bean;

public class DecisionLogRecordPoWithBLOBs extends DecisionLogRecordPo {
    private String reqMsg;

    private String rspMsg;

    public String getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }
}