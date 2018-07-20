package duomi.com.httpIvk.jiguang.param;

/**
 * Created by DEllComputer on 2018/1/31.
 */
public class AntiFraudInput {
    public String name;   //姓名
    public String phone;  //手机号码
    public String id_number; //身份证号
    private String imei; //Android IMEI 非必须
    private String mac; //MAC地址 非必须
    private String imsi; //国际移动用户识别码 非必须

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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }
}
