package duomi.services;


import duomi.dbMap.bean.EigenfactorPo;
import duomi.dispatch.request.EigenfactorRequest;
import duomi.dispatch.response.ComResponse;


/**
 * Created by DEllComputer on 2018/1/30.
 */
public interface EigenfactorService {

    //反欺诈特征因子查询
    public ComResponse<EigenfactorPo> getEigenfactor(EigenfactorRequest request) throws Exception;
}
