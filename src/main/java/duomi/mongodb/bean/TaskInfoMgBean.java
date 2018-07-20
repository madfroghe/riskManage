package duomi.mongodb.bean;

/**
 * Created by DEllComputer on 2018/2/2.
 */

import duomi.mongodb.dao.annotation.QueryField;
import duomi.mongodb.dao.annotation.QueryType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "t_dm_csp_task_info")
public class TaskInfoMgBean {
    @Id
    @QueryField
    private String id;

    @QueryField(type = QueryType.EQUALS, attribute = "taskId")
    private String taskId;

    @QueryField(type = QueryType.EQUALS , attribute = "phone")
    private String phone;

    @QueryField(type = QueryType.EQUALS, attribute = "name")
    private String name;

    @QueryField(type = QueryType.EQUALS,attribute = "reqTime")
    private Date reqTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }
}
