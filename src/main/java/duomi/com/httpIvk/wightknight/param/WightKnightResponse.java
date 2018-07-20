package duomi.com.httpIvk.wightknight.param;

import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/2/27.
 */
public class WightKnightResponse<T> {

    public String resultCode;   // 结果码，参见 ressultCode 附录码表
    public String resultDesc;   // 结果描述，如果结果码非成功则会返回失败明细
    public String flowNo;       // 本次请求的流水号，用于事后案件调查
    public String finalDecision;// 决策结果码，参见 decision 码表
    public String finalScore;   // 最终风险系数，只有权重策略模式下有效
    public T strategySet; // 策略集内容明细，参考 strategySet 字段说明
    public JSONObject resultData; //信贷报告data

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public String getFinalDecision() {
        return finalDecision;
    }

    public void setFinalDecision(String finalDecision) {
        this.finalDecision = finalDecision;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public T getStrategySet() {
        return strategySet;
    }

    public void setStrategySet(T strategySet) {
        this.strategySet = strategySet;
    }

    public JSONObject getResultData() {
        return resultData;
    }

    public void setResultData(JSONObject resultData) {
        this.resultData = resultData;
    }
}
