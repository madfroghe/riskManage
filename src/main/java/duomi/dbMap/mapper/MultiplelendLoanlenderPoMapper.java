package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.MultiplelendLoanlenderPo;

public interface MultiplelendLoanlenderPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MultiplelendLoanlenderPo record);

    int insertSelective(MultiplelendLoanlenderPo record);

    MultiplelendLoanlenderPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MultiplelendLoanlenderPo record);

    int updateByPrimaryKey(MultiplelendLoanlenderPo record);
    
    public List<MultiplelendLoanlenderPo> querylistByMap(Map<String, Object> params);
    
}