package duomi.com.httpIvk.helper.yjCloudHelper;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DEllComputer on 2018/3/12.
 */
public class DesUtil {
    private final static String DES = "DES";

    public static void main(String[] args) throws Exception {


    }

    /**
     * 获取加密字符串
     * @param appkey
     * @param map
     * @return
     */
    public static String getDesparam(String appkey,Map<String, Object> map){
        if(map==null || map.isEmpty()){
            return "";
        }

        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet())
        {
            if (buffer.length() > 0)
            {
                buffer.append("&");
            }
            buffer.append(entry.getKey()+"=");
            buffer.append(entry.getValue());
        }

        return encode(appkey,buffer.toString()).replaceAll("\\+", "%2B");
    }

    /**
     * 获取解密map
     * @return
     */
    public static Map<String, String> getValueMap(String key,String desparams){
        Map<String, String> map = new HashMap<String, String>();
        try {
            String params = decode(key, desparams);
            String [] array = params.split("&");
            for(int i=0;i<array.length;i++){
                String [] arr = array[i].split("=");
                map.put(arr[0], arr[1]);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map = null;
        }

        return map;
    }

    /**
     * 加密
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public static String encode(String key, String data) {

        try {
            // 生成一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(DES);
            // 用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
            byte[] bytes = cipher.doFinal(data.getBytes("utf-8"));

            return new BASE64Encoder().encode(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 解密
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public static String decode(String key, String data) throws Exception {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            // 生成一个可信任的随机数源
            SecureRandom sr = new SecureRandom();

            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(DES);
            // 用密钥初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

            byte[] bytes = cipher.doFinal(decoder.decodeBuffer(data));
            String strs = new String(bytes,"utf-8");//new BASE64Encoder().encode(bytes);

            return strs;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
