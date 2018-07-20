package duomi.Enums.tianyancha;

import java.util.Map;

/**
 * 天眼查企业信息查询接口枚举
 */
public enum TianyanchaBizEnum {

    TYC_BASE_INFO("TYC000001","天眼查-企业基本信息（含联系方式）","09",false,"/v4/open/baseinfoV2"),
    TYC_LAW_SUIT("TYC000002","天眼查-法律诉讼","09",true,"/v4/open/lawSuit"),
    TYC_COURT_ANNOUNCEMENT("TYC000003","天眼查-法院公告","09",true,"/v4/open/courtAnnouncement"),
    TYC_DISHONEST("TYC000004","天眼查-失信人","09",true,"/v4/open/dishonest"),
    TYC_EXEC_INFO("TYC000005","天眼查-被执行人","09",true,"/v4/open/zhixinginfo"),
    TYC_KT_ANNOUNCEMENT("TYC000006","天眼查-开庭公告","09",true,"/v4/open/ktannouncement"),
    TYC_ABNORMAL("TYC000007","天眼查-经营异常","09",true,"/v4/open/abnormal"),
    TYC_PUNISHMENT_INFO("TYC000008","天眼查-行政处罚","09",true,"/v4/open/punishmentInfo"),
    TYC_ILLEGAL_INFO("TYC000009","天眼查-严重违法","09",true,"/v4/open/illegalinfo"),
    TYC_EQUITY_INFO("TYC000010","天眼查-股权出质","09",true,"/v4/open/equityInfo"),
    TYC_MORTGAGE_INFO("TYC000011","天眼查-动产抵押","09",true,"/v4/open/mortgageInfo"),
    TYC_JUDICIAL_SALE("TYC000012","天眼查-司法拍卖","09",true,"/v4/open/judicialSale"),
    TYC_RELATION_MAP("TYC000013","天眼查-关联图谱","09",false,"/v3/open/oneKey/c");

    //接口编号
    private String code;
    //接口名称
    private String name;
    //接口类型
    private String type;
    //是否分页
    private boolean isPage;
    //接口名称
    private String intfName;
    //请求接口所需的参数
    private Map<String,String> params;

    TianyanchaBizEnum(String code, String name, String type, boolean isPage, String intfName) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.isPage = isPage;
        this.intfName = intfName;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPage() {
        return isPage;
    }

    public void setPage(boolean page) {
        isPage = page;
    }

    public String getIntfName() {
        return intfName;
    }

    public void setIntfName(String intfName) {
        this.intfName = intfName;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
