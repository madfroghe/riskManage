package duomi.com.constants;

import duomi.init.GlobalConfig;

public class PubConstants {
	public static final String ZBADATA_SWITCH = "on"; // 调用智宝数聚接口开关
	public static final String ZBADATA_SWITCH_OFF = "off"; // 调用智宝数聚接口开关 停用
	public static final String ZBADATA_SWITCH_ON = "on"; // 调用智宝数聚接口开关 启用
	public static final String ZBADATA_URL = "http://interface.zbadata.com"; // 智宝数聚接口地址
	public static final String ZBADATA_APPIDVAL = "283615341dd58a0a"; // 应用id-appidVal
	public static final String ZBADATA_KEYVAL = "mnb1UItdOOeBgfsUcDOH7HAmrSOfRztV"; // 密匙-keyVal

	public static final String TONGDUNDATA_URL = "https://api.tongdun.cn"; // 同盾地址
	public static final String SWITCH_ON = "on"; // 调用同盾接口开关 启用
	public static final String TONGDUDATA_SWITCH = "on"; // 调用同盾接口开关

	/** 外部服务查询状态表 状态值 **/
	public static final String OUTSIDESRV_STATUS_RECEIVEDISPATCH = "01"; // 01-收到调度请求,02-发送第三方服务,03-收到返回
	public static final String OUTSIDESRV_STATUS_SENDMSG = "02";// 02-发送第三方服务
	public static final String OUTSIDESRV_STATUS_RECEIVEDMSG = "03";// 03-收到返回
	public static final String OUTSIDESRV_STATUS_ERROR = "99";// 99-外部数据返回错误信息

	public static final String OUTSIDESRV_DATAVALID_VALID = "01"; // 01 有效
	public static final String OUTSIDESRV_DATAVALID_INVALID = "00"; // 00 无效

	public static final String ITF_LOG_MSGTYPE_RCVDISPATCHREQ = "01"; // 01-调度请求消息，02-发送的外部服务消息，03-收到的外部服务消息
	public static final String ITF_LOG_MSGTYPE_SENDMSG = "02"; // 01-调度请求消息，02-发送的外部服务消息，03-收到的外部服务消息
	public static final String ITF_LOG_MSGTYPE_RECEIVEMSG = "03"; // 01-调度请求消息，02-发送的外部服务消息，03-收到的外部服务消息

	public static final String CSP_STAT_ISEXISTS = "isExists"; // 是否已请求
	public static final String CSP_STAT_PO = "cspStatPo"; // 外部服务查询状态表

	/** 极光接口相关地址,密钥等 **/
	public static final String JIGUANGDATA_URL = "https://api.data.jiguang.cn";  //极光数据接口地址
	public static final String JIGUANGDATA_DEVKEY = "8b5c8f30946280eaf05519e2";  //开发者标识
	public static final String JIGUANGDATA_DEVSECRET = "819606115011d39e5a92682b"; //DEV_SECRET
	public static final String JIGUANGDATA_SWITCH = "on";  //调用极光接口开关 启用

	/** 白骑士接口相关partnerid，verifykey(正式环境)*/
	public static final String WKNIGHT_PARTNERID = "duomian";    //白骑士partenrid
	public static final String WKNIGHT_VERIFYKEY = "eddbb21810004eaa9009f5e8acd81df6";  //白骑士verifykey
	public static final String WKNIGHT_APPID = "duomian12345678";  //白骑士appId
	public static final String WKNIGHT_ACCOUNT = "duomian@baiqishi.com";  //白骑士多米安账户
	public static final String WKNIGHT_SWITCH = "on";         //白骑士接口开关

	/** 易简云接口相关地址,appid,key等  */
	public static final String YJCLOUD_URL = "http://api.wanjiedata.com/zxsp/";   //易简云接口地址
	public static final String APPID = "yijianyun";   //易简云appId
	public static final String APPKEY = "712DF195";   //易简云appKey
	public static final String YJCLOUD_SWITCH = "on";   //易简云接口开关

	/** 百融接口相关开关，密匙等 正式账号 */
//	public static final String BR_SWITCH = "on";    //百融接口开关
//  public static final String BR_USERNAME = "dmaAPI";  //百融登陆用户名
//	public static final String BR_PWD = "dmaAPI";  //百融登陆密码
//	public static final String BR_LOGIN_NAME = "LoginApi"; //百融登陆名
//	public static final String BR_API_CODE = "3000958"; //百融API CODE

	/** 百融接口相关开关，密匙等 测试账号 */
	public static final String BR_SWITCH = GlobalConfig.get("BR_SWITCH");    //百融接口开关
	public static final String BR_LOGIN_NAME = GlobalConfig.get("BR_LOGIN_NAME"); //百融登陆名

	//个人
	public static final String BR_PERSONAL_USERNAME = GlobalConfig.get("BR_PERSONAL_USERNAME");  //百融个人登陆用户名
	public static final String BR_PERSONAL_PWD = GlobalConfig.get("BR_PERSONAL_PWD");  //百融个人登陆密码
	public static final String BR_PERSONAL_API_CODE = GlobalConfig.get("BR_PERSONAL_API_CODE"); //百融API CODE

	//企业
	public static final String BR_BIZ_USERNAME = GlobalConfig.get("BR_BIZ_USERNAME"); //百融企业登录用户名
	public static final String BR_BIZ_PWD = GlobalConfig.get("BR_BIZ_PWD"); //百融企业登录密码
	public static final String BR_BIZ_API_CODE = GlobalConfig.get("BR_BIZ_API_CODE"); //百融企业API CODE
	public static final String BR_BIZ_V1_QUERY_DATA_URL = GlobalConfig.get("BR_BIZ_V1_QUERY_DATA_URL"); //百融企业请求数据接口
	public static final String Br_BIZ_V1_GET_RESULT_URL = GlobalConfig.get("Br_BIZ_V1_GET_RESULT_URL");//百融企业结果查询

}
