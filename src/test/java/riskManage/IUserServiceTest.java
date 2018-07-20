package riskManage;

import org.apache.commons.collections.set.SynchronizedSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;
import duomi.dbMap.bean.MultipleLendPo;
import duomi.dbMap.bean.UsersPo;
import duomi.dbMap.mapper.CspInterfaceStatPoMapper;
import duomi.dbMap.mapper.MultipleLendPoMapper;
import duomi.dbMap.mapper.UsersPoMapper;


/**
 * ����spring��junit��ϣ�junit����ʱ����springIOC���� spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ����junit spring�����ļ�
@ContextConfiguration({ "classpath:applicationContext.xml"})
public class IUserServiceTest {
	@Autowired
    public CspInterfaceStatPoMapper dao;
    
   /* @Test
    public void getUserByIdTest(){
     
    	UsersPo user = userService.selectByPrimaryKey(3);
        System.out.println(user.getName());
    }*/
    
    
    @Test
    public void getUserByIdTest(){
    	Long id=(long) 1;
    	CspInterfaceStatPoWithBLOBs po=dao.selectByPrimaryKey(id);
    	
    	System.out.println(po.getName());
    }
    
}
