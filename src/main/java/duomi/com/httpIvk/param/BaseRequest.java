package duomi.com.httpIvk.param;



import java.security.DigestException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.com.utils.JSONUtils;
import duomi.com.utils.Sha1;


public class BaseRequest {
	private final static Logger logger = LoggerFactory.getLogger(BaseRequest.class);
	
	private static final String appidVal=PubConstants.ZBADATA_APPIDVAL;
	private static final String keyVal=PubConstants.ZBADATA_KEYVAL;
	public String app_id;
	public String sign;
	
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public BaseRequest init(BaseRequest baseReq) throws DigestException{
		baseReq.setApp_id(appidVal);
		Map<String,Object> reqMap=JSONUtils.toHashMap(baseReq);
		String tmp=Sha1.getOrderByLexicographic(reqMap);
		String decrypt=tmp+keyVal;
		logger.debug("sha1加密前:"+decrypt);
		String sign=Sha1.getSha1(decrypt);
		logger.debug("sha1加密后:"+sign);
		baseReq.setSign(sign);
		return baseReq;
	}
	
}
