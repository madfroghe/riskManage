package duomi.com.httpIvk.helper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author hanchaoyong
 */
public class HttpTool {

	private static PoolingHttpClientConnectionManager connMgr;
	private static RequestConfig requestConfig;
	private static final int MAX_TIMEOUT = 2000000000;// 7000
	// private static final String APPLICATION_JSON = "application/json";
	private static final String APPLICATION_JSON = "application/json";

	private static final String CONTENT_ENCODING = "UTF-8";

	static {
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connMgr.setMaxTotal(100);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时时间，单位毫秒。
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		// 请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
		requestConfig = configBuilder.build();
	}

	/**
	 * 发送 GET 请求（HTTP），不带输入数据
	 *
	 * @param url
	 *            请求地址
	 * @return 请求返回内容
	 */
	/*
	 * public static String doGet(String url) { return doGet(url, new
	 * HashMap<>()); }
	 */

	/**
	 * 发送 GET 请求（HTTP），K-V形式
	 *
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return 请求返回内容
	 */
	public static String doGet(String url, Map<String, Object> params) {
		String apiUrl = url;
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
		apiUrl += param;
		String result = null;
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpPost = new HttpGet(apiUrl);
			HttpResponse response = httpclient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = IOUtils.toString(instream, "UTF-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 发送 POST 请求（HTTP），不带输入数据
	 *
	 * @param apiUrl
	 * @return
	 */
	/*
	 * public static String doPost(String apiUrl) { return doPost(apiUrl, new
	 * HashMap<>()); }
	 */

	/**
	 * 发送 POST 请求（HTTP），K-V形式
	 *
	 * @param apiUrl
	 *            API接口URL
	 * @param params
	 *            参数map
	 * @return 请求返回内容
	 */
	public static String doPost(String apiUrl, Map<String, Object> params) {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;

		try {
			httpPost.setConfig(requestConfig);
			List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(CONTENT_ENCODING)));
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, CONTENT_ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	/**
	 * 发送 POST 请求（HTTP），JSON形式
	 *
	 * @param apiUrl
	 *            API接口URL
	 * @param json
	 *            json对象
	 * @return 请求返回内容
	 */
	public static String doPost(String apiUrl, String json) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);

		CloseableHttpResponse response = null;

		try {
			httpPost.setConfig(requestConfig);
			StringEntity stringEntity = new StringEntity(json, CONTENT_ENCODING);// 解决中文乱码问题
			stringEntity.setContentEncoding(CONTENT_ENCODING);
			stringEntity.setContentType(APPLICATION_JSON);
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, CONTENT_ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
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
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		String strResult = "";
		try {
			httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");
			httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));

			HttpResponse response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				strResult = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
			} else {
				strResult = String.valueOf(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strResult;
	}

	/**
	 * 发送 SSL POST 请求（HTTPS），K-V形式
	 *
	 * @param apiUrl
	 *            API接口URL
	 * @param params
	 *            参数map
	 * @return 请求返回内容
	 */
	/*
	 * public static String doPostSSL(String apiUrl, Map<String, Object> params)
	 * { CloseableHttpClient httpClient =
	 * HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).
	 * setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).
	 * build(); HttpPost httpPost = new HttpPost(apiUrl); CloseableHttpResponse
	 * response = null; String httpStr = null;
	 * 
	 * try { httpPost.setConfig(requestConfig); List<NameValuePair> pairList =
	 * new ArrayList<NameValuePair>(params.size()); for (Map.Entry<String,
	 * Object> entry : params.entrySet()) { NameValuePair pair = new
	 * BasicNameValuePair(entry.getKey(), entry .getValue().toString());
	 * pairList.add(pair); } httpPost.setEntity(new
	 * UrlEncodedFormEntity(pairList, Charset.forName(CONTENT_ENCODING)));
	 * response = httpClient.execute(httpPost); int statusCode =
	 * response.getStatusLine().getStatusCode(); if (statusCode !=
	 * HttpStatus.SC_OK) { return null; } HttpEntity entity =
	 * response.getEntity(); if (entity == null) { return null; } httpStr =
	 * EntityUtils.toString(entity, CONTENT_ENCODING); } catch (Exception e) {
	 * e.printStackTrace(); } finally { if (response != null) { try {
	 * EntityUtils.consume(response.getEntity()); } catch (IOException e) {
	 * e.printStackTrace(); } } } return httpStr; }
	 */

	/**
	 * 发送 SSL POST 请求（HTTPS），JSON形式
	 *
	 * @param apiUrl
	 *            API接口URL
	 * @param json
	 *            JSON对象
	 * @return 请求返回内容
	 */
	/*
	 * public static String doPostSSL(String apiUrl, Object json) {
	 * CloseableHttpClient httpClient =
	 * HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).
	 * setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).
	 * build(); HttpPost httpPost = new HttpPost(apiUrl); CloseableHttpResponse
	 * response = null; String httpStr = null;
	 * 
	 * try { httpPost.setConfig(requestConfig); StringEntity stringEntity = new
	 * StringEntity(json.toString(), CONTENT_ENCODING);//解决中文乱码问题
	 * stringEntity.setContentEncoding(CONTENT_ENCODING);
	 * stringEntity.setContentType("application/json");
	 * httpPost.setEntity(stringEntity); response =
	 * httpClient.execute(httpPost); int statusCode =
	 * response.getStatusLine().getStatusCode(); if (statusCode !=
	 * HttpStatus.SC_OK) { return null; } HttpEntity entity =
	 * response.getEntity(); if (entity == null) { return null; } httpStr =
	 * EntityUtils.toString(entity, CONTENT_ENCODING); } catch (Exception e) {
	 * e.printStackTrace(); } finally { if (response != null) { try {
	 * EntityUtils.consume(response.getEntity()); } catch (IOException e) {
	 * e.printStackTrace(); } } } return httpStr; }
	 */

	/**
	 * 创建SSL安全连接
	 *
	 * @return
	 */
	/*
	 * private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
	 * SSLConnectionSocketFactory sslsf = null; try { SSLContext sslContext =
	 * new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
	 * 
	 * public boolean isTrusted(X509Certificate[] chain, String authType) throws
	 * CertificateException { return true; } }).build(); sslsf = new
	 * SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
	 * 
	 * @Override public boolean verify(String arg0, SSLSession arg1) { return
	 * true; }
	 * 
	 * @Override public void verify(String host, SSLSocket ssl) throws
	 * IOException { }
	 * 
	 * @Override public void verify(String host, X509Certificate cert) throws
	 * SSLException { }
	 * 
	 * @Override public void verify(String host, String[] cns, String[]
	 * subjectAlts) throws SSLException { } }); } catch
	 * (GeneralSecurityException e) { e.printStackTrace(); } return sslsf; }
	 */
}
