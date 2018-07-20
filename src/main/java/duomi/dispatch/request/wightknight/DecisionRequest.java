package duomi.dispatch.request.wightknight;

import duomi.dispatch.request.ComRequest;

/**
 * Created by DEllComputer on 2018/2/27.
 * 白骑士-风险决策请求参数
 */
public class DecisionRequest extends ComRequest {

    private String partnerId;  //必须，商户编号
    private String verifyKey;  //必须, 认证密钥
    private String appId;      //必须，应用编号
    private String eventType;  //必须, 事件类型
    private String tokenKey;   //当前设备指纹会话标识，用于事件中关 联设备指纹明细信息 如有用到设备相关规则时需上送
    private String zmOpenId;   //用户在商户端芝麻信用授权 ID 芝麻信用类规则服务需上送
    private String platform;   //应用平台类型，h5/web/ios/android
    private String returnRuleDetail; //是否需要返回命中规则明细数值
    private String account;    //用户账号
    private String name;       //用户姓名
    private String email;      //用户邮箱
    private String mobile;     //用户手机号(手机号若以 86 开头或+86 开头、中间 有‘-’或者空格，会被格式化成 11 位 标准手机号)
    private String certNo;     //用户身份证号(18 或 15 位身份证号中间若有空格， 会被去掉)
    private String address;    //用户住址
    private String addressCity; //用户所在城市
    private String contactsName;//用户联系人姓名
    private String contactsMobile; //用户联系人手机号
    private String contactsNameSec; //用户第二联系人姓名
    private String contactsMobileSec; //用户第二联系人手机号
    private String organization; //用户工作单位名称
    private String organizationAddress; //用户工作单位地址
    private String education;   //学历(文盲或半文盲/初中/高中/中专或 技校/大专/大学本科/研究生/博士)
    private String graduateSchool; //毕业院校名称
    private String graduateCity; //毕业院校城市
    private String marriage;     //是否已婚(未婚/已婚/离异/丧偶)
    private String deliverName;  //收货人
    private String deliverMobileNo; //收货人手机号
    private String deliverAddressStreet; //收货人街道地址信息
    private String deliverAddressCounty; //收货人县或区信息
    private String deliverAddressCity;  //收货人城市信息
    private String deliverAddressProvince; //收货人省份信息
    private String deliverAddressCountry; //收货人国家信息
    private double amount;       //金额(通用)
    private String bankCardNo;   //银行卡卡号
    private String bankCardName; //银行卡持卡人姓名
    private String bankCardMobile; //银行卡预留手机号
    private String creditCardNo;   //信用卡卡号
    private String creditCardName; //信用卡持卡人姓名
    private String creditCardMobile; //信用卡预留手机号
    private double longitude;  //经度
    private double latitude; //纬度

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getZmOpenId() {
        return zmOpenId;
    }

    public void setZmOpenId(String zmOpenId) {
        this.zmOpenId = zmOpenId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getReturnRuleDetail() {
        return returnRuleDetail;
    }

    public void setReturnRuleDetail(String returnRuleDetail) {
        this.returnRuleDetail = returnRuleDetail;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    @Override
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public String getContactsNameSec() {
        return contactsNameSec;
    }

    public void setContactsNameSec(String contactsNameSec) {
        this.contactsNameSec = contactsNameSec;
    }

    public String getContactsMobileSec() {
        return contactsMobileSec;
    }

    public void setContactsMobileSec(String contactsMobileSec) {
        this.contactsMobileSec = contactsMobileSec;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getGraduateCity() {
        return graduateCity;
    }

    public void setGraduateCity(String graduateCity) {
        this.graduateCity = graduateCity;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public String getDeliverMobileNo() {
        return deliverMobileNo;
    }

    public void setDeliverMobileNo(String deliverMobileNo) {
        this.deliverMobileNo = deliverMobileNo;
    }

    public String getDeliverAddressStreet() {
        return deliverAddressStreet;
    }

    public void setDeliverAddressStreet(String deliverAddressStreet) {
        this.deliverAddressStreet = deliverAddressStreet;
    }

    public String getDeliverAddressCounty() {
        return deliverAddressCounty;
    }

    public void setDeliverAddressCounty(String deliverAddressCounty) {
        this.deliverAddressCounty = deliverAddressCounty;
    }

    public String getDeliverAddressCity() {
        return deliverAddressCity;
    }

    public void setDeliverAddressCity(String deliverAddressCity) {
        this.deliverAddressCity = deliverAddressCity;
    }

    public String getDeliverAddressProvince() {
        return deliverAddressProvince;
    }

    public void setDeliverAddressProvince(String deliverAddressProvince) {
        this.deliverAddressProvince = deliverAddressProvince;
    }

    public String getDeliverAddressCountry() {
        return deliverAddressCountry;
    }

    public void setDeliverAddressCountry(String deliverAddressCountry) {
        this.deliverAddressCountry = deliverAddressCountry;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardName() {
        return bankCardName;
    }

    public void setBankCardName(String bankCardName) {
        this.bankCardName = bankCardName;
    }

    public String getBankCardMobile() {
        return bankCardMobile;
    }

    public void setBankCardMobile(String bankCardMobile) {
        this.bankCardMobile = bankCardMobile;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public String getCreditCardMobile() {
        return creditCardMobile;
    }

    public void setCreditCardMobile(String creditCardMobile) {
        this.creditCardMobile = creditCardMobile;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
