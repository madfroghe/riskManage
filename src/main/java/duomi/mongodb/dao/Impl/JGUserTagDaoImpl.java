package duomi.mongodb.dao.Impl;

import duomi.mongodb.bean.JGInterFaceModel.UserTagModel;
import duomi.mongodb.dao.JGUserTagDao;
import duomi.mongodb.dao.MongodbBaseDao;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/24.
 */
@Component
public class JGUserTagDaoImpl extends MongodbBaseDao<UserTagModel> implements JGUserTagDao {
    public void add(UserTagModel userTagModel) {
        super.save(userTagModel);
    }

    public void remove(UserTagModel userTagModel) {
        super.deleteByCondition(userTagModel);
    }

    public UserTagModel selectOne(UserTagModel userTagModel) {
        return super.findOne(userTagModel);
    }

    public List<UserTagModel> selectList(UserTagModel userTagModel) {
        return super.findByCondition(userTagModel);
    }

    public List<UserTagModel> selectAll() {
        return findAll();
    }
}
