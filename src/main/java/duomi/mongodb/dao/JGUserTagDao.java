package duomi.mongodb.dao;

import duomi.mongodb.bean.JGInterFaceModel.UserTagModel;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/24.
 */
public interface JGUserTagDao {

    public void add(UserTagModel userTagModel);

    public void remove(UserTagModel userTagModel);

    public UserTagModel selectOne(UserTagModel userTagModel);

    public List<UserTagModel> selectList(UserTagModel userTagModel);

    public List<UserTagModel> selectAll();
}
