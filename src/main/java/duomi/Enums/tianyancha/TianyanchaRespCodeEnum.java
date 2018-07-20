package duomi.Enums.tianyancha;

import java.util.HashMap;
import java.util.Map;

/**
 * 天眼查返回码值枚举
 */
public enum TianyanchaRespCodeEnum {
    SUCC("0","请求成功"),
    NO_DATA("300000","无数据"),
    REQ_FAIL("300001","请求失败"),
    ACC_INVALID("300002","账号失效"),
    ACC_EXPIRE("300003","账号过期"),
    REQ_TOO_FAST("300004","访问频率过快"),
    NO_PERMISSION("300005","没有访问权限"),
    NO_MONEY("300006","余额不足"),
    NO_COUNT("300007","剩余次数不足"),
    LACK_PARAM("300008","缺少必要参数"),
    ACC_FAULT("300009","账号信息有误"),
    URL_NOT_EXIST("300010","URL不存在");

    private static Map<String,String> map = new HashMap<>();
    //初始化将枚举的code和desc对应关系存入map中
    static{
        for (TianyanchaRespCodeEnum respCodeEnum : TianyanchaRespCodeEnum.values()){
            map.put(respCodeEnum.getCode(),respCodeEnum.getDesc());
        }
    }

    //码值
    private String code;
    //码值描述
    private String desc;

    TianyanchaRespCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过返回码值获取码值的描述
     * @param code 码值
     * @return
     */
    public static String getCodeDesc(String code){
        return map.get(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
