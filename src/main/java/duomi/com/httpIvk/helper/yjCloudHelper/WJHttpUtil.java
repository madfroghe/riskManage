package duomi.com.httpIvk.helper.yjCloudHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DEllComputer on 2018/3/12.
 */
public class WJHttpUtil {
    /**
     * post发送
     * @param wjurl
     * @param params
     * @return
     */
    public static String postSend(String wjurl,String params)
    {
        URL url = null;
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();

        try
        {
            url = new URL(wjurl);

            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");// 提交模式
            conn.setConnectTimeout(2*60*1000);// 连接超时 单位毫秒
            conn.setReadTimeout(2*60*1000);// 读取超时 单位毫秒
            conn.setDoOutput(true);// 是否输入参数

            byte[] bytes = (params).getBytes();
            conn.getOutputStream().write(bytes);// 输入参数
            conn.connect();
            InputStream inStream = conn.getInputStream();
            // 读取数据编码处理

            String strRead = null;
            reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));

            while ((strRead = reader.readLine()) != null) {
                buffer.append(strRead);
                buffer.append("\r\n");
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (null != reader)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                }

            }
            if (null != conn)
            {
                // 断开连接
                conn.disconnect();
            }
        }
        String responseContent = buffer.toString();

        return  responseContent;
    }


    /**
     * get发送
     * @param wjurl
     * @param params
     * @return
     */
    public static String getSend(String wjurl,String params)
    {
        URL url = null;
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();

        try
        {
            url = new URL(wjurl);

            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");// 提交模式
            conn.setConnectTimeout(2*60*1000);// 连接超时 单位毫秒
            conn.setReadTimeout(2*60*1000);// 读取超时 单位毫秒
            conn.setDoOutput(true);// 是否输入参数

            byte[] bytes = (params).getBytes();
            conn.getOutputStream().write(bytes);// 输入参数
            conn.connect();
            InputStream inStream = conn.getInputStream();
            // 读取数据编码处理

            String strRead = null;
            reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));

            while ((strRead = reader.readLine()) != null) {
                buffer.append(strRead);
                buffer.append("\r\n");
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (null != reader)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                }

            }
            if (null != conn)
            {
                // 断开连接
                conn.disconnect();
            }
        }
        String responseContent = buffer.toString();

        return  responseContent;
    }

}
