package duomi.services;

import duomi.dispatch.request.PersonalCreditRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * Created by DEllComputer on 2018/5/9.
 */
public interface PersonalCreditService  {

    //智宝-个人信用
    ComResponse<JSONObject> personalCredit(PersonalCreditRequest request) throws Exception;
}
