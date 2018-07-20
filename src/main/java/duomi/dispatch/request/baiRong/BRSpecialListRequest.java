package duomi.dispatch.request.baiRong;

import duomi.dispatch.request.ComRequest;

/**
 * Created by DEllComputer on 2018/3/26.
 */
public class BRSpecialListRequest extends ComRequest {

    private String id; //必须，身份证号
    private String cell; //必须，手机号
    private String name; //必须,姓名
    private String linkman_cell; //非必须 1.兼容字符串形式，此形式仅能传1个手机号，例："15901110000" ，2.支持数组形式，例：“[15901110000,15901110000,15901110000]”（支持最多输入3个手机号）
    private String time_range; //非必须 取值：1-5，单位：年；填入相应数字表示查询最近几年的特殊名单服务，未填或填入其它数字表示默认查询5年

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getLinkman_cell() {
        return linkman_cell;
    }

    public void setLinkman_cell(String linkman_cell) {
        this.linkman_cell = linkman_cell;
    }

    public String getTime_range() {
        return time_range;
    }

    public void setTime_range(String time_range) {
        this.time_range = time_range;
    }
}
