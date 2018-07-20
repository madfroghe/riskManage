package duomi.dbMap.bean;

/**
 * Created by DEllComputer on 2018/4/23.
 * 风险联系人明细
 */
public class RiskContactDetail {

    private String contact_number; //联系人号码
    private String contact_relation; //联系人关系
    private String contact_seq_no; //联系人排名
    private String contact_attribute; //号码属性
    private String contact_type; //号码分类
    private String contact_name; //号码标签
    private String contact_area; //号码归属地
    private String is_virtual_number; //号码是否小号
    private String call_count_1week; //近1周通话次数
    private String call_count_1month; //近1月通话次数
    private String call_count_3month; //近3月通话次数
    private String call_count_active_3month; //近3月主叫通话次数
    private String call_count_passive_3month; //近3月被叫通话次数
    private String call_count_late_night_3month; //近3月深夜通话次数
    private String call_count_work_time_3month; //近3月工作时间通话次数
    private String call_count_offwork_time_3month; //近3月非工作时间通话次数
    private String call_count_workday_3month; //近3月工作日通话次数
    private String call_count_holiday_3month; //近3月节假日通话次数
    private String call_count_6month; //近6月通话次数
    private String call_count_active_6month; //近6月主叫通话次数
    private String call_count_passive_6month; //近6月被叫通话次数
    private String call_count_late_night_6month; //近6月深夜通话次数
    private String call_count_work_time_6month; //近6月工作时间通话次数
    private String call_count_offwork_time_6month; //近6月非工作时间通话次数
    private String call_count_workday_6month; //近6月工作日通话次数
    private String call_count_holiday_6month; //近6月节假日通话次数
    private String call_time_1month; //近1月通话时长
    private String call_time_3month; //近3月通话时长
    private String call_time_active_3month; //近3月主叫通话时长
    private String call_time_passive_3month; //近3月被叫通话时长
    private String call_time_late_night_3month; //近3月深夜通话时长
    private String call_time_work_time_3month; //近3月工作时间通话时长
    private String call_time_offwork_time_3month; //近3月非工作时间通话时长
    private String call_time_6month; //近6月通话时长
    private String call_time_active_6month; //近6月主叫通话时长
    private String call_time_passive_6month; //近6月被叫通话时长
    private String call_time_late_night_6month; //近6月深夜通话时长
    private String call_time_work_time_6month; //近6月工作时间通话时长
    private String call_time_offwork_time_6month; //近6月非工作时间通话时长
    private String msg_count_1month; //近1月短信次数
    private String msg_count_3month; //近3月短信次数
    private String msg_count_6month; //近6月短信次数
    private String is_whole_day_call_3month; //近3个月是否有全天通话
    private String is_whole_day_call_6month; //近6个月是否有全天通话
    private String first_time_call_6month; //近6月第一次通话时间
    private String last_time_call_6month; //近6月最后一次通话时间
    private String max_gap_day_call_6month; //近6月最长通话间隔天数
    private String average_gap_day_call_6month; //近6月平均通话间隔天数

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getContact_relation() {
        return contact_relation;
    }

    public void setContact_relation(String contact_relation) {
        this.contact_relation = contact_relation;
    }

    public String getContact_seq_no() {
        return contact_seq_no;
    }

    public void setContact_seq_no(String contact_seq_no) {
        this.contact_seq_no = contact_seq_no;
    }

    public String getContact_attribute() {
        return contact_attribute;
    }

    public void setContact_attribute(String contact_attribute) {
        this.contact_attribute = contact_attribute;
    }

    public String getContact_type() {
        return contact_type;
    }

    public void setContact_type(String contact_type) {
        this.contact_type = contact_type;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_area() {
        return contact_area;
    }

    public void setContact_area(String contact_area) {
        this.contact_area = contact_area;
    }

    public String getIs_virtual_number() {
        return is_virtual_number;
    }

    public void setIs_virtual_number(String is_virtual_number) {
        this.is_virtual_number = is_virtual_number;
    }

    public String getCall_count_1week() {
        return call_count_1week;
    }

    public void setCall_count_1week(String call_count_1week) {
        this.call_count_1week = call_count_1week;
    }

    public String getCall_count_1month() {
        return call_count_1month;
    }

    public void setCall_count_1month(String call_count_1month) {
        this.call_count_1month = call_count_1month;
    }

    public String getCall_count_3month() {
        return call_count_3month;
    }

    public void setCall_count_3month(String call_count_3month) {
        this.call_count_3month = call_count_3month;
    }

    public String getCall_count_active_3month() {
        return call_count_active_3month;
    }

    public void setCall_count_active_3month(String call_count_active_3month) {
        this.call_count_active_3month = call_count_active_3month;
    }

    public String getCall_count_passive_3month() {
        return call_count_passive_3month;
    }

    public void setCall_count_passive_3month(String call_count_passive_3month) {
        this.call_count_passive_3month = call_count_passive_3month;
    }

    public String getCall_count_late_night_3month() {
        return call_count_late_night_3month;
    }

    public void setCall_count_late_night_3month(String call_count_late_night_3month) {
        this.call_count_late_night_3month = call_count_late_night_3month;
    }

    public String getCall_count_work_time_3month() {
        return call_count_work_time_3month;
    }

    public void setCall_count_work_time_3month(String call_count_work_time_3month) {
        this.call_count_work_time_3month = call_count_work_time_3month;
    }

    public String getCall_count_offwork_time_3month() {
        return call_count_offwork_time_3month;
    }

    public void setCall_count_offwork_time_3month(String call_count_offwork_time_3month) {
        this.call_count_offwork_time_3month = call_count_offwork_time_3month;
    }

    public String getCall_count_workday_3month() {
        return call_count_workday_3month;
    }

    public void setCall_count_workday_3month(String call_count_workday_3month) {
        this.call_count_workday_3month = call_count_workday_3month;
    }

    public String getCall_count_holiday_3month() {
        return call_count_holiday_3month;
    }

    public void setCall_count_holiday_3month(String call_count_holiday_3month) {
        this.call_count_holiday_3month = call_count_holiday_3month;
    }

    public String getCall_count_6month() {
        return call_count_6month;
    }

    public void setCall_count_6month(String call_count_6month) {
        this.call_count_6month = call_count_6month;
    }

    public String getCall_count_active_6month() {
        return call_count_active_6month;
    }

    public void setCall_count_active_6month(String call_count_active_6month) {
        this.call_count_active_6month = call_count_active_6month;
    }

    public String getCall_count_passive_6month() {
        return call_count_passive_6month;
    }

    public void setCall_count_passive_6month(String call_count_passive_6month) {
        this.call_count_passive_6month = call_count_passive_6month;
    }

    public String getCall_count_late_night_6month() {
        return call_count_late_night_6month;
    }

    public void setCall_count_late_night_6month(String call_count_late_night_6month) {
        this.call_count_late_night_6month = call_count_late_night_6month;
    }

    public String getCall_count_work_time_6month() {
        return call_count_work_time_6month;
    }

    public void setCall_count_work_time_6month(String call_count_work_time_6month) {
        this.call_count_work_time_6month = call_count_work_time_6month;
    }

    public String getCall_count_offwork_time_6month() {
        return call_count_offwork_time_6month;
    }

    public void setCall_count_offwork_time_6month(String call_count_offwork_time_6month) {
        this.call_count_offwork_time_6month = call_count_offwork_time_6month;
    }

    public String getCall_count_workday_6month() {
        return call_count_workday_6month;
    }

    public void setCall_count_workday_6month(String call_count_workday_6month) {
        this.call_count_workday_6month = call_count_workday_6month;
    }

    public String getCall_count_holiday_6month() {
        return call_count_holiday_6month;
    }

    public void setCall_count_holiday_6month(String call_count_holiday_6month) {
        this.call_count_holiday_6month = call_count_holiday_6month;
    }

    public String getCall_time_1month() {
        return call_time_1month;
    }

    public void setCall_time_1month(String call_time_1month) {
        this.call_time_1month = call_time_1month;
    }

    public String getCall_time_3month() {
        return call_time_3month;
    }

    public void setCall_time_3month(String call_time_3month) {
        this.call_time_3month = call_time_3month;
    }

    public String getCall_time_active_3month() {
        return call_time_active_3month;
    }

    public void setCall_time_active_3month(String call_time_active_3month) {
        this.call_time_active_3month = call_time_active_3month;
    }

    public String getCall_time_passive_3month() {
        return call_time_passive_3month;
    }

    public void setCall_time_passive_3month(String call_time_passive_3month) {
        this.call_time_passive_3month = call_time_passive_3month;
    }

    public String getCall_time_late_night_3month() {
        return call_time_late_night_3month;
    }

    public void setCall_time_late_night_3month(String call_time_late_night_3month) {
        this.call_time_late_night_3month = call_time_late_night_3month;
    }

    public String getCall_time_work_time_3month() {
        return call_time_work_time_3month;
    }

    public void setCall_time_work_time_3month(String call_time_work_time_3month) {
        this.call_time_work_time_3month = call_time_work_time_3month;
    }

    public String getCall_time_offwork_time_3month() {
        return call_time_offwork_time_3month;
    }

    public void setCall_time_offwork_time_3month(String call_time_offwork_time_3month) {
        this.call_time_offwork_time_3month = call_time_offwork_time_3month;
    }

    public String getCall_time_6month() {
        return call_time_6month;
    }

    public void setCall_time_6month(String call_time_6month) {
        this.call_time_6month = call_time_6month;
    }

    public String getCall_time_active_6month() {
        return call_time_active_6month;
    }

    public void setCall_time_active_6month(String call_time_active_6month) {
        this.call_time_active_6month = call_time_active_6month;
    }

    public String getCall_time_passive_6month() {
        return call_time_passive_6month;
    }

    public void setCall_time_passive_6month(String call_time_passive_6month) {
        this.call_time_passive_6month = call_time_passive_6month;
    }

    public String getCall_time_late_night_6month() {
        return call_time_late_night_6month;
    }

    public void setCall_time_late_night_6month(String call_time_late_night_6month) {
        this.call_time_late_night_6month = call_time_late_night_6month;
    }

    public String getCall_time_work_time_6month() {
        return call_time_work_time_6month;
    }

    public void setCall_time_work_time_6month(String call_time_work_time_6month) {
        this.call_time_work_time_6month = call_time_work_time_6month;
    }

    public String getCall_time_offwork_time_6month() {
        return call_time_offwork_time_6month;
    }

    public void setCall_time_offwork_time_6month(String call_time_offwork_time_6month) {
        this.call_time_offwork_time_6month = call_time_offwork_time_6month;
    }

    public String getMsg_count_1month() {
        return msg_count_1month;
    }

    public void setMsg_count_1month(String msg_count_1month) {
        this.msg_count_1month = msg_count_1month;
    }

    public String getMsg_count_3month() {
        return msg_count_3month;
    }

    public void setMsg_count_3month(String msg_count_3month) {
        this.msg_count_3month = msg_count_3month;
    }

    public String getMsg_count_6month() {
        return msg_count_6month;
    }

    public void setMsg_count_6month(String msg_count_6month) {
        this.msg_count_6month = msg_count_6month;
    }

    public String getIs_whole_day_call_3month() {
        return is_whole_day_call_3month;
    }

    public void setIs_whole_day_call_3month(String is_whole_day_call_3month) {
        this.is_whole_day_call_3month = is_whole_day_call_3month;
    }

    public String getIs_whole_day_call_6month() {
        return is_whole_day_call_6month;
    }

    public void setIs_whole_day_call_6month(String is_whole_day_call_6month) {
        this.is_whole_day_call_6month = is_whole_day_call_6month;
    }

    public String getFirst_time_call_6month() {
        return first_time_call_6month;
    }

    public void setFirst_time_call_6month(String first_time_call_6month) {
        this.first_time_call_6month = first_time_call_6month;
    }

    public String getLast_time_call_6month() {
        return last_time_call_6month;
    }

    public void setLast_time_call_6month(String last_time_call_6month) {
        this.last_time_call_6month = last_time_call_6month;
    }

    public String getMax_gap_day_call_6month() {
        return max_gap_day_call_6month;
    }

    public void setMax_gap_day_call_6month(String max_gap_day_call_6month) {
        this.max_gap_day_call_6month = max_gap_day_call_6month;
    }

    public String getAverage_gap_day_call_6month() {
        return average_gap_day_call_6month;
    }

    public void setAverage_gap_day_call_6month(String average_gap_day_call_6month) {
        this.average_gap_day_call_6month = average_gap_day_call_6month;
    }
}
