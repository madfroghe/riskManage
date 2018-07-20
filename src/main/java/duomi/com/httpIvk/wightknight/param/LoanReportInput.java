package duomi.com.httpIvk.wightknight.param;

import duomi.dispatch.request.wightknight.ExtParam;

/**
 * Created by DEllComputer on 2018/2/28.
 */
public class LoanReportInput {

    private String partnerId;  //必须 第三方用户唯一凭证，白骑士分配
    private String verifyKey;  //必须 验证码，白骑士分配
    private String productId;  //必须 107001
    private ExtParam extParam; //业务参数

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey;
    }

    public ExtParam getExtParam() {
        return extParam;
    }

    public void setExtParam(ExtParam extParam) {
        this.extParam = extParam;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
