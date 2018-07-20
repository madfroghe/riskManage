package duomi.dbMap.bean;

/**
 * Created by DEllComputer on 2018/4/17.
 * 运动轨迹详情（已处理,将传递给互金平台）
 */
public class MoveDetailHasEndTime {
    private String cityStartTime;
    private String cityAddress;
    private String cityEndTime;

    public String getCityStartTime() {
        return cityStartTime;
    }

    public void setCityStartTime(String cityStartTime) {
        this.cityStartTime = cityStartTime;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public String getCityEndTime() {
        return cityEndTime;
    }

    public void setCityEndTime(String cityEndTime) {
        this.cityEndTime = cityEndTime;
    }
}
