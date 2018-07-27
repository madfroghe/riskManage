package duomi.com.exception;

public class HttpBizException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String msg;
	
	public HttpBizException(String code,String msg)  
    {  
		super(msg);  
		this.setCode(code);
		this.setMsg(msg);
    } 
	
	public HttpBizException(String msg)  
    {  
        super(msg);
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	} 
	
	
}
