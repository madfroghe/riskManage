package duomi.services;

import duomi.com.httpIvk.param.education.EducationCheckResult;
import duomi.dispatch.request.EducationCheckquest;
import duomi.dispatch.response.ComResponse;

public interface EducationCheckService {
	
	public ComResponse<EducationCheckResult> CheckEducation(EducationCheckquest request) throws Exception;


}
