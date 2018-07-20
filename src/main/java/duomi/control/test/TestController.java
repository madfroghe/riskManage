package duomi.control.test;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import duomi.dbMap.bean.UsersPo;
import duomi.dbMap.mapper.UsersPoMapper;
import duomi.services.TestService;


@Controller
public class TestController {
	@Autowired
    private TestService testsrv;
    //private ZbaDataHttpServiceImpl httpsrv;
    
    @RequestMapping(value="/test",method=RequestMethod.GET)
    public String showUserName(@RequestParam("uid") int uid,HttpServletRequest request,Model model){
		String a=testsrv.teststr(uid);
    	//AntiFraudOutput out=httpsrv.checkAntiFraud("张杰", "110102199901019150");
    	//String a=String.valueOf(out.isSuccess());
        request.setAttribute("name", a);
        model.addAttribute("mame", a);
        return "showName";
    }
}