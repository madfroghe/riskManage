package dozer;

import java.util.Date;

import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import duomi.com.httpIvk.param.mobiledetail.DBDetailCallRecord;
import duomi.dbMap.bean.MobileDetailRecordPo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class TestDozser {
	@Autowired
	Mapper mapper;

	@Test
	public void testdozser() {
		Long id = (long) 1;
		SourceBean sb = new SourceBean();
		sb.setId((long) 1233211);
		sb.setName("aaa");
		sb.setBinary(new Date());

		TargetBean tb = mapper.map(sb, TargetBean.class);
		System.out.println(tb.getMyname());
	}

	@Test
	public void testPo() {
		DBDetailCallRecord call_record_bean = new DBDetailCallRecord();
		call_record_bean.setCall_land_type("111");
		call_record_bean.setCall_address("bb");

		MobileDetailRecordPo mbPo = mapper.map(call_record_bean, MobileDetailRecordPo.class);
		System.out.println("-------------" + mbPo.getCallAddress());
	}

	/*
	 * public static void main(String[] args) { List<String> maplist=new
	 * ArrayList<String>(); maplist.add("dozer/test-mapper.xml"); Mapper
	 * mapper=new DozerBeanMapper(maplist); SourceBean sb=new SourceBean();
	 * sb.setId((long) 1233211); sb.setName("aaa"); sb.setBinary(new Date());
	 * 
	 * TargetBean tb=mapper.map(sb, TargetBean.class);
	 * System.out.println(tb.getMyname()); }
	 */

}
