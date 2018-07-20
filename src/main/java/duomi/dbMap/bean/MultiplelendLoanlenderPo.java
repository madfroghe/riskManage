package duomi.dbMap.bean;

import java.util.Date;

public class MultiplelendLoanlenderPo {
    private Long id;

    private Long multiplelendId;

    private String type;

    private String code;

    private String time;

    private Double loanlendersAmount;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMultiplelendId() {
        return multiplelendId;
    }

    public void setMultiplelendId(Long multiplelendId) {
        this.multiplelendId = multiplelendId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getLoanlendersAmount() {
        return loanlendersAmount;
    }

    public void setLoanlendersAmount(Double loanlendersAmount) {
        this.loanlendersAmount = loanlendersAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}