package duomi.mongodb.dao;

import duomi.mongodb.bean.JGInterFaceModel.EigenFactorModel;

import java.util.List;

/**
 * Created by DEllComputer on 2018/1/31.
 */
public interface EigenFactorDao {

    public void add(EigenFactorModel eigenFactorModel);

    public void remove(EigenFactorModel eigenFactorModel);

    public EigenFactorModel selectOne(EigenFactorModel eigenFactorModel);

    public List<EigenFactorModel> selectList(EigenFactorModel eigenFactorModel);

    public List<EigenFactorModel> selectAll();


}
