package duomi.com.httpIvk.helper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import com.bfd.facade.MerchantServer;
import com.bfd.util.MD5Utils;
import duomi.com.httpIvk.helper.yjCloudHelper.DesUtil;
import duomi.com.httpIvk.helper.yjCloudHelper.WJHttpUtil;
import duomi.com.httpIvk.param.JiGResponse;
import duomi.com.httpIvk.services.BRongBizHttpServiceImpl;
import duomi.com.httpIvk.wightknight.param.WightKnightResponse;
import duomi.com.httpIvk.yiJianCloud.param.YiJianCloudResponse;
import duomi.dispatch.request.baiRong.BRCommonRequest;
import net.sf.json.JSON;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import duomi.com.constants.PubConstants;
import duomi.com.exception.HttpBizException;
import duomi.com.httpIvk.GenData.GenTestData;
import duomi.com.httpIvk.param.BaseRequest;
import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.utils.GsonUtils;
import duomi.com.utils.JSONUtils;
import duomi.dispatch.request.ComRequest;
import duomi.services.OutsideServiceRegistService;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

public class HttpIvkHelper<E> {

	private final static Logger logger = LoggerFactory.getLogger(HttpIvkHelper.class);

	private static final String url = PubConstants.ZBADATA_URL;


	private OutsideServiceRegistService regitSrv;

	public BaseResponse<E> postData(String url, ComRequest comReq, BaseRequest baseReq, Class<?> class1)
			throws Exception {
		BaseResponse<E> output = null;

		// 更新登记表状态为发送，
		regitSrv.updateCspStusBefore(comReq);
		String appno = comReq.getAppNo();
		String interName = comReq.getInterName();
		String retstr = "";
		if (PubConstants.ZBADATA_SWITCH_ON.equals(PubConstants.ZBADATA_SWITCH)) {
			try {
				baseReq.init(baseReq);
			} catch (Exception e) {
				e.printStackTrace();
			}

			List<NameValuePair> list = JSONUtils.toNameValuePairList(baseReq);
			String reqstr = JSONUtils.toJSONString(baseReq);

			logger.info("---------请求消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",请求消息：" + reqstr);

			// String retstr = HttpTool.post(url, list);
			retstr = HttpUtil.post(url, list);

			// 个人贷款逾期特殊处理
			/*
			 * if ("duomi.com.httpIvk.param.overdue.LoanOverdueResult".equals(
			 * class1.getName())) { retstr =
			 * retstr.replaceAll(",\"list\":\"\",", ",\"list\":null,"); }
			 */

			logger.info("---------返回消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",返回消息：" + retstr + "\n,请求消息：" + reqstr);

			GsonUtils<E> gson = new GsonUtils<E>();
			output = (BaseResponse<E>) gson.fromJson(retstr, BaseResponse.class, class1);
			// retstr = duomi.com.utils.StringUtils.decodeUnicode(retstr);
			if (!output.isSuccess()) {
				// 更新接口状态为99
				regitSrv.updateCspStus4Error(comReq, retstr);
				logger.error("接口：" + interName + "审批工单编号：" + appno + ",errorDesc:" + output.getErrorDesc() + "\n,请求消息："
						+ reqstr);
				throw new HttpBizException(output.getCode(), output.getErrorDesc());
			}
		} else {
			GenTestData gtd = new GenTestData();
			output = gtd.genData4test(class1);
		}
		// 更新登记表状态为收到服务
		regitSrv.updateCspStusAfter(comReq, retstr);

		return output;
	}

	/**
	 * 同盾接口调用
	 *
	 * @param url
	 * @param comReq
	 * @param req
	 * @param class1
	 * @return
	 * @throws Exception
	 */
	public BaseResponse<E> postData4TD(String url, ComRequest comReq, Object req, Class<?> class1) throws Exception {
		BaseResponse<E> output = null;

		// 更新登记表状态为发送，
		regitSrv.updateCspStusBefore(comReq);
		String appno = comReq.getAppNo();
		String interName = comReq.getInterName();
		String reqstr = "";
		String retstr = "";
		if (PubConstants.SWITCH_ON.equals(PubConstants.TONGDUDATA_SWITCH)) {

			List<NameValuePair> list = JSONUtils.toNameValuePairList(req);
			reqstr = JSONUtils.toJSONString(req);

			logger.info("---------请求消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",请求消息：" + reqstr);

			// String retstr = HttpTool.post(url, list);
			retstr = HttpUtil.post(url, list);

			// 个人贷款逾期特殊处理
			if ("duomi.com.httpIvk.param.overdue.LoanOverdueResult".equals(class1.getName())) {
				retstr = retstr.replaceAll(",\"list\":\"\",", ",\"list\":null,");
			}

			logger.info("---------返回消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",返回消息：" + retstr + "\n,请求报文:" + reqstr);

			GsonUtils<E> gson = new GsonUtils<E>();
			output = (BaseResponse<E>) gson.fromJson(retstr, BaseResponse.class, class1);

			// retstr = duomi.com.utils.StringUtils.decodeUnicode(retstr);
			if (!output.isSuccess()) {
				// 更新接口状态为99
				regitSrv.updateCspStus4Error(comReq, retstr);
				logger.error("接口：" + interName + "审批工单编号：" + appno + ",errorDesc:" + output.getErrorDesc() + "请求报文:"
						+ reqstr);
				throw new HttpBizException(output.getCode(), output.getErrorDesc());
			}
		} else {
			GenTestData gtd = new GenTestData();
			output = gtd.genData4test(class1);
		}
		// 更新登记表状态为收到服务
		regitSrv.updateCspStusAfter(comReq, retstr);

		return output;
	}

	/**
	 * 极光接口调用
	 * */
	public JiGResponse<E> getDataJiGUang(String url , Object req , ComRequest comReq , Class<?> class1 , String requestMethod) throws Exception {
		JiGResponse<E> output = null;

		// 更新登记表状态为发送，
		regitSrv.updateCspStusBefore(comReq);
		String appno = comReq.getAppNo();
		String interName = comReq.getInterName();
		String reqstr = "";
		String retstr = "";

		if(PubConstants.SWITCH_ON.equals(PubConstants.JIGUANGDATA_SWITCH)){
			List<NameValuePair> list = JSONUtils.toNameValuePairList(req);
			reqstr = JSONUtils.toJSONString(req);

			logger.info("---------请求消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",请求消息：" + reqstr);
			if("GET".equals(requestMethod)){
				retstr = jiguangGet(url, list);
			}else if("POST".equals(requestMethod)){
				retstr = jiguangPost(url,JSONUtils.objectToJson(req));
			}

			logger.info("---------返回消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",返回消息：" + retstr + "\n,请求报文:" + reqstr);

			GsonUtils<E> gson = new GsonUtils<E>();
			output = (JiGResponse<E>) gson.fromJson(retstr, JiGResponse.class, class1);

			if(!"2000".equals(output.getCode()) && !"2001".equals(output.getCode())){
				// 更新接口状态为99
				regitSrv.updateCspStus4Error(comReq, retstr);
				logger.error("接口：" + interName + "审批工单编号：" + appno + ",errorDesc:" + output.getMessage() + "请求报文:"
						+ reqstr);
				throw new HttpBizException(output.getCode(), output.getMessage());
			}


		}

		// 更新登记表状态为收到服务
		regitSrv.updateCspStusAfter(comReq, retstr);
		return output;
	}

	/**
	 * 百融接口调用
	 * */
	public JSONObject getDataBaiRong(JSONObject jso , ComRequest comReq , Class<?> class1) throws Exception {
		JSONObject output = null;
		MerchantServer ms=new MerchantServer(); //百融jar包提供的发送请求的类
		//更新登记表状态为发送
		regitSrv.updateCspStusBefore(comReq);
		String appno = comReq.getAppNo();
		String interName = comReq.getInterName();
		String reqstr = "";
		String retstr = "";

		if(PubConstants.SWITCH_ON.equals(PubConstants.BR_SWITCH)){
			reqstr = JSONUtils.toJSONString(jso);

			logger.info("---------请求消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",请求消息：" + reqstr);

			retstr = ms.getApiData(jso.toString(),PubConstants.BR_API_CODE);  //获取接口信息

			logger.info("---------返回消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",返回消息：" + retstr + "\n,请求报文:" + reqstr);

//			GsonUtils<E> gson = new GsonUtils<E>();
//			output = (BrongPackageResponse<E>) gson.fromJson(retstr, BrongPackageResponse.class, class1);
			output = JSONUtils.toJSONObject(retstr);

			if(!"00".equals(output.getString("code")) && !"100002".equals(output.getString("code"))){
				// 更新接口状态为99
				regitSrv.updateCspStus4Error(comReq, retstr);
				logger.error("接口：" + interName + "审批工单编号：" + appno + ",errorCode:" + output.getString("code") + "请求报文:"
						+ reqstr);
				throw new HttpBizException(output.getString("code"), "详见百融接口文档");
			}


		}

		// 更新登记表状态为收到服务
		regitSrv.updateCspStusAfter(comReq, retstr);
		return output;

	}

	/**
	 * 易简云接口调用
	 * */
	public YiJianCloudResponse<E> getDataYijianCloud(String url , Map<String , Object> map , ComRequest comReq , Class<?> class1 , String requestMethod) throws Exception{
		YiJianCloudResponse<E> output  = null;

		//更新登记表状态为发送
		regitSrv.updateCspStusBefore(comReq);
		String appno = comReq.getAppNo();
		String interName = comReq.getInterName();
		String reqstr = "";
		String retstr = "";

		if(PubConstants.SWITCH_ON.equals(PubConstants.YJCLOUD_SWITCH)){

			reqstr = JSONUtils.toJSONString(map);

			logger.info("---------请求消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",请求消息：" + reqstr);

			retstr = sendWjMessage(map,url,requestMethod);

			logger.info("---------返回消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",返回消息：" + retstr + "\n,请求报文:" + reqstr);

			GsonUtils<E> gson = new GsonUtils<E>();
			output = (YiJianCloudResponse<E>) gson.fromJson(retstr, YiJianCloudResponse.class, class1);

			if(!"0".equals(output.getResult()) && !"易简云-手机号码强授权定位查询-定位开通".equals(interName)){
				// 更新接口状态为99
				regitSrv.updateCspStus4Error(comReq, retstr);
				logger.error("接口：" + interName + "审批工单编号：" + appno + ",errorDesc:" + output.getMessage() + "请求报文:"
						+ reqstr);
				throw new HttpBizException(output.getResult(), output.getMessage());
			}
		}

		// 更新登记表状态为收到服务
		regitSrv.updateCspStusAfter(comReq, retstr);
		return output;

	}

	/**
	 * 易简云的发送请求方法
	 * */
	public static String sendWjMessage(Map<String, Object> map,String url,String requestMethod){


		String desparams = DesUtil.getDesparam(PubConstants.APPKEY, map);

		StringBuffer buffer = new StringBuffer();
		buffer.append("appid=").append(PubConstants.APPID).append("&desparams=").append(desparams);

		String returnStr = null;

		if("GET".equals(requestMethod)){
			returnStr = WJHttpUtil.getSend(url,buffer.toString());
		}else if("POST".equals(requestMethod)){
			returnStr = WJHttpUtil.postSend(url,buffer.toString());
		}

		return returnStr;
	}

	/**
	 * 白骑士接口调用
	 * */
	public WightKnightResponse<E> getDataWightKnight(String url , Object req , ComRequest comReq , Class<?> class1 , String requestMethod) throws Exception {
		WightKnightResponse<E> output = null;

		// 更新登记表状态为发送，
		regitSrv.updateCspStusBefore(comReq);
		String appno = comReq.getAppNo();
		String interName = comReq.getInterName();
		String reqstr = "";
		String retstr = "";

		if(PubConstants.SWITCH_ON.equals(PubConstants.WKNIGHT_SWITCH)){
			List<NameValuePair> list = JSONUtils.toNameValuePairList(req);
			reqstr = JSONUtils.toJSONString(req);

			logger.info("---------请求消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",请求消息：" + reqstr);
			if("GET".equals(requestMethod)){
				//白骑士GET请求
//				retstr = jiguangGet(url, list);
			}else if("POST".equals(requestMethod)){
				//白骑士POST请求
				retstr = whiteKnightPost(url,JSONUtils.objectToJson(req));
			}

			logger.info("---------返回消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",返回消息：" + retstr + "\n,请求报文:" + reqstr);

			GsonUtils<E> gson = new GsonUtils<E>();
			output = (WightKnightResponse<E>) gson.fromJson(retstr, WightKnightResponse.class, class1);

			if(!"BQS000".equals(output.getResultCode())){
				// 更新接口状态为99
				regitSrv.updateCspStus4Error(comReq, retstr);
				logger.error("接口：" + interName + "审批工单编号：" + appno + ",errorDesc:" + output.getResultDesc() + "请求报文:"
						+ reqstr);
				throw new HttpBizException(output.getResultCode(), output.getResultDesc());
			}
		}

		// 更新登记表状态为收到服务
		regitSrv.updateCspStusAfter(comReq, retstr);
		return output;
	}


	public E postData4josonlib(String url, BaseRequest req, Class<?> outputclass, Class<?> member)
			throws HttpBizException {

		E output = null;
		if (PubConstants.ZBADATA_SWITCH_ON.equals(PubConstants.ZBADATA_SWITCH)) {

			try {
				BaseRequest reqs = req.init(req);
			} catch (Exception e) {
				e.printStackTrace();
			}

			List<NameValuePair> list = JSONUtils.toNameValuePairList(req);

			// String url="http://interface.zbadata.com/risk/decision/";
			String retstr = HttpTool.post(url, list);
			logger.info(retstr);

			// output=(BaseResponse<E>) new GsonUtils<E>().fromJson(retstr,
			// BaseResponse.class, class1);
			JSONObject jsonObject = JSONObject.fromObject(retstr);
			output = (E) JSONUtils.toBean(jsonObject, outputclass);

			logger.info(output.toString());
			/*
			 * if(!output.isSuccess()){ logger.info(output.getErrorDesc()); }
			 */
		} else {
			GenTestData gtd = new GenTestData();
			output = (E) gtd.genData4test(member);
		}
		return output;
	}

	public OutsideServiceRegistService getRegitSrv() {
		return regitSrv;
	}

	public void setRegitSrv(OutsideServiceRegistService regitSrv) {
		this.regitSrv = regitSrv;
	}

	/**
	 * 极光GET请求
	 * */
	public static String jiguangGet(String url, List<NameValuePair> list) {

		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		String strResult = "";

		BASE64Encoder base64Encoder = new BASE64Encoder();
		String text = PubConstants.JIGUANGDATA_DEVKEY + ":" + PubConstants.JIGUANGDATA_DEVSECRET;
		String base64Str = base64Encoder.encode(text.getBytes());
		try {
			httpclient = HttpUtil.getHttpClient();
			String str = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));//将传递的请求参数转化为get方式的请求
			HttpGet httpGet = new HttpGet(url + "?" + str);
			HttpUtil.config(httpGet);
			httpGet.addHeader("Content-type", "application/x-www-form-urlencoded");
			httpGet.setHeader("Authorization","Basic " + base64Str);

			response = httpclient.execute(httpGet);
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
	 * 极光POST请求
	 * */
	public static String jiguangPost(String url, String jsonStr){
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		String strResult = "";

		BASE64Encoder base64Encoder = new BASE64Encoder();
		String text = PubConstants.JIGUANGDATA_DEVKEY + ":" + PubConstants.JIGUANGDATA_DEVSECRET;
		String base64Str = base64Encoder.encode(text.getBytes());

		try {
			httpclient = HttpUtil.getHttpClient();
			HttpPost httpPost = new HttpPost(url);
			HttpUtil.config(httpPost);
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setHeader("accept","application/json");
			httpPost.setHeader("Authorization","Basic " + base64Str);
			HttpEntity entity=new StringEntity(jsonStr,"utf-8");
			httpPost.setEntity(entity);
			response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				strResult = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
			} else {
				strResult = String.valueOf(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e){
			e.printStackTrace();
		}finally {
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
	 * 白骑士POST请求
	 * */
	public static String whiteKnightPost(String url, String jsonStr){
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		String strResult = "";

		try {
			httpclient = HttpUtil.getHttpClient();
			HttpPost httpPost = new HttpPost(url);
			HttpUtil.config(httpPost);
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setHeader("accept","application/json");
			HttpEntity entity=new StringEntity(jsonStr,"utf-8");
			httpPost.setEntity(entity);
			response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				strResult = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
			} else {
				strResult = String.valueOf(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e){
			e.printStackTrace();
		}finally {
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

	public JSONObject getBaiRongBizForData(JSONObject jso, BRCommonRequest request, String dataUrl,String resultUrl) throws Exception {
		JSONObject output = null;
		String appno = request.getAppNo();
		String interName = request.getInterName();
		String reqstr = "";
		String retstr = "";
		final String[] result = new String[1];

		if(PubConstants.SWITCH_ON.equals(PubConstants.BR_SWITCH)) {
			reqstr = JSONUtils.toJSONString(jso);
			logger.info("---------请求消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",请求消息：" + reqstr);
			retstr = HttpUtil.sendFormPost(dataUrl, reqstr, 60000);
			logger.info("---------返回消息-----------接口识别标志：" + interName);
			logger.info("接口：" + interName + "审批工单编号：" + appno + ",返回消息：" + retstr + "\n,请求报文:" + reqstr);

			output = JSONUtils.toJSONObject(retstr);
			if(output.has("code") && output.getString("code").equals("100007")){
			    //token 过期
                BRongBizHttpServiceImpl.tokenid = null;
                BRongBizHttpServiceImpl.tokenid = BRongBizHttpServiceImpl.login();
            }
			if(!(output.getString("Code")).equals("20000")){
				// 更新接口状态为99
				regitSrv.updateCspStus4Error(request, retstr);
				logger.error("接口：" + interName + "审批工单编号：" + appno + ",errorCode:" + output.getString("Code") + "请求报文:"
						+ reqstr);
				throw new HttpBizException(output.getString("Code"), "详见百融接口文档");
			}
			String taskId = output.getJSONObject("Task").getString("Id");
			Callable callable = new Callable<JSONObject>() {
				@Override
				public JSONObject call() throws Exception {
					while(true) {
						JSONObject jsonData = new JSONObject();
						jsonData.put("api",request.getProductApi());
						jsonData.put("taskid",taskId);
						jso.put("jsonData",jsonData);
						jso.put("checkCode",MD5Utils.genMd5(jsonData.toString() + MD5Utils.genMd5(jso.getString("apiCode") + jso.getString("tokenid"))));
						result[0] = HttpUtil.sendFormPost(resultUrl,jso.toString(),60000);
						JSONObject rtJson = JSONUtils.toJSONObject(result[0]);
						if(rtJson.getJSONObject("Task").getString("Status").equals("FINISHED") ||
								rtJson.getJSONObject("Task").getString("Status").equals("FAILED")){
							return rtJson;
						}else{
							Thread.sleep(3000);
						};
					}
				}
			};
            output = (JSONObject) callable.call();
            if("20000".equals(output.getString("Code")) && output.has("Result")){
                // 接受到调度请求后，登记外部服务查询状态表
                regitSrv.insertCspStus(request);
                //更新登记表状态为发送
                regitSrv.updateCspStusBefore(request);
                // 更新登记表状态为收到服务
                regitSrv.updateCspStusAfter(request, output.toString());
            }
            return output;
		}
		return null;
	}
}
