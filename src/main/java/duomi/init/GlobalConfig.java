package duomi.init;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * properties文件初始化加载类
 */
public class GlobalConfig implements InitializingBean {
    private final static Logger logger = Logger.getLogger(GlobalConfig.class);
    //spring读取properties配置文件后转换为Resource数组
    private Resource[] keyFileResources;
    //properties文件值填充map
    private static Map<String, String> map = new HashMap<String, String>();
    //是否初始化标志
    protected static volatile boolean initialized = false;

    /**
     * 获取配置属性
     * @param key
     * @return
     */
    public static String get(String key) {
        return map.get(key);
    }

    public void setKeyFileResources(Resource... keyFileResources) {
        this.keyFileResources = keyFileResources;
    }

    /**
     * 将键值对取到集合内
     */
    private void loadKeyFormProperties() throws Exception{
        //验证是否已初始化
        if (initialized) {
            return;
        }
        try {
            //遍历所有的properties配置文件,将配置属性记录到map当中
            for (Resource keyFileResource: keyFileResources) {
                //转换Resource对象为Properties对象
                Properties props = PropertiesLoaderUtils.loadProperties(keyFileResource);
                //遍历properties并填值到map
                Enumeration enums = props.propertyNames();
                while (enums.hasMoreElements()){
                    String key = (String) enums.nextElement();
                    String value = props.getProperty(key);
                    map.put(key,value);
                }
            }
            initialized = true;
        } catch (Exception e) {
            logger.error("初始化读取properties配置文件异常",e);
            throw new Exception(e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadKeyFormProperties();
    }

}