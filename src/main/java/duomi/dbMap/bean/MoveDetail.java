package duomi.dbMap.bean;

/**
 * Created by DEllComputer on 2018/4/17.
 * 运动轨迹详情（数据库中查询出来,未处理）
 */
public class MoveDetail {

    private String callStartTime;
    private String callAddress;

    public String getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(String callStartTime) {
        this.callStartTime = callStartTime;
    }

    public String getCallAddress() {
        return callAddress;
    }

    public void setCallAddress(String callAddress) {
        this.callAddress = callAddress;
    }
}
