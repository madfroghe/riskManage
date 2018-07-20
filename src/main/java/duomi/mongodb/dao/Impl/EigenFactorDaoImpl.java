package duomi.mongodb.dao.Impl;

import duomi.mongodb.bean.JGInterFaceModel.EigenFactorModel;
import duomi.mongodb.dao.EigenFactorDao;
import duomi.mongodb.dao.MongodbBaseDao;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DEllComputer on 2018/1/31.
 */
@Component
public class EigenFactorDaoImpl extends MongodbBaseDao<EigenFactorModel> implements EigenFactorDao {


    public void add(EigenFactorModel eigenFactorModel) {
        super.save(eigenFactorModel);
    }

    public void remove(EigenFactorModel eigenFactorModel) {
         super.deleteByCondition(eigenFactorModel);
    }

    public EigenFactorModel selectOne(EigenFactorModel eigenFactorModel) {
        return (EigenFactorModel)super.findOne(eigenFactorModel);
    }

    public List<EigenFactorModel> selectList(EigenFactorModel eigenFactorModel) {
        return super.findByCondition(eigenFactorModel);
    }

    public List<EigenFactorModel> selectAll() {
        return findAll();
    }
}
