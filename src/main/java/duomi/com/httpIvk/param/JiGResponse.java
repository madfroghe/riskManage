package duomi.com.httpIvk.param;

/**
 * Created by DEllComputer on 2018/1/30.
 */
public class JiGResponse<T> {
    public String code; //极光返回的业务码
    public String message; //对业务码的辅助提示信息
    public String req_id;  //极光生成的全局唯一请求ID
    public T data;  //返回信息

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReq_id() {
        return req_id;
    }

    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
