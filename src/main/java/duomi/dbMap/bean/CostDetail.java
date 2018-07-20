package duomi.dbMap.bean;

/**
 * Created by DEllComputer on 2018/4/18.
 * 话费详情统计(近六个月,按月统计)
 */
public class CostDetail {
    private String totalMoney;  //月话费
    private String month;   //年月

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
