package duomi.dbMap.bean;

import java.util.Date;

public class MultiplelendOverduePo {
    private Long id;

    private Long multiplelendId;

    private String code;

    private Integer counts;

    private String money;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}