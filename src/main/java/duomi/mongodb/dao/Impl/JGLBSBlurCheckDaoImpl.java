package duomi.mongodb.dao.Impl;

import duomi.mongodb.bean.JGInterFaceModel.LBSBlurCheckModel;
import duomi.mongodb.dao.JGLBSBlurCheckDao;
import duomi.mongodb.dao.MongodbBaseDao;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/23.
 */
@Component
public class JGLBSBlurCheckDaoImpl extends MongodbBaseDao<LBSBlurCheckModel> implements JGLBSBlurCheckDao {
    public void add(LBSBlurCheckModel lbsBlurCheckModel) {
        super.save(lbsBlurCheckModel);
    }

    public void remove(LBSBlurCheckModel lbsBlurCheckModel) {
        super.deleteByCondition(lbsBlurCheckModel);
    }

    public LBSBlurCheckModel selectOne(LBSBlurCheckModel lbsBlurCheckModel) {
        return super.findOne(lbsBlurCheckModel);
    }

    public List<LBSBlurCheckModel> selectList(LBSBlurCheckModel lbsBlurCheckModel) {
        return super.findByCondition(lbsBlurCheckModel);
    }

    public List<LBSBlurCheckModel> selectAll() {
        return findAll();
    }
}
