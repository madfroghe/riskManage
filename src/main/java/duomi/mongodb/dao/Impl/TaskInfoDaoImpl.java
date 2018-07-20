package duomi.mongodb.dao.Impl;

import duomi.mongodb.bean.TaskInfoMgBean;
import duomi.mongodb.dao.MongodbBaseDao;
import duomi.mongodb.dao.TaskInfoDao;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/2.
 */
@Component
public class TaskInfoDaoImpl extends MongodbBaseDao<TaskInfoMgBean> implements TaskInfoDao {


    public void add(TaskInfoMgBean taskInfoMgBean) {
        super.save(taskInfoMgBean);
    }

    public void remove(TaskInfoMgBean taskInfoMgBean) {
        super.deleteByCondition(taskInfoMgBean);
    }

    public TaskInfoMgBean selectOne(TaskInfoMgBean taskInfoMgBean) {
        return super.findOne(taskInfoMgBean);
    }

    public List<TaskInfoMgBean> selectList(TaskInfoMgBean taskInfoMgBean) {
        return super.findByCondition(taskInfoMgBean);
    }

    public List<TaskInfoMgBean> selectAll() {
        return super.findAll();
    }
}
