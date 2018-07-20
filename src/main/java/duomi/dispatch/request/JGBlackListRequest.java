package duomi.dispatch.request;

/**
 * Created by DEllComputer on 2018/2/2.
 */
public class JGBlackListRequest extends ComRequest {
    private String name;    //必须，姓名；
    private String id_number;  //必须，身份证号；
    private String phone; //必须，手机号；
    private String imei;  //非必须，移动设备的唯一标识；
    private String mac;   //非必须, 移动设备的唯一标识；


    @Override
    public String getName() {
        return name;
    }

    @Override
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
}
