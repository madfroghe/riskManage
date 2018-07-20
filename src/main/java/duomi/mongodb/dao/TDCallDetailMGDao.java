package duomi.mongodb.dao;

import duomi.mongodb.bean.TDCallDetailBean;

import java.util.List;

/**
 * Created by DEllComputer on 2018/3/8.
 */
public interface TDCallDetailMGDao {

    public void add(TDCallDetailBean tdCallDetailBean);

    public void remove(TDCallDetailBean tdCallDetailBean);

    public TDCallDetailBean selectOne(TDCallDetailBean tdCallDetailBean);

    public List<TDCallDetailBean> selectList(TDCallDetailBean tdCallDetailBean);

    public List<TDCallDetailBean> selectAll();
}
