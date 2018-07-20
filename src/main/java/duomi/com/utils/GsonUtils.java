package duomi.com.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.Gson;

import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.litigation.ExecutiveNotice;
import duomi.com.httpIvk.param.litigation.Notice;
import duomi.com.httpIvk.param.litigation.PersonLitigationResult;
import duomi.com.httpIvk.param.phone.PhoneCheckResult;

public class GsonUtils<T> {
	public static Object fromJson(String json, Class main, Class... member) {
		Gson gson = new Gson();
		Type objectType = type(main, member);
		return gson.fromJson(json, objectType);
	}

	/*
	 * public String toJson(Object obj, Class main, Class<T> member) { Gson gson
	 * = new Gson(); Type objectType = type(main, member); return
	 * gson.toJson(obj, objectType); }
	 */

	public String toJson(Object obj, Class main, Class... member) {
		Gson gson = new Gson();
		Type objectType = type(main, member);
		return gson.toJson(obj, objectType);
	}

	static ParameterizedType type(final Class raw, final Type... args) {
		return new ParameterizedType() {
			public Type getRawType() {
				return raw;
			}

			public Type[] getActualTypeArguments() {
				return args;
			}

			public Type getOwnerType() {
				return null;
			}
		};
	}

	public static void main(String[] args) {
		String jsonstr = "{'success':true,'data':{'sxgg':{'idCard':'xxx','status':'NO_DATA'},'cpws':{'idCard':'xxx','status':'NO_DATA'},'zxgg':{'idCard':'xxx','status':'EXIST','pageData':[{'name':'xxx','identificationNO':'xxx','executionTarget':72150,'recordTime':1484236800000,'caseNO':'(2017)黔0382执141号','court':''}]}}}";
		try {
			System.out.println(jsonstr);
			GsonUtils<PersonLitigationResult> gu = new GsonUtils<PersonLitigationResult>();
			BaseResponse<PersonLitigationResult> rp = (BaseResponse<PersonLitigationResult>) gu.fromJson(jsonstr,
					BaseResponse.class, PersonLitigationResult.class);
			System.out.println(rp);
			Notice<ExecutiveNotice> zxgg = rp.getData().getZxgg();

			String a = gu.toJson(rp, BaseResponse.class, PersonLitigationResult.class);
			System.out.println(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main2(String[] args) {
		String jsonstr = "{'success':true,'data':{'name':'吴程','mobile':'17782051822','idCard':'511622198809160235','status':'SAME','statusDesc':'一致'}}";
		try {
			System.out.println(jsonstr);
			GsonUtils<PhoneCheckResult> gu = new GsonUtils<PhoneCheckResult>();
			BaseResponse rp = (BaseResponse) gu.fromJson(jsonstr, BaseResponse.class, PhoneCheckResult.class);
			System.out.println(rp);

			String a = gu.toJson(rp, BaseResponse.class, PhoneCheckResult.class);
			System.out.println(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
