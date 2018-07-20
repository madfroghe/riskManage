package duomi.com.httpIvk.jiguang.param;

import duomi.dispatch.request.jiguanglbsbean.Address;
import duomi.dispatch.request.jiguanglbsbean.Coordinate;

/**
 * Created by DEllComputer on 2018/2/23.
 */
public class LBSCheckInput {
    private String name;    // 姓名  必须
    private String id_number; // 身份证号 必须
    private String phone;  // 手机号码 必须
    private String imei; //移动设备唯一标识 非必须
    private String imsi; //全球移动用户标识 非必须
    private String mac; //硬件地址 非必须
    private Address address; //地址 必须
    private Integer distance_range; //距离阀值(米) 有条件必输(当coordinate参数不为空时，其lng和lat两个参数必须有值，并且distance_range也必须有值，不然会报错)
    private Coordinate coordinate; //地理坐标 非必须

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getDistance_range() {
        return distance_range;
    }

    public void setDistance_range(Integer distance_range) {
        this.distance_range = distance_range;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
