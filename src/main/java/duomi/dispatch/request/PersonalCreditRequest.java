package duomi.dispatch.request;

/**
 * Created by DEllComputer on 2018/5/9.
 */
public class PersonalCreditRequest extends ComRequest {
    private String name;
    private String idCard;

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
}
