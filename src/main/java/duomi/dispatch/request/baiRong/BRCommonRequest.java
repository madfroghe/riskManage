package duomi.dispatch.request.baiRong;

import duomi.dispatch.request.ComRequest;

import java.util.HashMap;
import java.util.Map;

public class BRCommonRequest extends ComRequest {

    private String productApi;
    private String bizFullName;
    private String keyNo; //公司关联主键
    private Integer page; //页码

    private String creditCode; //企业统一信用代码（注册号）
    private String legalPerson; //法人姓名
    private String personId; //法人身份证

    public String getProductApi() {
        return productApi;
    }

    public void setProductApi(String productApi) {
        this.productApi = productApi;
    }

    public String getBizFullName() {
        return bizFullName;
    }

    public void setBizFullName(String bizFullName) {
        this.bizFullName = bizFullName;
    }

    public enum ProductApiEnum{
        BIZ_DETAIL("BizDetail","企业工商详情","BR0000004"),
        BIZ_COMPANY_MAP("BizCompanyMap","企业图谱","BR0000005"),
        BIZ_RELATIONSHIP("BizRelationship","企业疑似关系","BR0000008"),
        BIZ_BLACK_LIST("BizBlackList","企业黑名单","BR0000006"),
        BIZ_KEY_SEARCH("BizKeySearch","企业关键字模糊查询","BR0000007"),
        BIZ_EXECUTOR("BizExecutor","企业被执行人信息","BR0000009"),
        BIZ_UNCREDIT_EXECUTOR("BizUncreditExecutor","企业失信被执行人信息","BR00000010"),
        BIZ_COURT_ANNOUNCEMENT("BizCourtAnnouncement","企业法院公告","BR00000011"),
        BIZ_FOUR("BizFour","企业四要素认证","BR00000012");
        private String key;
        private String name;
        private String innerNo;
        //innerNo转key
        private static Map<String,String> innerNoToKeyMap = new HashMap<>();
        //初始化填冲值
        static{
            for (ProductApiEnum apiEnum : ProductApiEnum.values()) {
                innerNoToKeyMap.put(apiEnum.getInnerNo(),apiEnum.getKey());
            }
            
        }
        ProductApiEnum(String key, String name, String innerNo) {
            this.key = key;
            this.name = name;
            this.innerNo = innerNo;
        }
        /**
         * 通过innerNo获取key值
         * @param innerNo
         * @return
         */
        public static String getKeyByInnerNo(String innerNo){
            return innerNoToKeyMap.get(innerNo);
        }

        public String getInnerNo() {
            return innerNo;
        }
        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getKeyNo() {
        return keyNo;
    }

    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
