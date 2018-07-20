package duomi.services.tianyanchaService;

import duomi.Enums.tianyancha.TianyanchaBizEnum;
import duomi.com.exception.HttpBizException;
import duomi.dispatch.request.ComRequest;
import duomi.dispatch.response.ComResponse;
import net.sf.json.JSONObject;

/**
 * 天眼查企业风控信息查询通用业务层
 */
public interface TianyanchaBizCommonService {

    /**
     * 通用的企业信息查询入口
     * @param bizEnum 查询信息枚举
     * @return 通用返回参数对象
     */
    ComResponse<JSONObject> entranceOfqueryBizInfo(TianyanchaBizEnum bizEnum, ComRequest request) throws HttpBizException;

    /**
     * 通用的企业信息查询接口
     * @param bizEnum 查询信息枚举
     * @return 通用返回参数对象
     */
    ComResponse<JSONObject> queryBizInfoOfCom(TianyanchaBizEnum bizEnum, ComRequest request) throws HttpBizException;

    /**
     * 查询需要分页查询的接口数据，并把多页的数据汇总
     * @param bizEnum 查询信息枚举
     * @return 多页的数据汇总
     */
    ComResponse<JSONObject> queryBizInfoOfPage(TianyanchaBizEnum bizEnum, ComRequest request) throws HttpBizException;
    /**
     * 获取企业id
     * @param request 通用参数对象
     * @return 企业id
     * @throws HttpBizException 异常信息
     */
    String getBizId(ComRequest request) throws HttpBizException;
}
