package duomi.mongodb.bean.wightknightInterfaecModel;

import duomi.com.httpIvk.wightknight.param.WightKnightResponse;
import duomi.mongodb.dao.annotation.QueryField;
import duomi.mongodb.dao.annotation.QueryType;
import net.sf.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by DEllComputer on 2018/2/28.
 * 白骑士-风险决策model
 */
@Document(collection = "t_dm_csp_white_knight_decision")
public class DecisionModel {
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

    @QueryField(type = QueryType.IN, attribute = "data")
    private WightKnightResponse<JSONObject> data;

    @QueryField(type = QueryType.IN, attribute = "analyJSON")
    private JSONObject analyJSON;

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

    public WightKnightResponse<JSONObject> getData() {
        return data;
    }

    public void setData(WightKnightResponse<JSONObject> data) {
        this.data = data;
    }

    public JSONObject getAnalyJSON() {
        return analyJSON;
    }

    public void setAnalyJSON(JSONObject analyJSON) {
        this.analyJSON = analyJSON;
    }
}
