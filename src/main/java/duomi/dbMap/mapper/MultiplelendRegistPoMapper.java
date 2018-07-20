package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.MultiplelendRegistPo;

public interface MultiplelendRegistPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MultiplelendRegistPo record);

    int insertSelective(MultiplelendRegistPo record);

    MultiplelendRegistPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MultiplelendRegistPo record);

    int updateByPrimaryKey(MultiplelendRegistPo record);
    
    public List<MultiplelendRegistPo> querylistByMap(Map<String, Object> params);
    
}