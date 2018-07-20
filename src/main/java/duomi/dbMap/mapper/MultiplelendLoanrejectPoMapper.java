package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.MultiplelendLoanrejectPo;

public interface MultiplelendLoanrejectPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MultiplelendLoanrejectPo record);

    int insertSelective(MultiplelendLoanrejectPo record);

    MultiplelendLoanrejectPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MultiplelendLoanrejectPo record);

    int updateByPrimaryKey(MultiplelendLoanrejectPo record);
    
    public List<MultiplelendLoanrejectPo> querylistByMap(Map<String, Object> params);
    
}