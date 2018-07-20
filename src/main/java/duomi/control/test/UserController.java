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


@Controller
public class UserController {

    @Autowired
    private UsersPoMapper userService;
    
    @RequestMapping(value="/showname",method=RequestMethod.GET)
    public String showUserName(@RequestParam("uid") int uid,HttpServletRequest request,Model model){
        System.out.println("showname");
        UsersPo user = userService.selectByPrimaryKey(uid);
        if(user != null){
            request.setAttribute("name", user.getName());
            model.addAttribute("mame", user.getName());
            return "showName";
        }
        request.setAttribute("error", "失败");
        return "error";
    }
}