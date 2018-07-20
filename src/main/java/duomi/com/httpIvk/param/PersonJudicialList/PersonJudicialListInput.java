package duomi.com.httpIvk.param.PersonJudicialList;

import duomi.com.httpIvk.param.BaseRequest;

/**
 * Created by DEllComputer on 2018/5/9.
 */
public class PersonJudicialListInput extends BaseRequest {

    private String dataType;  //司法类型
    private String idCard;  //身份证号
    private String name;    //真实姓名
    private String timestamp;  //时间戳

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
