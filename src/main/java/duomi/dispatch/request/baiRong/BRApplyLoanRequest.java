package duomi.dispatch.request.baiRong;

import duomi.dispatch.request.ComRequest;

/**
 * Created by DEllComputer on 2018/3/28.
 */
public class BRApplyLoanRequest extends ComRequest {

    private String id; //必须，身份证号
    private String cell; //必须，手机号
    private String name; //必须,姓名

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
}
