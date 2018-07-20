package httpIvk;

import java.util.List;

import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import duomi.com.constants.HouseRspConstants;
import duomi.com.constants.PubConstants;
import duomi.com.httpIvk.helper.HttpIvkHelper;
import duomi.com.httpIvk.helper.HttpUtil;
import duomi.com.httpIvk.house.param.HouseBaseResponse;
import duomi.com.httpIvk.house.param.HouseToken;
import duomi.com.httpIvk.house.param.HouseUseReq;
import duomi.com.httpIvk.param.creditGuard.CreditGuardInput;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.JSONUtils;

public class TestHttp {
	private final static Logger logger = LoggerFactory.getLogger(HttpIvkHelper.class);

	public static void main(String[] args) {
		new TestHttp().testTongDun();
	}

	public static void testTongDun() {
		String url_CreditGuard = "/bodyguard/apply/v4?";
		String url_param = "partner_code=duomian&partner_key=66dc89cbbb4846b28c6b030ae446ae01&app_name=fjd_web";
		String apiUrl = PubConstants.TONGDUNDATA_URL + url_CreditGuard + url_param;

		CreditGuardInput creditGuard = new CreditGuardInput();
		creditGuard.setAccount_mobile("13333333333");
		creditGuard.setAccount_name("皮晴晴");
		creditGuard.setId_number("370404199006301915");

		List<NameValuePair> list = JSONUtils.toNameValuePairList(creditGuard);
		String reqstr = JSONUtils.toJSONString(creditGuard);
		logger.info("同盾请求报文：" + reqstr);
		String retJson = HttpUtil.post(apiUrl, list);
		System.out.println(retJson);

	}

	public void testHose() {

		String apiUrl = "http://open.fangjia.com/accessToken";
		HouseUseReq houseUser = new HouseUseReq();

		List<NameValuePair> list = JSONUtils.toNameValuePairList(houseUser);
		String reqstr = JSONUtils.toJSONString(houseUser);
		logger.info("房价网授权请求报文：" + reqstr);

		String retJson = HttpUtil.post(apiUrl, list);

		logger.info("房价网授权返回报文：" + retJson);

		GsonUtils<HouseToken> gson = new GsonUtils<HouseToken>();
		HouseBaseResponse<HouseToken> output = (HouseBaseResponse<HouseToken>) gson.fromJson(retJson,
				HouseBaseResponse.class, HouseToken.class);
		String token = null;

		if (HouseRspConstants.CODE_SUCCESS.equals(output.getCode())) {
			token = output.getResult().getToken();
		} else {
			System.out.println("房价网授权交易失败");
		}

		System.out.println(token);
	}
}
