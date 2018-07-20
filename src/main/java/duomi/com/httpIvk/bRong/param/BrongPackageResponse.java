package duomi.com.httpIvk.bRong.param;

/**
 * Created by DEllComputer on 2018/3/26.
 * 百融打包调用Response
 */
public class BrongPackageResponse<T> {

    private String code; //返回码
    private String swift_number; //流水号
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSwift_number() {
        return swift_number;
    }

    public void setSwift_number(String swift_number) {
        this.swift_number = swift_number;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
