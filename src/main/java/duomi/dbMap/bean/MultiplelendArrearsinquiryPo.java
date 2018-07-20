package duomi.dbMap.bean;

import java.util.Date;

public class MultiplelendArrearsinquiryPo {
    private Long id;

    private Long multiplelendId;

    private String code;

    private String money;

    private Date creatTime;

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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}