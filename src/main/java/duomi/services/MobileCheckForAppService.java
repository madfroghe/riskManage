package duomi.services;

import duomi.dispatch.request.Mobile3ERequest;

/**
 * Created by DEllComputer on 2018/4/9.
 */
public interface MobileCheckForAppService {

    //手机三要素验证
    public String checkMobileBy3E(Mobile3ERequest request) throws Exception;

}
