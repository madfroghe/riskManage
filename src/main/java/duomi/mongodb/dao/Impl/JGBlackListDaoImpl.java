package duomi.mongodb.dao.Impl;

import duomi.mongodb.bean.JGInterFaceModel.BlackListModel;
import duomi.mongodb.dao.JGBlackListDao;
import duomi.mongodb.dao.MongodbBaseDao;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/24.
 */
@Component
public class JGBlackListDaoImpl extends MongodbBaseDao<BlackListModel> implements JGBlackListDao {
    public void add(BlackListModel blackListModel) {
          super.save(blackListModel);
    }

    public void remove(BlackListModel blackListModel) {
          super.deleteByCondition(blackListModel);
    }

    public BlackListModel selectOne(BlackListModel blackListModel) {
        return super.findOne(blackListModel);
    }

    public List<BlackListModel> selectList(BlackListModel blackListModel) {
        return super.findByCondition(blackListModel);
    }

    public List<BlackListModel> selectAll() {
        return findAll();
    }
}
