package httpIvk;

import java.util.List;

import org.apache.http.NameValuePair;

import duomi.com.httpIvk.helper.HttpUtil;
import duomi.com.utils.JSONUtils;
import duomi.dispatch.request.MobileCBNotifyData;
import duomi.dispatch.request.MobileCallBackRequest;
import duomi.dispatch.request.MobileDetailRequest;

public class HttpMoblieTest {
	public static void main(String[] args) {
		new HttpMoblieTest().mobileCallBack();
	}

	/**
	 * 开始
	 */
	public void startMobileTesk() {
		MobileDetailRequest po = new MobileDetailRequest();
		po.setMobile("13658032580");
		po.setName("刘并");
		po.setIdcard("110101198701011778");
		po.setMbpasswd("870526");

		List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

		// String url = "http://localhost:8080/riskManage/startMobileTesk";
		String url = "http://101.132.222.202:6780/riskManage/startMobileTesk";
		String retstr = HttpUtil.post(url, list);

		System.out.println("HttpClientTest.java:----" + retstr);

	}

	/**
	 * 开始
	 */
	public void sendMobileTesk() {
		MobileDetailRequest po = new MobileDetailRequest();
		po.setTask_id("TASKYYS100000201712201741450691520884");
		po.setShortMsgCode("518835");
		po.setNext_stage("QUERY");

		List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

		// String url = "http://localhost:8080/riskManage/sendMobileTesk";
		String url = "http://101.132.222.202:6780/riskManage/sendMobileTesk";
		String retstr = HttpUtil.post(url, list);

		System.out.println("HttpClientTest.java:----" + retstr);

	}

	/**
	 * 通话详单回调
	 */
	public void mobileCallBack() {
		MobileCallBackRequest po = new MobileCallBackRequest();
		MobileCBNotifyData nData = new MobileCBNotifyData();
		nData.setTask_id("TASKYYS100000201804131332130691521503");
		po.setNotify_data(JSONUtils.toJSONString(nData));
		String datastr = JSONUtils.toJSONString(po);

		System.out.println(datastr);
		// List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

		List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

		String url = "http://localhost:6780/riskManage/mobileCallBack";
		// String url = "http://101.132.222.202:6780/riskManage/mobileCallBack";
		String retstr = HttpUtil.post(url, list);

		System.out.println("HttpClientTest.java:----" + retstr);

	}

}
