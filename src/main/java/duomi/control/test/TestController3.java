package duomi.control.test;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import duomi.com.httpIvk.helper.HttpUtil;
import duomi.com.httpIvk.param.phone.PhoneCheckResult;
import duomi.com.utils.JSONUtils;
import duomi.services.TestService;

@Controller
public class TestController3 {
	@RequestMapping(value="/hello5.do")
    @ResponseBody
    public String hello(HttpServletResponse response) {
		PhoneCheckResult rt=new PhoneCheckResult();
		rt.setName("aaaa");
	    String jsonString = JSONUtils.toJSONString(rt);
	    return jsonString;
    }
	
	
}