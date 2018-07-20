package duomi.mongodb.bean;

import duomi.mongodb.dao.annotation.QueryField;
import duomi.mongodb.dao.annotation.QueryType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by DEllComputer on 2018/3/8.
 */
@Document(collection = "t_dm_td_call_detail")
public class TDCallDetailBean {
    @Id
    @QueryField
    private String id;

    @QueryField(type = QueryType.EQUALS, attribute = "taskId")
    private String taskId;

    @QueryField(type = QueryType.EQUALS , attribute = "resJSON")
    private String resJSON;

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

    public String getResJSON() {
        return resJSON;
    }

    public void setResJSON(String resJSON) {
        this.resJSON = resJSON;
    }
}
