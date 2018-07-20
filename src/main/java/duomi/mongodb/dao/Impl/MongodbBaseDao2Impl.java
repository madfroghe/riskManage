package duomi.mongodb.dao.Impl;

import duomi.mongodb.dao.MongodbBaseDao2;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Created by DEllComputer on 2018/3/8.
 */
@Component
public class MongodbBaseDao2Impl extends MongodbBaseDao2 {

    /**
     * 保存JSONObject对象到对应collection中
     * */
    @Override
    public JSONObject save(JSONObject jsonObject, String collectionName) {
        return super.save(jsonObject, collectionName);
    }
}
