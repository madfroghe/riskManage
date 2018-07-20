package httpIvk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPInputStream;

import duomi.com.httpIvk.param.mobiledetail.MBDetailRequest;
import duomi.dispatch.request.*;
import duomi.dispatch.request.baiRong.BRExecutionRequest;
import duomi.dispatch.request.yijianCloud.*;
import duomi.services.TDMobileDetailService;
import org.apache.http.NameValuePair;

import duomi.com.httpIvk.helper.HttpUtil;
import duomi.com.httpIvk.house.param.HousePriceResult;
import duomi.com.httpIvk.param.antifraud.AntiFraudResult;
import duomi.com.httpIvk.param.blacklist.BlackListResult;
import duomi.com.httpIvk.param.consume.ConsCreditOfflineResult;
import duomi.com.httpIvk.param.creditGuard.CreditGuardRiskItems;
import duomi.com.httpIvk.param.creditscore.CreditScoreResult;
import duomi.com.httpIvk.param.education.EducationCheckResult;
import duomi.com.httpIvk.param.enterprise.PersonEnterpriseResult;
import duomi.com.httpIvk.param.idcard.IdCardCheckResult;
import duomi.com.httpIvk.param.litigation.PersonLitigationResult;
import duomi.com.httpIvk.param.overdue.LoanOverdueResult;
import duomi.com.httpIvk.param.phone.MobileAccessNumResult;
import duomi.com.httpIvk.param.phone.MobileAverageFeeResult;
import duomi.com.httpIvk.param.phone.MobileHaltResult;
import duomi.com.httpIvk.param.phone.MobileStatusResult;
import duomi.com.httpIvk.param.phone.PhoneCheckResult;
import duomi.com.httpIvk.param.phone.PhoneOnlineDurationResult;
import duomi.com.httpIvk.param.travel.AirTravelScoreResult;
import duomi.com.httpIvk.param.travel.FlightInfoResult;
import duomi.com.utils.JSONUtils;
import duomi.dispatch.request.house.HouseCityRequest;
import duomi.dispatch.request.house.HousePriceRequest;
import duomi.dispatch.request.house.HouseRentRequest;
import duomi.dispatch.response.ComResponse;
import duomi.dispatch.response.TDCreditGuardResponse;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HttpClientTest {



    public static void main(String[] args) {

        new HttpClientTest().testLBS();
    }

    /**
     * 测试易简云-个人对外投资
     * **/
    public void testEntPersonal(){
        QueryEntByPersonRequest queryEntByPersonRequest = new QueryEntByPersonRequest();
        queryEntByPersonRequest.setAppNo("234567sadsad8987sadsadsdsadadas65643");
        queryEntByPersonRequest.setUserName("杨帆");
        queryEntByPersonRequest.setEntityName("成都易捷金融服务外包有限公司");
        queryEntByPersonRequest.setAuthID("test456789867657654");
        List<NameValuePair> list = JSONUtils.toNameValuePairList(queryEntByPersonRequest);
        String url = "http://localhost:6780/riskManage/yjcloud/queryEntByPerson";
        String retStr = HttpUtil.post(url, list);
        System.out.println(retStr);
    }



    /**
     * 测试反欺诈服务黑名单
     * **/
    public void testBlackListCheck(){
        BlacklistcheckRequest blacklistcheckRequest = new BlacklistcheckRequest();
        blacklistcheckRequest.setAppNo("234567sadsad8987sadsadas65643");
        blacklistcheckRequest.setMobile("18280338368");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(blacklistcheckRequest);
        String url = "http://localhost:6780/riskManage/blacklistcheck";
        String retStr = HttpUtil.post(url, list);
        System.out.println(retStr);
    }


    /**
     * 测试易简云-手机号工作地区验证
     * */
    public void testWorkPlaceCheck(){
        HousePlaceCheckRequest workPlaceCheckRequest = new HousePlaceCheckRequest();
        workPlaceCheckRequest.setAppNo("2345678987sadsadas65643");
        workPlaceCheckRequest.setTelNO("18280338368");
//        104.109824,30.605177
        workPlaceCheckRequest.setLatitude("30.605177");
        workPlaceCheckRequest.setLongitude("104.109824");
        workPlaceCheckRequest.setAuthID("test234564567");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(workPlaceCheckRequest);
        String url = "http://localhost:6780/riskManage/yjcloud/housePlaceCheck";
        String retStr = HttpUtil.post(url, list);
        System.out.println(retStr);
    }




    /**
     * 测试智宝-个人司法信息列表    有问题,等待智宝回复
     * */
    public void testQueryEntByPerson(){
        PersonJudicialListRequest request = new PersonJudicialListRequest();
        request.setAppNo("456i343675s7sadaasdaasdsad67sdaadsa1aassddaaasdasdssa148");
        request.setName("姜东诚");
        request.setIdCard("511323199312254177");
        request.setDataType("cpws,ktgg,sxgg,zxgg");

        Date date = new Date();
        long dateTime = date.getTime();

        long dateTime2 = dateTime/1000;
        String timeStamp = String.valueOf(dateTime2);
        int time = Integer.valueOf(timeStamp);
        request.setTimestamp("1525924284");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(request);
        String url = "http://localhost:6780/riskManage/personJudicialList";
        String retStr = HttpUtil.post(url, list);
        System.out.println(retStr);
    }


    /**
     * 测试易简云-企业基本信息接口
     * */
    public void testBaseEntInfo(){
        QueryEntBaseInfoRequest request = new QueryEntBaseInfoRequest();
        request.setAppNo("456i343675s7671148");
        request.setEntityName("成都易捷金融服务外包有限公司");
        request.setAuthID("test23456789");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(request);
        String url = "http://localhost:6780/riskManage/yjcloud/queryEntBaseInfo";
        String retStr = HttpUtil.post(url, list);
        System.out.println(retStr);
    }




    /**
     * 临时调用易简和同盾相关接口
     * */
    public void testdiaodu(){
//        LoanOverDueRequest loanOverDueRequest = new LoanOverDueRequest();
//        loanOverDueRequest.setMobile("13541272588");
//        loanOverDueRequest.setUserName("杨成俊");
//        loanOverDueRequest.setTelNO("13541272588");
//        loanOverDueRequest.setCertCode("510130196304210113");
//        loanOverDueRequest.setAppNo("testlishi1234567890");
//        loanOverDueRequest.setQueryMonth("12");
//        loanOverDueRequest.setAuthID("test123445678");
//
//        List<NameValuePair> list = JSONUtils.toNameValuePairList(loanOverDueRequest);
//        String url = "http://localhost:6780/riskManage/yjcloud/loanoverDue";
//        String retStr = HttpUtil.post(url, list);
//        System.out.println(retStr);
        CreditGuardRequest po = new CreditGuardRequest();
        po.setAppNo("testlishi12345678901111");
        po.setMobile("13541272588");
        po.setName("杨成俊");
        po.setIdCard("510130196304210113");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);
        String url = "http://localhost:6780/riskManage/getCreditGuard";

        // 生产
        // String url = "http://47.100.77.49:6780/riskManage/getCreditGuard";
        String retstr = HttpUtil.post(url, list);
        System.out.println("----" + retstr);

    }



    /**
     * 易简云-手机强定位开通状态查询接口
     * */
    public void locationStatus(){
        LocationStatusRequest request = new LocationStatusRequest();
        request.setTelNO("15680413834");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(request);
        String url = "http://101.132.222.202:6780/riskManage/yjcloud/locationStatus";
        String retStr = HttpUtil.post(url, list);
        System.out.println(retStr);
    }

    /**
     * 易简云-手机强定位开通接口
     * */
    public void locationOrder(){
        LocationOrderRequest request = new LocationOrderRequest();
        request.setTelNO("18613227205");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(request);
        String url = "http://localhost:6780/riskManage/yjcloud/locationOrder";
        String retStr = HttpUtil.post(url, list);
        System.out.println(retStr);
    }

    /**
     * 易简云-金融黑名单
     * */
    public void financeBlackList(){
        FinanceBlackListRequest request = new FinanceBlackListRequest();
        request.setCertCode("511323199312254177");
        request.setUsername("姜东诚");
        request.setTelNO("18280338368");
        request.setAuthID("test1234567890");
        request.setAppNo("test45678954567");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(request);
        String url = "http://localhost:6780/riskManage/yjcloud/financeBlackList";
        String retStr = HttpUtil.post(url, list);
        System.out.println(retStr);
    }



    /**
     * 每月通话费
     */
    public void costDetailTest() {
        MobileQueryDetailRequest mobileQueryDetailRequest = new MobileQueryDetailRequest();
        mobileQueryDetailRequest.setMobile("18628082560");
        List<NameValuePair> list = JSONUtils.toNameValuePairList(mobileQueryDetailRequest);
        String url = "http://localhost:6780/riskManage/queryCostDetailByMobile";
        String retStr = HttpUtil.post(url, list);
        System.out.println(retStr);
    }

    /**
     * 魔盒数据回调测试
     */
    public void testTDDetail() {
        MBDetailRequest mbDetailRequest = new MBDetailRequest();
        mbDetailRequest.setTask_id("TASKYYS100000201801311434100700790838");


    }


    /**
     * 运动轨迹
     */
    public void moveTest() {
        MobileQueryDetailRequest mobileQueryDetailRequest = new MobileQueryDetailRequest();
        mobileQueryDetailRequest.setMobile("13982025111");
        List<NameValuePair> list = JSONUtils.toNameValuePairList(mobileQueryDetailRequest);
        String url = "http://localhost:6780/riskManage/queryMoveDetailByMobile";
        String retStr = HttpUtil.post(url, list);
        System.out.println(retStr);
    }


    /**
     * 小区租金
     */
    public void getHouseRent() {
        HouseRentRequest po = new HouseRentRequest();
        po.setAppNo("b120241265");
        po.setMobile("13281110613");
        po.setName("徐慧");

        po.setCity("成都市");
        po.setDistrict("高新区");
        po.setName("英郡");
        po.setSize(new BigDecimal(90));
        // po.setHouseType(3);

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://101.132.222.202:6780/riskManage/getCurrentHouseRent";
        // String url = "http://localhost:6780/riskManage/getCurrentHouseRent";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);

    }

    /**
     * 手机身份验证-for app
     */
    public void testApp() {
        MobileQueryDetailRequest mobileQueryDetailRequest = new MobileQueryDetailRequest();
        mobileQueryDetailRequest.setMobile("18280338368");
        List<NameValuePair> list = JSONUtils.toNameValuePairList(mobileQueryDetailRequest);
        String url = "http://localhost:6780/riskManage/queryMobileDetail2";
        String retStr = HttpUtil.post(url, list);
        System.out.println(retStr);

    }


    /**
     * 易简云-学历信息查询
     */
    public void testEducation() {
        EducationRequest educationRequest = new EducationRequest();
        educationRequest.setCertCode("511323199312254177");
        educationRequest.setUserName("姜东诚");
        educationRequest.setAuthID("test12345678");
        educationRequest.setAppNo("testd5678dsd9dsadas11");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(educationRequest);
        String url = "http://localhost:6780/riskManage/yjcloud/education";
        String retStr = HttpUtil.post(url, list);
        System.out.println(retStr);
    }

    /**
     * 极光-地址模糊匹配
     */
    public void testLBS() {
        JGLbsBlurCheckRequest res = new JGLbsBlurCheckRequest();
        res.setName("徐泽兴");
        res.setId_number("510130197508173515");
        res.setPhone("13608028655");
        res.setAddress_txt("四川省成都市临邛镇街道办临邛大道958号2栋1层149号");
        res.setImei("862991033227379");
        res.setAppNo("201805210004140123456789012");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(res);
        String url = "http://localhost:6780/riskManage/jiguang/lbsBlurCheck";
        String retStr = HttpUtil.post(url, list);
        System.out.println(retStr);

    }

    public void testTDMobileDetail() {
        MobileQueryDetailRequest request = new MobileQueryDetailRequest();
        request.setMobile("15680413834");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(request);

        String url = "http://localhost:6780/riskManage/queryReqTimeByPhone";

        String retStr = HttpUtil.post(url, list);

        System.out.println(retStr);


    }


    /**
     * 查询同盾信贷保镖
     */
    public void getCreditGuard() {
        CreditGuardRequest po = new CreditGuardRequest();
        po.setAppNo("2017120600114301");
        po.setMobile("15228980821");
        po.setName("王磊");
        po.setIdCard("510124198807110175");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String url = "http://101.132.222.202:6780/riskManage/getCreditGuard";
        String url = "http://localhost:6780/riskManage/getCreditGuard";

        // 生产
        // String url = "http://47.100.77.49:6780/riskManage/getCreditGuard";
        String retstr = HttpUtil.post(url, list);
        System.out.println("----" + retstr);

        if (retstr != null && retstr.indexOf("\\") > 0) {
            System.out.println(retstr.indexOf("\\"));
            retstr = retstr.replace("\\", "");
            if (retstr.startsWith("\"") && retstr.endsWith("\""))
                retstr = retstr.substring(1, retstr.length() - 1);
        }

        System.out.println("HttpClientTest.java:----" + retstr);
        /*
		 * BaseResponse<CreditGuardResult> output =
		 * (BaseResponse<CreditGuardResult>) new GsonUtils<CreditGuardResult>()
		 * .fromJson(retstr, BaseResponse.class, CreditGuardResult.class);
		 */

        List<Integer> rids = new ArrayList<Integer>();
        rids.add(2773331);
        rids.add(2773333);

        JSONObject jsonObject = JSONObject.fromObject(retstr);
        TDCreditGuardResponse output = JSONUtils.toBean(jsonObject, TDCreditGuardResponse.class);
        List<CreditGuardRiskItems> risk_items = output.getResData().getANTIFRAUD().getRisk_items();
        Iterator it = risk_items.iterator();
        int banknum1M = 0; // 1个月银行申请次数
        int nbanknum1M = 0;// 1个月非银行申请次数

        int banknum3M = 0;// 3个月银行申请次数
        int nbanknum3M = 0;// 3个月非银行申请次数

        String riskname1 = "";
        String riskname2 = "";
        int countrisk = 1;
        while (it.hasNext()) {
            MorphDynaBean risk_item = (MorphDynaBean) it.next();
            int rule_id = (Integer) risk_item.get("rule_id");
            String riskname = (String) risk_item.get("risk_name");
            if (countrisk == 1) {
                riskname1 = riskname;
            } else if (countrisk == 2) {
                riskname2 = riskname;
            }

            if (rule_id == 2773331) {
                List<MorphDynaBean> risk_details = (List<MorphDynaBean>) risk_item.get("risk_detail");
                if (risk_details != null) {
                    for (MorphDynaBean risk_detail : risk_details) {
                        List<MorphDynaBean> platform_details = (List<MorphDynaBean>) risk_detail.get("platform_detail");
                        if (platform_details != null) {
                            for (MorphDynaBean platform_detail : platform_details) {
                                String name = (String) platform_detail.get("industry_display_name");
                                int count = (Integer) platform_detail.get("count");
                                if (name.indexOf("银行") > -1) {
                                    banknum1M = banknum1M + count;
                                } else {
                                    nbanknum1M = nbanknum1M + count;
                                }
                            }
                        }
                    }
                }

            } else if (rule_id == 2773333) {
                List<MorphDynaBean> risk_details = (List<MorphDynaBean>) risk_item.get("risk_detail");
                if (risk_details != null) {
                    for (MorphDynaBean risk_detail : risk_details) {
                        List<MorphDynaBean> platform_details = (List<MorphDynaBean>) risk_detail.get("platform_detail");
                        if (platform_details != null) {
                            for (MorphDynaBean platform_detail : platform_details) {
                                String name = (String) platform_detail.get("industry_display_name");
                                int count = (Integer) platform_detail.get("count");
                                if (name.indexOf("银行") > -1) {
                                    banknum3M = banknum3M + count;
                                } else {
                                    nbanknum3M = nbanknum3M + count;
                                }
                            }
                        }
                    }
                }
            }

        }

        System.out.println(banknum1M + "---------" + nbanknum1M);
        System.out.println(banknum3M + "---------" + nbanknum3M);

        System.out.println(output);
    }

    /**
     * 查询通话详单
     */
    public void getMobileDetail() {
        MobileQueryDetailRequest po = new MobileQueryDetailRequest();
        po.setMobile("13658032580");
        // po.setName("徐慧");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://localhost:8080/riskManage/queryMobileDetail";
        // String url = "http://localhost:8080/riskManage/getFlightInfo";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);

    }

    /**
     * 查询个人贷款逾期
     */
    public void getLoanOverdue() {
        LoanOverdueRequest po = new LoanOverdueRequest();
        po.setAppNo("2017121200001201");
        po.setMobile("13880299284");
        po.setName("石勣");
        po.setIdCard("510104198409022879");
        po.setMonth("12");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String url = "http://101.132.222.202:6780/riskManage/getLoanOverdue";
        String url = "http://localhost:6780/riskManage/getLoanOverdue";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<LoanOverdueResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<LoanOverdueResult>().getClass());

        System.out.println(output);
    }

    /**
     * 手机号码自然人接入号码个数查询
     */
    public void getMobileNumbers() {
        FlightInfoRequest po = new FlightInfoRequest();
        po.setAppNo("120241");
        po.setMobile("13281110613");
        po.setName("徐慧");
        po.setIdCard("513902199805096886");
        po.setMonth("12");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://localhost:6780/riskManage/getMobileNumbers";
        // String url = "http://localhost:8080/riskManage/getFlightInfo";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<FlightInfoResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<FlightInfoResult>().getClass());

        System.out.println(output);
    }

    /**
     * 航旅信息查询
     */
    public void getFlightInfo() {
        FlightInfoRequest po = new FlightInfoRequest();
        po.setAppNo("20241");
        po.setMobile("13281110613");
        po.setName("徐慧");
        po.setIdCard("513902199805096886");
        po.setMonth("12");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://101.132.222.202:8080/riskManage/getSpecialList";
        // String url = "http://localhost:8080/riskManage/getFlightInfo";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<FlightInfoResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<FlightInfoResult>().getClass());

        System.out.println(output);
    }

    /**
     * 多次申请
     */
    public void getMultipleCreditAppe() {
        MultipleCreditAppRequest po = new MultipleCreditAppRequest();

        po.setAppNo("a21120234");
        po.setMobile("18584185039");
        po.setName("侯云苓");
        po.setIdCard("510821199108041311");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://101.132.222.202:6780/riskManage/getMultipleCreditApp";
        // String url = "http://localhost:8080/riskManage/getMultipleCreditApp";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
    }

    /**
     * 线下消费
     */
    public void getConsCreditOffline() {
        ConsCreditOfflineRequest po = new ConsCreditOfflineRequest();
        po.setAppNo("20234a");
        po.setMobile("18180516619");
        po.setName("蒋璐");
        po.setIdCard("510411198811248411");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String url = "http://101.132.222.202:8080/riskManage/getSpecialList";
        String url = "http://localhost:8080/riskManage/getConsCreditOffline";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<ConsCreditOfflineResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<ConsCreditOfflineResult>().getClass());

        System.out.println(output);
    }

    /**
     * 航旅
     */
    public void getAirTravelScore() {
        AirTravelScoreRequest po = new AirTravelScoreRequest();
        po.setAppNo("1120234");
        po.setMobile("13908081068");
        po.setName("石磊");
        po.setIdCard("510902198208039173");
        // {"appNo":"2017112800044701","idCard":"330724199012205617","interId":0,"interName":"航旅旅客价值等级查询","interNo":"ZB000022","interType":"01","mobile":"13540475386","name":"吴航"}
        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://101.132.222.202:6780/riskManage/getSpecialList";
        // String url = "http://localhost:8080/riskManage/getAirTravelScore";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<AirTravelScoreResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<AirTravelScoreResult>().getClass());

        System.out.println(output);
    }

    /**
     * 获取特殊名单
     */
    public void getSpecialList() {
        SpecialListRequest po = new SpecialListRequest();
        po.setAppNo("appno11111");
        po.setMobile("13880299284");
        po.setName("石勣");
        po.setIdCard("510104198409022879");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String url = "http://101.132.222.202:8080/riskManage/getSpecialList";
        String url = "http://localhost:6780/riskManage/getSpecialList";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<HousePriceResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<HousePriceResult>().getClass());

        System.out.println(output);
    }

    /**
     * 获取银行卡信息
     */
    public void getBackCardTrade() {
        BankCardTradeRequest po = new BankCardTradeRequest();
		/*
		 * po.setAppNo("ff20234"); po.setMobile("13540042701");
		 * po.setName("万露莹"); po.setIdCard("513001198102160044");
		 * po.setAccountNo("6217003810057004932");
		 */

		/*
		 * po.setAppNo("fffff0001"); po.setMobile("18181983228");
		 * po.setName("薛曾毅"); po.setIdCard("511028198312064817");
		 * po.setAccountNo("6217003810057004932");
		 */

        po.setAppNo("fffff0003");
        po.setMobile("18108160213");
        po.setName("贺元杰");
        po.setIdCard("511028198705260034");
        po.setAccountNo("6221532320013112557");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);
        // {"accountNo":"6217003810057004932","appNo":"2017112800051101","idCard":"513001198102160044","interId":0,"interName":"银行卡信息查询","interNo":"ZB000019","interType":"01","mobile":"13540042701","name":"万露莹"}
        // String url =
        // "http://101.132.222.202:8080/riskManage/getBackCardTrade";
        String url = "http://localhost:6780/riskManage/getBackCardTrade";
        String retstr = HttpUtil.post(url, list);

        System.out.println("----------" + retstr);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<HousePriceResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<HousePriceResult>().getClass());

        System.out.println(output);
    }

    /**
     * 户籍查询
     */
    public void getCensusRegister() {
        CensusRegisterRequest po = new CensusRegisterRequest();
        po.setAppNo("a120234");
        po.setMobile("18584185039");
        po.setName("侯云苓");
        po.setIdCard("510821199108041311");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String
        // url="http://101.132.222.202:8080/riskManage/checkPersonBlackList";
        String url = "http://localhost:8080/riskManage/getCensusRegister";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<HousePriceResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<HousePriceResult>().getClass());

        System.out.println(output);
    }

    /**
     * 获取房价网价格
     */
    public void getHousePrice() {
        HousePriceRequest po = new HousePriceRequest();
        po.setAppNo("test12031411");
        po.setMobile("");
        po.setName("");
        po.setIdCard("");

        po.setCity("成都市");
        po.setDistrict("双流区");
        po.setName("南阳锦城香樟阁");
        po.setSize("90.48");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String
        // url="http://101.132.222.202:8080/riskManage/checkPersonBlackList";
        // String url = "http://localhost:8080/riskManage/getHousePrice";
        // 生产

        String url = "http://47.100.78.18:8090/riskManage/getHousePrice";
        // String url = "http://47.100.77.49:6780/riskManage/getHousePrice";

        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<HousePriceResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<HousePriceResult>().getClass());

        System.out.println(output);
    }

    /**
     * 获取房价网城市
     */
    public void getCityRegions() {
        HouseCityRequest po = new HouseCityRequest();
        po.setAppNo("20234");
        po.setMobile("13281110613");
        po.setName("徐慧");
        po.setIdCard("513902199805096886");
        po.setCity("成都");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String
        // url="http://101.132.222.202:8080/riskManage/checkPersonBlackList";
        String url = "http://localhost:8080/riskManage/getCityRegions";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<PersonLitigationResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<PersonLitigationResult>().getClass());

        System.out.println(output);
    }

    /**
     * 获取房价网城市
     */
    public void getHouseCity() {
        ComRequest po = new ComRequest();
        po.setAppNo("20234");
        po.setMobile("13281110613");
        po.setName("徐慧");
        po.setIdCard("513902199805096886");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String
        // url="http://101.132.222.202:8080/riskManage/checkPersonBlackList";
        String url = "http://localhost:8080/riskManage/getCity";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<PersonLitigationResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<PersonLitigationResult>().getClass());

        System.out.println(output);
    }

    /**
     * 个人涉诉全类查询
     */
    public void getPersonLitigation() {
        PersonLitigationRequest po = new PersonLitigationRequest();
        po.setAppNo("20234");
        po.setMobile("13281110613");
        po.setName("徐慧");
        po.setIdCard("513902199805096886");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String
        // url="http://101.132.222.202:8080/riskManage/checkPersonBlackList";
        String url = "http://localhost:8080/riskManage/getPersonLitigation";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<PersonLitigationResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<PersonLitigationResult>().getClass());

        System.out.println(output);
    }

    /**
     * 个人名下企业查询
     */
    public void getPersonEnterprise() {
        PersonEnterpriseRequest po = new PersonEnterpriseRequest();
        po.setAppNo("111230230");
        po.setMobile("13488967880");
        po.setName("王霞");
        po.setIdCard("513022198109105023");

        // {"appNo":"2017112800026501","idCard":"513022198109105023","interId":0,"interName":"个人名下企业查询","interNo":"ZB000017","interType":"01","mobile":"13488967880","name":"王霞"}
        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://101.132.222.202:6780/riskManage/getPersonEnterprise";
        // String url = "http://localhost:8080/riskManage/getPersonEnterprise";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<PersonEnterpriseResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<PersonEnterpriseResult>().getClass());

        System.out.println(output);
    }

    /**
     * 手机号码最近三月停机次数查询
     */
    public void getMobileAccessNum() {
        MobileAccessNumRequest po = new MobileAccessNumRequest();
        po.setAppNo("20228");
        po.setMobile("13281110613");
        po.setName("徐慧");
        po.setIdCard("513902199805096886");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String
        // url="http://101.132.222.202:8080/riskManage/checkPersonBlackList";
        String url = "http://localhost:8080/riskManage/getMobileAccessNum";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<MobileAccessNumResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<MobileAverageFeeResult>().getClass());

        System.out.println(output);
    }

    /**
     * 手机号码最近三月停机次数查询
     */
    public void getMobileAverageFee() {
        MobileAverageFeeRequest po = new MobileAverageFeeRequest();
        po.setAppNo("20228");
        po.setMobile("13281110613");
        po.setName("徐慧");
        po.setIdCard("513902199805096886");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String
        // url="http://101.132.222.202:8080/riskManage/checkPersonBlackList";
        String url = "http://localhost:8080/riskManage/getMobileAverageFee";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<MobileAverageFeeResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<MobileAverageFeeResult>().getClass());

        System.out.println(output);
    }

    /**
     * 手机号指定月通话天数最多的城市
     */
    public void testMobileCity() {

        MobileMostCityRequest po = new MobileMostCityRequest();
        po.setAppNo("112022611");
        po.setMobile("13281110613");
        po.setName("徐慧");
        po.setIdCard("513902199805096886");
        po.setMonth("201710");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String
        // url="http://101.132.222.202:8080/riskManage/checkPersonBlackList";
        String url = "http://localhost:8080/riskManage/getMobileMostcity";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<MobileHaltResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<MobileHaltResult>().getClass());

        System.out.println(output);
    }

    /**
     * 手机号码最近三月停机次数查询
     */
    public void testMoblieHalt() {
        MobileHaltRequest po = new MobileHaltRequest();
        po.setAppNo("20222");
        po.setMobile("13281110613");
        po.setName("徐慧");
        po.setIdCard("513902199805096886");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String
        // url="http://101.132.222.202:8080/riskManage/checkPersonBlackList";
        String url = "http://localhost:8080/riskManage/getMobileHalt";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<MobileHaltResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<MobileHaltResult>().getClass());

        System.out.println(output);
    }

    /**
     * 个人黑名单
     */
    public void testBlackList() {
        BlackListRequest po = new BlackListRequest();
        po.setAppNo("121120123");
        po.setName("徐慧");
        po.setIdCard("513902199805096886");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String
        // url="http://101.132.222.202:8080/riskManage/checkPersonBlackList";
        String url = "http://localhost:8080/riskManage/checkPersonBlackList";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<BlackListResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<BlackListResult>().getClass());

        System.out.println(output);
    }

    /**
     * 个人多重借贷查询
     */
    public void testMultipleLend() {
        MultipleLendRequest po = new MultipleLendRequest();
        po.setAppNo("20123");

        po.setMobile("13281110613");
        po.setMonth("3");
        po.setPlatform("0");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://101.132.222.202:8080/riskManage/checkMultipleLend";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<AntiFraudResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<AntiFraudResult>().getClass());

        System.out.println(output);
    }

    /**
     * 反欺诈云
     */
    public void testAntiFraud() {
        AntiFraudRequest po = new AntiFraudRequest();
        po.setAppNo("20026");

        po.setMobile("13281110613");
        po.setName("徐慧");
        po.setIdCard("513902199805096886");
        // po.setAccountNo("6221532320013112331");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://101.132.222.202:8080/riskManage/checkAntiFraud";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<AntiFraudResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<AntiFraudResult>().getClass());

        System.out.println(output);
    }

    /**
     * 手机在网状态(三大运营商)
     */
    public void testMoblieStatus() {
        MobileStatusRequest po = new MobileStatusRequest();
        po.setAppNo("21220012");

        po.setMobile("13281110613");
        po.setName("徐慧");
        po.setIdCard("513902199805096886");
        // po.setAccountNo("6221532320013112331");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://localhost:8080/riskManage/getMobileStatus";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<MobileStatusResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<MobileStatusResult>().getClass());

        System.out.println(output);
    }

    /**
     * 手机号在网时长查询(三大运营商)
     */
    public void testPhoneOnlineDurationPo() {
        PhoneOnlineDurationRequest po = new PhoneOnlineDurationRequest();
        po.setAppNo("20013");

        po.setMobile("13281110613");
        po.setName("徐慧");
        po.setIdCard("513902199805096886");
        // po.setAccountNo("6221532320013112331");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://101.132.222.202:8080/riskManage/getPhoneOnlineDuration";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<PhoneOnlineDurationResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<PhoneOnlineDurationResult>().getClass());

        System.out.println(output);
    }

    /**
     * 个人信用评分
     */
    public void testCreditScore() {
        CreditScoreRequest po = new CreditScoreRequest();
        po.setAppNo("a12320013");

        po.setMobile("18584185039");
        po.setName("侯云苓");
        po.setIdCard("510821199108041311");
        // po.setAccountNo("6221532320013112331");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://101.132.222.202:6780/riskManage/getCreditScore";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<CreditScoreResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<CreditScoreResult>().getClass());

        System.out.println(output);
    }

    /**
     * 个人不良记录
     */
    public void testBadRecordCheck() {
        BadrecordRequest po = new BadrecordRequest();
        po.setAppNo("2017121300001601");

        po.setName("周伟春");
        po.setIdCard("310104196611063619");
        po.setMobile("13701888202");
        // po.setAccountNo("6221532320013112331");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String url = "http://101.132.222.202:6780/riskManage/checkBadrecord";
        String url = "http://localhost:6780/riskManage/checkBadrecord";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<EducationCheckResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<EducationCheckResult>().getClass());

        System.out.println(output);
    }

    /**
     * 学历查询
     */
    public void testEducationCheck() {
        EducationRequest po = new EducationRequest();
        po.setAppNo("190sadhasdsasd016");

        po.setUserName("潘鑫");
        po.setCertCode("511025199810231812");
        po.setAuthID("test23456789456");

        // po.setAccountNo("6221532320013112331");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://localhost:6780/riskManage/yjcloud/education";
        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<EducationCheckResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<EducationCheckResult>().getClass());

        System.out.println(output);
    }

    /**
     * 身份证二要素
     */
    public void testIdCardCheck() {
        IdCardRequest po = new IdCardRequest();
        po.setAppNo("test12123009111");

        po.setName("文宏");
        po.setIdCard("510524198809124177");
        // po.setMobile("13281110613");

        // po.setAccountNo("6221532320013112331");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String url = "http://localhost:8080/riskManage/checkIdCard";

        // String url = "http://47.100.77.49:6780/riskManage/checkIdCard";

        String url = "http://101.132.222.202:6780/riskManage/checkIdCard";

        String retstr = HttpUtil.post(url, list);

        System.out.println("HttpClientTest.java:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<IdCardCheckResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<IdCardCheckResult>().getClass());

        System.out.println(output);
    }

    /**
     * 手机三要素鉴权
     */
    public void testMobileCheck() {
        Mobile3ERequest po = new Mobile3ERequest();
        po.setAppNo("test1110003");
        // 18628199292
        po.setMobile("13088061615");
        po.setName("钟小兰");
        po.setIdCard("510106197909244820");
        // po.setAccountNo("6221532320013112331");

        // 510106198112164836 罗庆欢 18628119292 18628119292
        // 510106197909244820 钟小兰 13882143192 13882143192

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        // String url = "http://localhost:6780/riskManage/checkMoble";
        String url = "http://101.132.222.202:6780/riskManage/checkMoble";
        String retstr = HttpUtil.post(url, list);

        System.out.println("testjava:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse<PhoneCheckResult> output = JSONUtils.toBean(jsonObject,
                new ComResponse<PhoneCheckResult>().getClass());

        System.out.println(output);
    }

    /**
     * 银行卡四要素验证
     */
    public void testBackCard4E() {
        BackCard4ERequest po = new BackCard4ERequest();

        po.setAppNo("10003");
        po.setMobile("15882303925");
        po.setName("张文华");
        po.setIdCard("513722199410013622");
        po.setAccountNo("6221532320013112331");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://101.132.222.202:8080/riskManage/bankCard4E";
        String retstr = HttpUtil.post(url, list);
		/*
		 * System.out.println(retstr); String str=retstr.substring(1,
		 * retstr.length()-1);
		 */
        System.out.println("testjava:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse output = JSONUtils.toBean(jsonObject, ComResponse.class);

        System.out.println(output);
    }

    /**
     * 银行卡三要素验证
     */
    public void testBackCard3E() {
        BackCard3ERequest po = new BackCard3ERequest();
        po.setAppNo("test2200411112");
        po.setName("文宏");
        po.setIdCard("510524198809124177");
        // po.setAccountNo("6217583100030016163");
        po.setAccountNo("6221532330001803968");

        List<NameValuePair> list = JSONUtils.toNameValuePairList(po);

        String url = "http://101.132.222.202:6780/riskManage/bankCard3E";
        // String url = "http://localhost:8080/riskManage/bankCard3E";
        String retstr = HttpUtil.post(url, list);
		/*
		 * System.out.println(retstr); String str=retstr.substring(1,
		 * retstr.length()-1);
		 */
        System.out.println("testjava:----" + retstr);
        JSONObject jsonObject = JSONObject.fromObject(retstr);
        ComResponse output = JSONUtils.toBean(jsonObject, ComResponse.class);

        System.out.println(output);
    }

}
