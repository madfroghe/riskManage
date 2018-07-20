package duomi.dispatch.response.result;

import duomi.dbMap.bean.CostDetail;

import java.util.List;

/**
 * Created by DEllComputer on 2018/4/18.
 */
public class CostDetailResponse {
    List<CostDetail> costDetailList;

    public List<CostDetail> getCostDetailList() {
        return costDetailList;
    }

    public void setCostDetailList(List<CostDetail> costDetailList) {
        this.costDetailList = costDetailList;
    }
}
