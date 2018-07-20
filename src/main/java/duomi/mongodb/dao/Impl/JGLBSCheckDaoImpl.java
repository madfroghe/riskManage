package duomi.mongodb.dao.Impl;

import duomi.mongodb.bean.JGInterFaceModel.LBSCheckModel;
import duomi.mongodb.dao.JGLBSCheckDao;
import duomi.mongodb.dao.MongodbBaseDao;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/23.
 */
@Component
public class JGLBSCheckDaoImpl extends MongodbBaseDao<LBSCheckModel> implements JGLBSCheckDao {

    public void add(LBSCheckModel lbsCheckModel) {
        super.save(lbsCheckModel);
    }

    public void remove(LBSCheckModel lbsCheckModel) {
        super.deleteByCondition(lbsCheckModel);
    }

    public LBSCheckModel selectOne(LBSCheckModel lbsCheckModel) {
        return super.findOne(lbsCheckModel);
    }

    public List<LBSCheckModel> selectList(LBSCheckModel lbsCheckModel) {
        return super.findByCondition(lbsCheckModel);
    }

    public List<LBSCheckModel> selectAll() {
        return findAll();
    }
}
