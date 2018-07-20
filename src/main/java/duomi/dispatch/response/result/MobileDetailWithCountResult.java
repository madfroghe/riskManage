package duomi.dispatch.response.result;

import duomi.dbMap.bean.MobileDetailRecordWithCountPo;

import java.util.List;

/**
 * Created by DEllComputer on 2018/4/13.
 */
public class MobileDetailWithCountResult {
    private List<MobileDetailRecordWithCountPo> mobileDetailRecordWithCountPos;

    public List<MobileDetailRecordWithCountPo> getMobileDetailRecordWithCountPos() {
        return mobileDetailRecordWithCountPos;
    }

    public void setMobileDetailRecordWithCountPos(List<MobileDetailRecordWithCountPo> mobileDetailRecordWithCountPos) {
        this.mobileDetailRecordWithCountPos = mobileDetailRecordWithCountPos;
    }
}
