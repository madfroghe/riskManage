package duomi.mongodb.dao;

import duomi.mongodb.bean.TaskInfoMgBean;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/2.
 */
public interface TaskInfoDao {

    public void add(TaskInfoMgBean taskInfoMgBean);

    public void remove(TaskInfoMgBean taskInfoMgBean);

    public TaskInfoMgBean selectOne(TaskInfoMgBean taskInfoMgBean);

    public List<TaskInfoMgBean> selectList(TaskInfoMgBean taskInfoMgBean);

    public List<TaskInfoMgBean> selectAll();
}
