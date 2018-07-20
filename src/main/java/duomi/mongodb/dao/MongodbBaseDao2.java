package duomi.mongodb.dao;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by DEllComputer on 2018/3/8.
 *
 */
public abstract class MongodbBaseDao2 {
    @Autowired
    protected MongoTemplate mongoTemplate;

    // 保存一个对象到mongodb
    public JSONObject save(JSONObject jsonObject,String collectionName) {
        mongoTemplate.save(jsonObject,collectionName);
        return jsonObject;
    }
}
