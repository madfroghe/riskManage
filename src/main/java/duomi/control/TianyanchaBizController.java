package duomi.control;

import duomi.Enums.tianyancha.TianyanchaBizEnum;
import duomi.com.exception.HttpBizException;
import duomi.dispatch.request.ComRequest;
import duomi.dispatch.response.ComResponse;
import duomi.services.tianyanchaService.TianyanchaBizCommonService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/tianyancha/biz",method = RequestMethod.POST)
public class TianyanchaBizController {
    @Autowired
    private TianyanchaBizCommonService tianyanchaBizCommonService;

    /**
     * 获取企业基本信息(天眼查)
     * @param request 通用请求参数对象
     * @return 通用返回结果对象
     * @throws HttpBizException 异常信息
     */
    @RequestMapping(value = "/getBizBaseInfo")
    public ComResponse<JSONObject> getBizBaseInfo(ComRequest request) throws HttpBizException {
        //验证必需参数
        Map<String,String> params = new HashMap<>();
        String bizFullName = request.getBizFullName();
        if (StringUtils.isEmpty(bizFullName)){
            throw new HttpBizException("企业全称不能为空!");
        }
        params.put("name",bizFullName);

        //获取对应枚举，并封装参数
        TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_BASE_INFO;
        bizEnum.setParams(params);

        return tianyanchaBizCommonService.entranceOfqueryBizInfo(bizEnum,request);
    }

    /**
     * 获取企业法律诉讼信息
     * @param request 通用请求参数对象
     * @return 通用返回结果对象
     * @throws HttpBizException 异常信息
     */
    @RequestMapping(value = "/getBizLawSuit")
    public ComResponse<JSONObject> getBizLawSuit(ComRequest request) throws HttpBizException {
        //验证必需参数
        Map<String,String> params = new HashMap<>();
        String bizFullName = request.getBizFullName();
        if (StringUtils.isEmpty(bizFullName)){
            throw new HttpBizException("企业全称不能为空!");
        }
        params.put("name",bizFullName);

        //获取对应枚举，并封装参数
        TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_LAW_SUIT;
        bizEnum.setParams(params);

        return tianyanchaBizCommonService.entranceOfqueryBizInfo(bizEnum,request);
    }

    /**
     * 获取企业法院公告信息
     * @param request 通用请求参数对象
     * @return 通用返回结果对象
     * @throws HttpBizException 异常信息
     */
    @RequestMapping(value = "/getBizCourtAnnouncement")
    public ComResponse<JSONObject> getBizCourtAnnouncement(ComRequest request) throws HttpBizException {
        //验证必需参数
        Map<String,String> params = new HashMap<>();
        String bizFullName = request.getBizFullName();
        if (StringUtils.isEmpty(bizFullName)){
            throw new HttpBizException("企业全称不能为空!");
        }
        params.put("name",bizFullName);

        //获取对应枚举，并封装参数
        TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_COURT_ANNOUNCEMENT;
        bizEnum.setParams(params);

        return tianyanchaBizCommonService.entranceOfqueryBizInfo(bizEnum,request);
    }

    /**
     * 获取企业失信人信息
     * @param request 通用请求参数对象
     * @return 通用返回结果对象
     * @throws HttpBizException 异常信息
     */
    @RequestMapping(value = "/getBizDishonest")
    public ComResponse<JSONObject> getBizDishonest(ComRequest request) throws HttpBizException {
        //验证必需参数
        Map<String,String> params = new HashMap<>();
        String bizFullName = request.getBizFullName();
        if (StringUtils.isEmpty(bizFullName)){
            throw new HttpBizException("企业全称不能为空!");
        }
        params.put("name",bizFullName);

        //获取对应枚举，并封装参数
        TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_DISHONEST;
        bizEnum.setParams(params);

        return tianyanchaBizCommonService.entranceOfqueryBizInfo(bizEnum,request);
    }

    /**
     * 获取企业被执行人信息
     * @param request 通用请求参数对象
     * @return 通用返回结果对象
     * @throws HttpBizException 异常信息
     */
    @RequestMapping(value = "/getBizZhixinginfo")
    public ComResponse<JSONObject> getBizZhixinginfo(ComRequest request) throws HttpBizException {
        //验证必需参数
        Map<String,String> params = new HashMap<>();
        String bizFullName = request.getBizFullName();
        if (StringUtils.isEmpty(bizFullName)){
            throw new HttpBizException("企业全称不能为空!");
        }
        params.put("name",bizFullName);

        //获取对应枚举，并封装参数
        TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_EXEC_INFO;
        bizEnum.setParams(params);

        return tianyanchaBizCommonService.entranceOfqueryBizInfo(bizEnum,request);
    }

    /**
     * 获取企业开庭公告信息
     * @param request 通用请求参数对象
     * @return 通用返回结果对象
     * @throws HttpBizException 异常信息
     */
    @RequestMapping(value = "/getBizKtAnnouncement")
    public ComResponse<JSONObject> getBizKtAnnouncement(ComRequest request) throws HttpBizException {
        //验证必需参数
        Map<String,String> params = new HashMap<>();
        String bizFullName = request.getBizFullName();
        if (StringUtils.isEmpty(bizFullName)){
            throw new HttpBizException("企业全称不能为空!");
        }
        params.put("name",bizFullName);

        //获取对应枚举，并封装参数
        TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_KT_ANNOUNCEMENT;
        bizEnum.setParams(params);

        return tianyanchaBizCommonService.entranceOfqueryBizInfo(bizEnum,request);
    }

    /**
     * 获取企业经营异常信息
     * @param request 通用请求参数对象
     * @return 通用返回结果对象
     * @throws HttpBizException 异常信息
     */
    @RequestMapping(value = "/getBizAbnormal")
    public ComResponse<JSONObject> getBizAbnormal(ComRequest request) throws HttpBizException {
        //验证必需参数
        Map<String,String> params = new HashMap<>();
        String bizFullName = request.getBizFullName();
        if (StringUtils.isEmpty(bizFullName)){
            throw new HttpBizException("企业全称不能为空!");
        }
        params.put("name",bizFullName);

        //获取对应枚举，并封装参数
        TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_ABNORMAL;
        bizEnum.setParams(params);

        return tianyanchaBizCommonService.entranceOfqueryBizInfo(bizEnum,request);
    }

    /**
     * 获取企业行政处罚信息
     * @param request 通用请求参数对象
     * @return 通用返回结果对象
     * @throws HttpBizException 异常信息
     */
    @RequestMapping(value = "/getBizPunishmentInfo")
    public ComResponse<JSONObject> getBizPunishmentInfo(ComRequest request) throws HttpBizException {
        //验证必需参数
        Map<String,String> params = new HashMap<>();
        String bizFullName = request.getBizFullName();
        if (StringUtils.isEmpty(bizFullName)){
            throw new HttpBizException("企业全称不能为空!");
        }
        params.put("name",bizFullName);

        //获取对应枚举，并封装参数
        TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_PUNISHMENT_INFO;
        bizEnum.setParams(params);

        return tianyanchaBizCommonService.entranceOfqueryBizInfo(bizEnum,request);
    }

    /**
     * 获取企业严重违法信息
     * @param request 通用请求参数对象
     * @return 通用返回结果对象
     * @throws HttpBizException 异常信息
     */
    @RequestMapping(value = "/getBizIllegalInfo")
    public ComResponse<JSONObject> getBizIllegalInfo(ComRequest request) throws HttpBizException {
        //验证必需参数
        Map<String,String> params = new HashMap<>();
        String bizFullName = request.getBizFullName();
        if (StringUtils.isEmpty(bizFullName)){
            throw new HttpBizException("企业全称不能为空!");
        }
        params.put("name",bizFullName);

        //获取对应枚举，并封装参数
        TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_ILLEGAL_INFO;
        bizEnum.setParams(params);

        return tianyanchaBizCommonService.entranceOfqueryBizInfo(bizEnum,request);
    }

    /**
     * 获取企业股权出质信息
     * @param request 通用请求参数对象
     * @return 通用返回结果对象
     * @throws HttpBizException 异常信息
     */
    @RequestMapping(value = "/getBizEquityInfo")
    public ComResponse<JSONObject> getBizEquityInfo(ComRequest request) throws HttpBizException {
        //验证必需参数
        Map<String,String> params = new HashMap<>();
        String bizFullName = request.getBizFullName();
        if (StringUtils.isEmpty(bizFullName)){
            throw new HttpBizException("企业全称不能为空!");
        }
        params.put("name",bizFullName);

        //获取对应枚举，并封装参数
        TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_EQUITY_INFO;
        bizEnum.setParams(params);

        return tianyanchaBizCommonService.entranceOfqueryBizInfo(bizEnum,request);
    }

    /**
     * 获取企业动产抵押信息
     * @param request 通用请求参数对象
     * @return 通用返回结果对象
     * @throws HttpBizException 异常信息
     */
    @RequestMapping(value = "/getBizMortgageInfo")
    public ComResponse<JSONObject> getBizMortgageInfo(ComRequest request) throws HttpBizException {
        //验证必需参数
        Map<String,String> params = new HashMap<>();
        String bizFullName = request.getBizFullName();
        if (StringUtils.isEmpty(bizFullName)){
            throw new HttpBizException("企业全称不能为空!");
        }
        params.put("name",bizFullName);

        //获取对应枚举，并封装参数
        TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_MORTGAGE_INFO;
        bizEnum.setParams(params);

        return tianyanchaBizCommonService.entranceOfqueryBizInfo(bizEnum,request);
    }

    /**
     * 获取企业司法拍卖信息
     * @param request 通用请求参数对象
     * @return 通用返回结果对象
     * @throws HttpBizException 异常信息
     */
    @RequestMapping(value = "/getBizJudicialSale")
    public ComResponse<JSONObject> getBizJudicialSale(ComRequest request) throws HttpBizException {
        //验证必需参数
        Map<String,String> params = new HashMap<>();
        String bizFullName = request.getBizFullName();
        if (StringUtils.isEmpty(bizFullName)){
            throw new HttpBizException("企业全称不能为空!");
        }
        params.put("name",bizFullName);

        //获取对应枚举，并封装参数
        TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_JUDICIAL_SALE;
        bizEnum.setParams(params);

        return tianyanchaBizCommonService.entranceOfqueryBizInfo(bizEnum,request);
    }

    /**
     * 获取企业关联图谱信息
     * @param request 通用请求参数对象
     * @return 通用返回结果对象
     * @throws HttpBizException 异常信息
     */
    @RequestMapping(value = "/getBizRelationMap")
    public ComResponse<JSONObject> getBizRelationMap(ComRequest request) throws HttpBizException {
        Map<String,String> params = new HashMap<>();
        //查询企业id
        String bizId = tianyanchaBizCommonService.getBizId(request);
        params.put("id",bizId);
        //封装请求参数
        TianyanchaBizEnum bizEnum = TianyanchaBizEnum.TYC_RELATION_MAP;
        bizEnum.setParams(params);
        //调用通用接口
        return tianyanchaBizCommonService.entranceOfqueryBizInfo(bizEnum,request);
    }
}
