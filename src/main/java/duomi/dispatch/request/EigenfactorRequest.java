package duomi.dispatch.request;

/**
 * Created by DEllComputer on 2018/1/30.
 */
public class EigenfactorRequest extends ComRequest {
    private String name;   //姓名
    private String phone;  //手机号码
    private String id_number; //身份证号
    private String imei; //Android IMEI 非必须
    private String mac; //MAC地址 非必须
    private String imsi; //国际移动用户识别码 非必须

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
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
