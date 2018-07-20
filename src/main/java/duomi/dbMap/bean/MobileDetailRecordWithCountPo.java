package duomi.dbMap.bean;

/**
 * Created by DEllComputer on 2018/4/13.
 * 通话详单统计后的实体
 */
public class MobileDetailRecordWithCountPo {

    private String mobile;  //主叫电话
    private String callOtherNumber; //被叫电话
    private Integer callTime;   //通话时长
    private Integer counts; //通话次数
    private Integer called;  //被叫次数
    private String min_call_time; //最早一次通话时间
    private String max_call_time; //最近一次通话时间
    private Integer max_day_counts; //一天最多通话次数
    private Integer day_start_counts; //凌晨通话次数

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCallOtherNumber() {
        return callOtherNumber;
    }

    public void setCallOtherNumber(String callOtherNumber) {
        this.callOtherNumber = callOtherNumber;
    }

    public Integer getCallTime() {
        return callTime;
    }

    public void setCallTime(Integer callTime) {
        this.callTime = callTime;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getCalled() {
        return called;
    }

    public void setCalled(Integer called) {
        this.called = called;
    }

    public String getMin_call_time() {
        return min_call_time;
    }

    public void setMin_call_time(String min_call_time) {
        this.min_call_time = min_call_time;
    }

    public String getMax_call_time() {
        return max_call_time;
    }

    public void setMax_call_time(String max_call_time) {
        this.max_call_time = max_call_time;
    }

    public Integer getMax_day_counts() {
        return max_day_counts;
    }

    public void setMax_day_counts(Integer max_day_counts) {
        this.max_day_counts = max_day_counts;
    }

    public Integer getDay_start_counts() {
        return day_start_counts;
    }

    public void setDay_start_counts(Integer day_start_counts) {
        this.day_start_counts = day_start_counts;
    }
}
