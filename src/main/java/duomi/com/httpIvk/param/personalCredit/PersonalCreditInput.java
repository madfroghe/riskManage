package duomi.com.httpIvk.param.personalCredit;

import duomi.com.httpIvk.param.BaseRequest;

/**
 * Created by DEllComputer on 2018/5/9.
 */
public class PersonalCreditInput extends BaseRequest {
    private String name; // 接口参数：真实姓名
    private String idCard; // 接口参数：身份证号

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
