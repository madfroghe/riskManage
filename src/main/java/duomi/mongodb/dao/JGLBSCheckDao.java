package duomi.mongodb.dao;

import duomi.mongodb.bean.JGInterFaceModel.LBSCheckModel;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/23.
 */
public interface JGLBSCheckDao {

    public void add(LBSCheckModel lbsCheckModel);

    public void remove(LBSCheckModel lbsCheckModel);

    public LBSCheckModel selectOne(LBSCheckModel lbsCheckModel);

    public List<LBSCheckModel> selectList(LBSCheckModel lbsCheckModel);

    public List<LBSCheckModel> selectAll();
}
