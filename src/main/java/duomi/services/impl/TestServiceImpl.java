package duomi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duomi.com.httpIvk.services.ZbaDataHttpService;
import duomi.dbMap.bean.UsersPo;
import duomi.dbMap.mapper.UsersPoMapper;
import duomi.services.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
    private UsersPoMapper userService;
	
	@Autowired
	private ZbaDataHttpService httpservice;
	
	public String teststr(int uid) {
		UsersPo user = userService.selectByPrimaryKey(uid);
		System.out.println(user.getName());
		
		
		//AntiFraudOutput out=httpservice.checkAntiFraud("张杰", "110102199901019150");
		//System.out.println(out.isSuccess());
		return "bbbb";
	}

	public UsersPoMapper getUserService() {
		return userService;
	}

	public void setUserService(UsersPoMapper userService) {
		this.userService = userService;
	}

	public ZbaDataHttpService getHttpservice() {
		return httpservice;
	}

	public void setHttpservice(ZbaDataHttpService httpservice) {
		this.httpservice = httpservice;
	}
	
}
