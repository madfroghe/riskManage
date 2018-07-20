package duomi.mongodb.dao.Impl;

import duomi.mongodb.bean.JGInterFaceModel.UserAddressModel;
import duomi.mongodb.dao.JGUserAddressDao;
import duomi.mongodb.dao.MongodbBaseDao;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/24.
 */
@Component
public class JGUserAddressDaoImp extends MongodbBaseDao<UserAddressModel> implements JGUserAddressDao {
    public void add(UserAddressModel userAddressModel) {
        super.save(userAddressModel);
    }

    public void remove(UserAddressModel userAddressModel) {
        super.deleteByCondition(userAddressModel);
    }

    public UserAddressModel selectOne(UserAddressModel userAddressModel) {
        return super.findOne(userAddressModel);
    }

    public List<UserAddressModel> selectList(UserAddressModel userAddressModel) {
        return super.findByCondition(userAddressModel);
    }

    public List<UserAddressModel> selectAll() {
        return findAll();
    }
}
