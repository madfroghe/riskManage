package duomi.mongodb.bean.JGInterFaceModel;

import duomi.com.httpIvk.param.JiGResponse;
import duomi.mongodb.dao.annotation.QueryField;
import duomi.mongodb.dao.annotation.QueryType;
import net.sf.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by DEllComputer on 2018/2/24.
 *
 * 极光-用户标签
 */
@Document(collection = "t_dm_csp_jg_usertag")
public class UserTagModel {

    @Id
    @QueryField
    private String id;

    @QueryField(type = QueryType.EQUALS, attribute = "appno")
    private String appno;

    @QueryField(type = QueryType.LIKE, attribute = "name")
    private String name;

    @QueryField(type = QueryType.EQUALS, attribute = "mobiel")
    private String mobiel;

    @QueryField
    private String idCard;

    @QueryField(type = QueryType.EQUALS,attribute = "interSerno")
    private String interSerno;

    @QueryField(type = QueryType.IN, attribute = "data")
    private JiGResponse<JSONObject> data;

    @QueryField(type = QueryType.IN, attribute = "analyJSON")
    private JSONObject analyJSON;

    public JSONObject getAnalyJSON() {
        return analyJSON;
    }

    public void setAnalyJSON(JSONObject analyJSON) {
        this.analyJSON = analyJSON;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppno() {
        return appno;
    }

    public void setAppno(String appno) {
        this.appno = appno;
    }

    public String getName() {
        return name;
    }

    public String getInterSerno() {
        return interSerno;
    }

    public void setInterSerno(String interSerno) {
        this.interSerno = interSerno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobiel() {
        return mobiel;
    }

    public void setMobiel(String mobiel) {
        this.mobiel = mobiel;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public JiGResponse<JSONObject> getData() {
        return data;
    }

    public void setData(JiGResponse<JSONObject> data) {
        this.data = data;
    }
}
