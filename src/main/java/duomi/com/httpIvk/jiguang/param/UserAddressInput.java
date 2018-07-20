package duomi.com.httpIvk.jiguang.param;

/**
 * Created by DEllComputer on 2018/2/24.
 */
public class UserAddressInput {
    private String imei; //非必须 Android IMEI
    private String phone; //必须  手机号
    private String phone_md5; //必须 手机号码md5
    private String mac; //非必须 MAC地址
    private String imsi; //非必须 国际移动用户识别码
    private String name; //必须 用户姓名
    private String id_number; //必须 身份证号

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone_md5() {
        return phone_md5;
    }

    public void setPhone_md5(String phone_md5) {
        this.phone_md5 = phone_md5;
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
}
