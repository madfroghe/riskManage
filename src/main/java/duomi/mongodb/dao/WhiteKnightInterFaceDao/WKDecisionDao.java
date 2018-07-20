package duomi.mongodb.dao.WhiteKnightInterFaceDao;

import duomi.mongodb.bean.wightknightInterfaecModel.DecisionModel;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/28.
 */
public interface WKDecisionDao {

    public void add(DecisionModel decisionModel);

    public void remove(DecisionModel decisionModel);

    public DecisionModel selectOne(DecisionModel decisionModel);

    public List<DecisionModel> selectList(DecisionModel decisionModel);

    public List<DecisionModel> selectAll();
}
