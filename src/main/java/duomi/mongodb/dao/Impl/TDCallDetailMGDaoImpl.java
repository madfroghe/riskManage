package duomi.mongodb.dao.Impl;

import duomi.mongodb.bean.TDCallDetailBean;
import duomi.mongodb.dao.MongodbBaseDao;
import duomi.mongodb.dao.TDCallDetailMGDao;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DEllComputer on 2018/3/8.
 */
@Component
public class TDCallDetailMGDaoImpl extends MongodbBaseDao<TDCallDetailBean> implements TDCallDetailMGDao {
    public void add(TDCallDetailBean tdCallDetailBean) {
        super.save(tdCallDetailBean);
    }

    public void remove(TDCallDetailBean tdCallDetailBean) {
        super.deleteByCondition(tdCallDetailBean);
    }

    public TDCallDetailBean selectOne(TDCallDetailBean tdCallDetailBean) {
        return findOne(tdCallDetailBean);
    }

    public List<TDCallDetailBean> selectList(TDCallDetailBean tdCallDetailBean) {
        return super.findByCondition(tdCallDetailBean);
    }

    public List<TDCallDetailBean> selectAll() {
        return findAll();
    }
}
