package duomi.com.httpIvk.helper;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * Http工具类
 *
 * @author lvxh
 * @since V100R001C02
 * @version V100R001C02
 */
public abstract class HttpUtil {

	static final int timeOut = 60 * 1000;

	private static CloseableHttpClient httpClient = null;

	private final static Object syncLock = new Object();

	public static void config(HttpRequestBase httpRequestBase) {

		// 配置请求的超时设置
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut)
				.setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
		httpRequestBase.setConfig(requestConfig);
	}

	/**
	 * 获取HttpClient对象
	 * 
	 * @return
	 * @author SHANHY
	 * @create 2015年12月18日
	 */
	public static CloseableHttpClient getHttpClient() {
		if (httpClient == null) {
			synchronized (syncLock) {
				if (httpClient == null) {
					httpClient = createHttpClient(200, 40, 100);
				}
			}
		}
		return httpClient;
	}

	/**
	 * 创建HttpClient对象
	 * 
	 * @return
	 * @author SHANHY
	 * @create 2015年12月18日
	 */
	public static CloseableHttpClient createHttpClient(int maxTotal, int maxPerRoute, int maxRoute) {
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", plainsf).register("https", sslsf).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		// 将最大连接数添加
		cm.setMaxTotal(maxTotal);
		// 将每一个路由基础的连接添加
		cm.setDefaultMaxPerRoute(maxPerRoute);
		// HttpHost httpHost = new HttpHost(hostname, port);
		// 将目标主机的最大连接数添加
		cm.setDefaultMaxPerRoute(maxRoute);

		// 请求重试处理
		HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= 5) {// 假设已经重试了5次，就放弃
					return false;
				}
				if (exception instanceof NoHttpResponseException) {// 假设server丢掉了连接。那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
					return false;
				}
				if (exception instanceof InterruptedIOException) {// 超时
					return false;
				}
				if (exception instanceof UnknownHostException) {// 目标server不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
					return false;
				}
				if (exception instanceof SSLException) {// SSL握手异常
					return false;
				}

				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				// 假设请求是幂等的，就再次尝试
				if (!(request instanceof HttpEntityEnclosingRequest)) {
					return true;
				}
				return false;
			}
		};

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm)
				.setRetryHandler(httpRequestRetryHandler).build();

		return httpClient;
	}

	/**
	 * 用法 String url="http://192.168.2.181:8080/sic/app/login.action"; Map
	 * <String,String>map = new HashMap<>(); map.put("domainCode", "SA60");
	 * map.put("loginName", "root"); map.put("password", "rootroot");
	 * System.out.println(sendSms(url,map));
	 *
	 * @param url
	 *            String 请求地址
	 *
	 * @param list
	 *            键值对列表
	 *
	 * @return String 返回结果集
	 */
	public static String post(String url, List<NameValuePair> list) {

		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		String strResult = "";
		try {
			httpclient = getHttpClient();
			config(httpPost);
			httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");
			httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));

			response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				strResult = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
				// strResult =
				// duomi.com.utils.StringUtils.decodeUnicode(strResult);
			} else {
				strResult = String.valueOf(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				/*
				 * if (httpclient != null) { httpclient.close(); }
				 */
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strResult;
	}

	/**
	 * 用法 String url="http://192.168.2.181:8080/sic/app/login.action"; Map
	 * <String,String>map = new HashMap<>(); map.put("domainCode", "SA60");
	 * map.put("loginName", "root"); map.put("password", "rootroot");
	 * System.out.println(sendSms(url,map));
	 *
	 * @param url
	 *            String 请求地址
	 *
	 * @param list
	 *            键值对列表
	 *
	 * @return String 返回结果集
	 */
	public static String postJSon(String url, String jsonstr) {

		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		String strResult = "";
		try {
			httpclient = getHttpClient();
			config(httpPost);
			httpPost.addHeader("Content-type", "application/json; charset=utf-8");
			// httpPost.setEntity(new StringEntity(jsonstr,
			// Charset.forName("UTF-8")));

			response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				strResult = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
			} else {
				strResult = String.valueOf(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				/*
				 * if (httpclient != null) { httpclient.close(); }
				 */
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strResult;
	}

	/**
	 * .以JSON方式(application/json)发送HTTP请求，并实时返回相应消息
	 *
	 * @param url
	 *            请求URL
	 * @param data
	 *            请求数据
	 * @return 请求相应
	 */
	public static String sendJSONPostImmediate(String url, String data) {
		return sendPost(url, data.replaceAll("\\\\u003d", "="), "application/json", 1000);
	}

	/**
	 * .以JSON方式(application/json)发送HTTP请求，并返回相应消息
	 *
	 * @param url
	 *            请求URL
	 * @param data
	 *            请求数据
	 * @return 请求相应
	 */
	public static String sendJSONPost(String url, String data, int timeout) {
		return sendPost(url, data.replaceAll("\\\\u003d", "="), "application/json", timeout);
	}

	/**
	 * .以FORM方式(application/x-www-form-urlencoded)发送HTTP请求，并返回相应消息
	 *
	 * @param url
	 *            请求URL
	 * @param data
	 *            请求数据
	 * @return 请求相应
	 */
	public static String sendFormPost(String url, String data, int timeout) {
		return sendPost(url, data.replaceAll("\\\\u003d", "="), "application/x-www-form-urlencoded", timeout);
	}

	/**
	 * .发送HTTP请求，并返回相应消息
	 *
	 * @param url
	 *            请求URL
	 * @param data
	 *            请求数据
	 * @param contentType
	 *            请求类型
	 * @param timeout
	 *            请求超时设置
	 * @return 请求相应
	 */
	private static String sendPost(String url, String data, String contentType, int timeout) {
		StringBuffer sbf = new StringBuffer();
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(timeout);
			connection.setReadTimeout(timeout);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", contentType);
			connection.connect();

			IOUtils.write(data, connection.getOutputStream(), StandardCharsets.UTF_8);

			for (String line : IOUtils.readLines(connection.getInputStream(), StandardCharsets.UTF_8)) {
				sbf.append(line);
			}
		} catch (Exception e) {
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return sbf.toString();
	}

	/**
	 * 用法 String url="http://192.168.2.181:8080/sic/app/login.action"; Map
	 * <String,String>map = new HashMap<>(); map.put("domainCode", "SA60");
	 * map.put("loginName", "root"); map.put("password", "rootroot");
	 * System.out.println(sendSms(url,map));
	 *
	 * @param the
	 *            type of the desired object
	 *
	 * @param url
	 *            String 请求地址
	 *
	 * @param list
	 *            键值对列表
	 *
	 * @param classOfT
	 *            classOfT 转换类型
	 *
	 * @return Object 返回结果集
	 */
	public static <T> T getPostObj(String url, List<NameValuePair> list, Class<T> classOfT) {
		String jsonString = post(url, list);
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		return null;
		// return JsonUtil.getObject(jsonString, classOfT);
	}

	/**
	 * ����
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncode(String source, String encode) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "0";
		}
		return result;
	}

	public static String urlEncodeGBK(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "0";
		}
		return result;
	}
}