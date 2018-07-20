package duomi.mongodb.dao;

import duomi.mongodb.bean.JGInterFaceModel.LBSBlurCheckModel;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/23.
 */
public interface JGLBSBlurCheckDao {
    public void add(LBSBlurCheckModel lbsBlurCheckModel);

    public void remove(LBSBlurCheckModel lbsBlurCheckModel);

    public LBSBlurCheckModel selectOne(LBSBlurCheckModel lbsBlurCheckModel);

    public List<LBSBlurCheckModel> selectList(LBSBlurCheckModel lbsBlurCheckModel);

    public List<LBSBlurCheckModel> selectAll();
}
