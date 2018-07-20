package duomi.dispatch.request;

/**
 * Created by DEllComputer on 2018/5/9.
 */
public class PersonJudicialListRequest extends ComRequest{
    private String timestamp;  //时间戳
    private String name;    //真实姓名
    private String idCard;  //身份证号
    private String dataType;  //司法类型

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getIdCard() {
        return idCard;
    }

    @Override
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
