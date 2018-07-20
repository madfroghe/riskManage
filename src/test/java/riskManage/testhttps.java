package riskManage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import httpIvk.HttpTools;

public class testhttps {

	public static void main(String[] args) {
		new testhttps().testhttps();
	}

	private void testhttps() {
		String ss = "{'bussSeqNo':'201711111127154664','channelId':'AG001','partnerId':'EJ001','reqData':{'mobileNo':'18011512324','userId':'F_bsrze1K8Bl9CLLWoQ','userName':'薛曾毅'},'reqTime':'20171111112715','sysSeqNo':'QeB_azulI8U0Md7TTpKdTap0tqXo'}";
		for (int i = 0; i < 5; i++) {

			JSONObject obj = (JSONObject) JSON.parse(ss);
			obj.put("bussSeqNo", i);

			String map = "";
			try {
				map = HttpTools.postJson("https://47.100.78.85/api/cust/custStatus?" + i, JSON.toJSONString(obj), 20000,
						20000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(map);
		}

	}
}
