package duomi.com.httpIvk.jiguang.param;

/**
 * Created by DEllComputer on 2018/2/24.
 */
public class UserTagInput {

    private String imei; //非必须 Android IMEI
    private String phone; //必须 手机号码
    private String phone_md5; //必须 手机号码(MD5加密,小写)
    private String mac; //非必须 MAC地址
    private String imsi; //非必须 国际移动用户识别码
    private String tag; //非必须 需要查询的标签
    private String name; //必须 用户姓名
    private String id_number; //必须 身份证号

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public String getPhone_md5() {
        return phone_md5;
    }

    public void setPhone_md5(String phone_md5) {
        this.phone_md5 = phone_md5;
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
}
