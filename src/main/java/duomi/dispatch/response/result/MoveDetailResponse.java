package duomi.dispatch.response.result;


import duomi.dbMap.bean.MoveDetailHasEndTime;

import java.util.List;

/**
 * Created by DEllComputer on 2018/4/17.
 */
public class MoveDetailResponse {
    List<MoveDetailHasEndTime> moveDetailList;

    public List<MoveDetailHasEndTime> getMoveDetailList() {
        return moveDetailList;
    }

    public void setMoveDetailList(List<MoveDetailHasEndTime> moveDetailList) {
        this.moveDetailList = moveDetailList;
    }
}
