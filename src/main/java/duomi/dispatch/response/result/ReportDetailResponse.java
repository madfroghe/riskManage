package duomi.dispatch.response.result;

import duomi.dbMap.bean.RiskContactDetail;
import duomi.dbMap.bean.RiskContactStats;

import java.util.List;

/**
 * Created by DEllComputer on 2018/4/23.
 */
public class ReportDetailResponse {
    List<RiskContactStats> riskContactStatsList;  //风险联系人统计
    List<RiskContactDetail> riskContactDetailList;  //风险联系人明细

    public List<RiskContactStats> getRiskContactStatsList() {
        return riskContactStatsList;
    }

    public void setRiskContactStatsList(List<RiskContactStats> riskContactStatsList) {
        this.riskContactStatsList = riskContactStatsList;
    }

    public List<RiskContactDetail> getRiskContactDetailList() {
        return riskContactDetailList;
    }

    public void setRiskContactDetailList(List<RiskContactDetail> riskContactDetailList) {
        this.riskContactDetailList = riskContactDetailList;
    }
}
