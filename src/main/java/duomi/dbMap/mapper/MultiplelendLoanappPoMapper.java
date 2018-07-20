package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.MultiplelendLoanappPo;

public interface MultiplelendLoanappPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MultiplelendLoanappPo record);

    int insertSelective(MultiplelendLoanappPo record);

    MultiplelendLoanappPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MultiplelendLoanappPo record);

    int updateByPrimaryKey(MultiplelendLoanappPo record);
    
    public List<MultiplelendLoanappPo> querylistByMap(Map<String, Object> params);
    
}