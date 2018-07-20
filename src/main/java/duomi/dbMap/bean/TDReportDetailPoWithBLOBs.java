package duomi.dbMap.bean;

public class TDReportDetailPoWithBLOBs extends TDReportDetailPo {
    private String riskContactStats;

    private String riskContactDetail;

    private String financeContactStats;

    private String financeContactDetail;

    public String getRiskContactStats() {
        return riskContactStats;
    }

    public void setRiskContactStats(String riskContactStats) {
        this.riskContactStats = riskContactStats;
    }

    public String getRiskContactDetail() {
        return riskContactDetail;
    }

    public void setRiskContactDetail(String riskContactDetail) {
        this.riskContactDetail = riskContactDetail;
    }

    public String getFinanceContactStats() {
        return financeContactStats;
    }

    public void setFinanceContactStats(String financeContactStats) {
        this.financeContactStats = financeContactStats;
    }

    public String getFinanceContactDetail() {
        return financeContactDetail;
    }

    public void setFinanceContactDetail(String financeContactDetail) {
        this.financeContactDetail = financeContactDetail;
    }
}