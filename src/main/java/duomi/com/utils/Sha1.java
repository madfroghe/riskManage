package duomi.com.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Sha1 {

	public static final String ignoreProperty = "sign";

	// 下面四个import放在类名前面 包名后面//import
	// java.io.UnsupportedEncodingException;//import
	// java.security.MessageDigest;//import
	// java.security.NoSuchAlgorithmException;//import java.util.Arrays;
	public static String getSha1(String str) {
		if (null == str || 0 == str.length()) {
			return null;
		}
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] buf = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取参数的字典排序
	 * 
	 * @param maps
	 *            参数key-value map集合
	 * @return String 排序后的字符串
	 */
	public static String getOrderByLexicographic(Map<String, Object> maps) {
		return splitParams(lexicographicOrder(getParamsName(maps)), maps);
	}

	/**
	 * 获取参数名称 key
	 * 
	 * @param maps
	 *            参数key-value map集合
	 * @return
	 */
	private static List<String> getParamsName(Map<String, Object> maps) {
		List<String> paramNames = new ArrayList<String>();
		for (Map.Entry<String, Object> entry : maps.entrySet()) {
			paramNames.add(entry.getKey());
		}
		return paramNames;
	}

	/**
	 * 参数名称按字典排序
	 * 
	 * @param paramNames
	 *            参数名称List集合
	 * @return 排序后的参数名称List集合
	 */
	private static List<String> lexicographicOrder(List<String> paramNames) {
		Collections.sort(paramNames);
		return paramNames;
	}

	/**
	 * 拼接排序好的参数名称和参数值
	 * 
	 * @param paramNames
	 *            排序后的参数名称集合
	 * @param maps
	 *            参数key-value map集合
	 * @return String 拼接后的字符串
	 */
	private static String splitParams(List<String> paramNames, Map<String, Object> maps) {
		StringBuilder paramStr = new StringBuilder();
		for (String paramName : paramNames) {
			if (ignoreProperty.equals(paramName)) {
				continue;
			}
			paramStr.append(paramName);
			for (Map.Entry<String, Object> entry : maps.entrySet()) {
				if (paramName.equals(entry.getKey())) {
					paramStr.append("=").append(String.valueOf(entry.getValue()));
				}
			}
		}
		return paramStr.toString();
	}

}
