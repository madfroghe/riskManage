package mobile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import duomi.com.httpIvk.param.mobiledetail.MBDetailRequest;
import duomi.services.TDMobileDetailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class TestMBInJUnit {
	@Autowired
	TDMobileDetailService service;

	@Test
	public void testMB() {
		MBDetailRequest req = new MBDetailRequest();
		req.setTask_id("TASKYYS100000201711301521590681101965");
		String rsp = service.getMobileDetail(req);
		System.out.println(rsp);
	}
}
