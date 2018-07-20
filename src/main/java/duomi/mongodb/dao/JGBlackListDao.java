package duomi.mongodb.dao;

import duomi.mongodb.bean.JGInterFaceModel.BlackListModel;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/24.
 */
public interface JGBlackListDao {

    public void add(BlackListModel blackListModel);

    public void remove(BlackListModel blackListModel);

    public BlackListModel selectOne(BlackListModel blackListModel);

    public List<BlackListModel> selectList(BlackListModel blackListModel);

    public List<BlackListModel> selectAll();
}
