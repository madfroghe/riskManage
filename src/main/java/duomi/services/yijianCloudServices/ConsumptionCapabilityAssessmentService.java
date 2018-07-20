package duomi.services.yijianCloudServices;

import duomi.dispatch.request.yijianCloud.ConsumptionCapabilityAssessmentRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/3/13.
 */
public interface ConsumptionCapabilityAssessmentService {

    //易简云-航旅消费能力评估
    public ComResponse<JSONObject> consumptionCapabilityAssessment(ConsumptionCapabilityAssessmentRequest request) throws Exception;
}
