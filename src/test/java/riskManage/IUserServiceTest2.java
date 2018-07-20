package riskManage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import duomi.dbMap.bean.CspInterfaceStatPo;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.UsersPo;
import duomi.dbMap.mapper.CspInterfaceStatPoMapper;
import duomi.dbMap.mapper.UsersPoMapper;


public class IUserServiceTest2 {
	  public static void main(String[] args) {
	    ApplicationContext application = new ClassPathXmlApplicationContext("applicationContext.xml");
	 /*   UsersPoMapper uService = (UsersPoMapper) application.getBean("usersPoMapper");
	    UsersPo user=new UsersPo();
	    user.setId(3);
	    user.setName("naaaf");
	    user.setPassword("bbbd");
	    uService.insert(user);
	    
	    UsersPo user2 = uService.selectByPrimaryKey(1);*/
	    
	    CspInterfaceStatPoMapper dao=(CspInterfaceStatPoMapper) application.getBean("cspInterfaceStatPoMapper");
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("appListno", 1111);
		params.put("status", "03");
		CspInterfaceStatPoWithBLOBs po=dao.selectByMap(params);
	    
	    
	    System.out.println(po.getName());
	}
}
