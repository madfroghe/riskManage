package mobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Created by yubo.xuan on 17/5/12.
 */
public class HttpUtils {
	private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000)
			.setConnectionRequestTimeout(15000).build();

	public static String executeHttpPost(String address, String queryParam, Map<String, String> headers, String body) {
		HttpPost postReq = new HttpPost(address + queryParam);

		// set headers
		if (null != headers) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			for (String key : headers.keySet()) {
				nameValuePairs.add(new BasicNameValuePair(key, headers.get(key)));
			}
		}

		// set body
		StringEntity bodyEntity = new StringEntity(body, "UTF-8");
		bodyEntity.setContentType("application/x-www-form-urlencoded");
		postReq.setEntity(bodyEntity);

		String result = sendHttpPost(postReq);

		return result;
	}

	private static String sendHttpPost(HttpPost httpPost) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
}
