package duomi.com.httpIvk.helper.tianyanchaHelper;

import duomi.init.GlobalConfig;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 天眼查发送http请求工具类
 */
@Component
public class TianyanchaHttpHelper {
    private final static Logger logger = Logger.getLogger(TianyanchaHttpHelper.class);
    private final static String token = GlobalConfig.get("tyc_token");
    private final static String url = GlobalConfig.get("tyc_url");

    /**
     * 发送http GET请求
     * @param intfName 接口名称
     * @param params 请求参数
     * @return 返回报文
     */
    @PostConstruct
    public static String httpGetReq(String intfName,Map<String, String> params) throws Exception {
        String result = null;
        String apiUrl = url + intfName;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }

        try {
            URLEncoder.encode(param.toString(),"UTF-8");
            apiUrl += param;
            HttpClient httpclient = new DefaultHttpClient();

            HttpGet httpGet = new HttpGet(apiUrl);
            httpGet.setHeader("Authorization", token);
            HttpResponse response = httpclient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, "UTF-8");
            }
        } catch (Exception e) {
            logger.error("调用天眼查接口:" + apiUrl + "异常",e);
            throw new Exception("调用天眼查接口:" + apiUrl + "异常",e);
        }
        return result;
    }


}
