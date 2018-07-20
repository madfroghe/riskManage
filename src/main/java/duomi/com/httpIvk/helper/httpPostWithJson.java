package duomi.com.httpIvk.helper;


import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import java.util.UUID;
import net.sf.json.JSONObject;
import java.nio.charset.Charset;

public class httpPostWithJson {
	public static boolean httpPostWithJson(JSONObject jsonObj,String url,String appId){
	    boolean isSuccess = false;
	    
	    HttpPost post = null;
	    try {
	        HttpClient httpClient = new DefaultHttpClient();

	        // ���ó�ʱʱ��
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
	            
	        post = new HttpPost(url);
	        // ������Ϣͷ
	        post.setHeader("Content-type", "application/json; charset=utf-8");
	        post.setHeader("Connection", "Close");
	        String sessionId = getSessionId();
	        post.setHeader("SessionId", sessionId);
	        //post.setHeader("appid", appid);
	                    
	        // ������Ϣʵ��
	        StringEntity entity = new StringEntity(jsonObj.toString(), Charset.forName("UTF-8"));
	        entity.setContentEncoding("UTF-8");
	        // ����Json��ʽ���������
	        entity.setContentType("application/json");
	        post.setEntity(entity);
	            
	        HttpResponse response = httpClient.execute(post);
	            
	        // ���鷵����
	        int statusCode = response.getStatusLine().getStatusCode();
	        if(statusCode != HttpStatus.SC_OK){
	            //LogUtil.info("�������: "+statusCode);
	            isSuccess = false;
	        }else{
	            int retCode = 0;
	            String sessendId = "";
	            // �������а�retCode���ỰId
	            for(Header header : response.getAllHeaders()){
	                if(header.getName().equals("retcode")){
	                    retCode = Integer.parseInt(header.getValue());
	                }
	                if(header.getName().equals("SessionId")){
	                    sessendId = header.getValue();
	                }
	            }
	            
	            /*if(ErrorCodeHelper.IAS_SUCCESS != retCode ){
	                // ��־��ӡ
	                //LogUtil.info("error return code,  sessionId: "sessendId"\t"+"retCode: "+retCode);
	                isSuccess = false;
	            }else{
	                isSuccess = true;
	            }*/
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        isSuccess = false;
	    }finally{
	        if(post != null){
	            try {
	                post.releaseConnection();
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return isSuccess;
	}

	// ����Ψһ�ỰId
	public static String getSessionId(){
	    UUID uuid = UUID.randomUUID();
	    String str = uuid.toString();
	    return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
	}
}
