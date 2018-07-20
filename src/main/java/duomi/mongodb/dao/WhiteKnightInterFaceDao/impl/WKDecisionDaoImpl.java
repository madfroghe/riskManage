package duomi.mongodb.dao.WhiteKnightInterFaceDao.impl;

import duomi.mongodb.bean.wightknightInterfaecModel.DecisionModel;
import duomi.mongodb.dao.MongodbBaseDao;
import duomi.mongodb.dao.WhiteKnightInterFaceDao.WKDecisionDao;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/28.
 */
@Component
public class WKDecisionDaoImpl extends MongodbBaseDao<DecisionModel> implements WKDecisionDao {
    public void add(DecisionModel decisionModel) {
        super.save(decisionModel);
    }

    public void remove(DecisionModel decisionModel) {
        super.deleteByCondition(decisionModel);
    }

    public DecisionModel selectOne(DecisionModel decisionModel) {
        return super.findOne(decisionModel);
    }

    public List<DecisionModel> selectList(DecisionModel decisionModel) {
        return super.findByCondition(decisionModel);
    }

    public List<DecisionModel> selectAll() {
        return findAll();
    }
}
