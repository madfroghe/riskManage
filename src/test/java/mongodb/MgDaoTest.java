package mongodb;

import java.text.SimpleDateFormat;
import java.util.*;

import duomi.com.constants.MongoDbCollectionConstants;
import duomi.com.httpIvk.param.BaseResponse;
import duomi.com.httpIvk.param.travel.AirTravelScoreResult;
import duomi.com.utils.FileUtil;
import duomi.com.utils.JSONUtils;
import duomi.dbMap.bean.CallDetailPoWithBLOBs;
import duomi.dbMap.bean.DecisionLogRecordPo;
import duomi.dbMap.bean.DecisionLogRecordPoWithBLOBs;
import duomi.dbMap.bean.MobileDetailRecordPo;
import duomi.dbMap.mapper.CallDetailPoMapper;
import duomi.dbMap.mapper.DecisionLogRecordPoMapper;
import duomi.dbMap.mapper.MobileDetailRecordPoMapper;
import duomi.mongodb.bean.TDCallDetailBean;
import duomi.mongodb.bean.wightknightInterfaecModel.DecisionModel;
import duomi.mongodb.bean.wightknightInterfaecModel.LoanReportModel;
import duomi.mongodb.dao.Impl.MongodbBaseDao2Impl;
import duomi.mongodb.dao.Impl.TDCallDetailMGDaoImpl;
import duomi.mongodb.dao.WhiteKnightInterFaceDao.impl.WKDecisionDaoImpl;
import duomi.mongodb.dao.WhiteKnightInterFaceDao.impl.WKLoanReportDAOImpl;
import duomi.services.wightknightservice.impl.WKDecisionServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import duomi.mongodb.bean.Article;
import duomi.mongodb.dao.Impl.ArticleDaoImpl;

/**
 * 测试springIoc是否成功 Created by tl on 17/2/11.
 * <p/>
 * 注解@ContextConfiguration表示将ApplicationContext对象注入进来，就不用像以往那样在测试程序里先new了，直接使用
 */
/*
 * @RunWith(SpringJUnit4ClassRunner.class) // @ContextConfiguration(locations =
 * "classpath:spring-context.xml")
 * 
 * @ContextConfiguration(locations = { "classpath:spring-context.xml",
 * "classpath:springmvc-servlet.xml" })
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ����junit spring�����ļ�
// @ContextConfiguration({ "classpath*:spring-context.xml",
// "classpath*:springmvc-servlet.xml" })
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class MgDaoTest {

	// 在任何需要记录日志的类中
	private static Logger logger = Logger.getLogger(MgDaoTest.class);

	@Autowired
	private ArticleDaoImpl atDao;

	@Autowired
	private WKLoanReportDAOImpl wkLoanReportDAO;

	@Autowired
	private TDCallDetailMGDaoImpl tdCallDetailMGDao;

	@Autowired
	private MongodbBaseDao2Impl mongodbBaseDao2;

	@Autowired
	private MobileDetailRecordPoMapper mobileDetailRecordDao;

	@Autowired
	private CallDetailPoMapper callDetailDao;

	@Autowired
	private DecisionLogRecordPoMapper decisionLogRecordPoMapper;

	public static JSONObject jsonObject1 = new JSONObject();

	@Test // 测试Spring IOC的开发环境
	public void save() {
		Map<String,Object> paramMap = new HashMap();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		paramMap.put("taskId","TASKYYS100000201712011822230700791262");
		MobileDetailRecordPo latelyRecord = mobileDetailRecordDao.queryLatelyTaskInfoByMap(paramMap);
        String time = sd.format(latelyRecord.getCreateTime());

		JSONObject mgJson = new JSONObject();

		if(latelyRecord != null){
			mgJson.put("mobile",latelyRecord.getMobile());
			try {

				String timeStr = sd.format(latelyRecord.getCreateTime());
				mgJson.put("time",timeStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_ZB_MOBILEAVERAGEFEE);
	}

	@Test
	public void testTDDetail(){
		CallDetailPoWithBLOBs callDetail = new CallDetailPoWithBLOBs();
		callDetail.setTaskId("test");
		callDetail.setReqJson("test");
		callDetail.setResJson("test");
		if(true){
			callDetail.setStatusType("02");
		}else{
			callDetail.setStatusType("01");
		}
		callDetailDao.insertSelective(callDetail);
	}

	@Test
	public void testMapperTest(){
		DecisionLogRecordPoWithBLOBs decisionLogRecordPo = new DecisionLogRecordPoWithBLOBs();
		decisionLogRecordPo.setId(16789L);
		decisionLogRecordPo.setMobile("1236675778");
		decisionLogRecordPo.setAppNo("aapppp");
		decisionLogRecordPo.setReqMsg("test");
		decisionLogRecordPo.setRspMsg("test");
		decisionLogRecordPoMapper.insert(decisionLogRecordPo);
	}

	@Test
	public void testTDMg(){
		JSONObject mgJson = new JSONObject();
		mgJson.put("taskId","test");
		mgJson.put("reqJson","test");
		mgJson.put("resJson","test");

			mgJson.put("mobile","test");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			mgJson.put("time",sd.format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}


		mongodbBaseDao2.save(mgJson, MongoDbCollectionConstants.T_DM_CSP_TD_MOBILEDETAIL);
	}

	@Test //
	public void selectOne() {
//		Article article = new Article();
		// article.setId("1121212");
//		article.setAppno("appno123");
//
//		article = atDao.selectOne(article);
//		System.out.println(article.getContent());
		LoanReportModel loanReportModel = new LoanReportModel();
		loanReportModel.setAppno("sfdghdsfdsfsdf234567");
		loanReportModel = wkLoanReportDAO.selectOne(loanReportModel);
		System.out.println(loanReportModel.getData().toString());
	}

	@Test //
	public void selectList() {
		Article article = new Article();
		// article.setId("1121212");
		article.setAppno("appno123");

		List<Article> articles = atDao.selectList(article);
		// logger.log(priority, message);(articles.size());
	}

	@Test //
	public void selectAll() {
		Article article = new Article();
		// article.setId("1121212");
		article.setAppno("appno123");

		List<Article> articles = atDao.selectAll();
		System.out.println(articles.size());
	}

	@Test //
	public void delete() {
		Article article = new Article();
		// article.setId("1121212");
		article.setAppno("appno123");

		atDao.remove(article);
	}

	/*
	 * @Test public void update() { User user =
	 * userDao.findOneByUsername("skyLine2");
	 * logger.info("-------更新之前账户密码:-------" + user.getPassword());
	 * 
	 * user.setPassword("9999888"); userDao.updateFirst(user);
	 * 
	 * logger.info("-------更新之后账户密码:-------" + user.getPassword()); }
	 * 
	 * @Test // 测试Spring IOC的开发环境 public void findAll() { // List<User> lists =
	 * userDao.findAll(); // for (User user : lists) { //
	 * logger.info("-------user遍历:-------" + user.getUsername()); // }
	 * 
	 * List<User> lists2 = userDao.findList(1, 2); for (User user : lists2) {
	 * logger.info("-------user遍历:-------" + user.getUsername()); } }
	 * 
	 * @Test public void delete() {
	 * userDao.delete(userDao.findOneByUsername("skyLine2").getId()); }
	 */

//	@Test
//	public void testJson(){
//		String filename = "/duomian_idea_svn/riskManage/src/main/resources/msg/AntiFraud3.json";
//		String jsonstr = null;
//		try {
//			jsonstr = FileUtil.readFile(filename, null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		JSONObject jsonObject = JSONObject.fromObject(jsonstr);
//		JSONObject jsonObject2 = this.analysisJson(jsonObject,"anaData", jsonObject1);
//		System.out.println(jsonObject2.toString());
//	}

	/**
	 * 将多层json解析为一层
	 * */
//	public JSONObject  analysisJson(Object objJson,String keyName,JSONObject staticJSON){
//		String firstKeyName = keyName;
//		//如果obj为json数组
//		if(objJson instanceof JSONArray){
//			JSONArray objArray = (JSONArray)objJson;
//			for (int i = 0; i < objArray.size(); i++) {
//				analysisJson(objArray.get(i),keyName+i,staticJSON);
//			}
//		}
//		//如果为json对象
//		else if(objJson instanceof JSONObject){
//			JSONObject jsonObject = (JSONObject)objJson;
//			Iterator it = jsonObject.keys();
//			while(it.hasNext()){
//
//				String key = it.next().toString();
//				if(key != null){
//					keyName += "." + key;
//				}
//				Object object = jsonObject.get(key);
//				//如果得到的是数组
//				if(object instanceof JSONArray){
//					JSONArray objArray = (JSONArray)object;
//					analysisJson(objArray,keyName,staticJSON);
//				}
//				//如果key中是一个json对象
//				else if(object instanceof JSONObject){
//					analysisJson((JSONObject)object,keyName,staticJSON);
//				}
//				//如果key中是其他
//				else{
//					staticJSON.put(keyName,object.toString());
//					keyName = firstKeyName;
//				}
//			}
//		}
//
//		return staticJSON;
//	}
	/**
	 * 给当前时间加上8小时
	 * */
	public static String formatTimeEight(Date time) throws Exception {
		Date d = null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = sd.format(time);
		d = sd.parse(timeStr);
		long rightTime = (long) (d.getTime() + 8 * 60 * 60 * 1000); //把当前得到的时间用date.getTime()的方法写成时间戳的形式，再加上8小时对应的毫秒数
		String newtime = sd.format(rightTime);//把得到的新的时间戳再次格式化成时间的格式
		return newtime;
	}

}