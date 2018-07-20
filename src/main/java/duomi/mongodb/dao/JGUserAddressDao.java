package duomi.mongodb.dao;

import duomi.mongodb.bean.JGInterFaceModel.UserAddressModel;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/24.
 */
public interface JGUserAddressDao {

    public void add(UserAddressModel userAddressModel);

    public void remove(UserAddressModel userAddressModel);

    public UserAddressModel selectOne(UserAddressModel userAddressModel);

    public List<UserAddressModel> selectList(UserAddressModel userAddressModel);

    public List<UserAddressModel> selectAll();
}
