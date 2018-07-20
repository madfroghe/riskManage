package duomi.dispatch.request;

/**
 * Created by DEllComputer on 2018/5/16.
 */
public class BlacklistcheckRequest extends ComRequest {

    private String mobile;

    @Override
    public String getMobile() {
        return mobile;
    }

    @Override
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
