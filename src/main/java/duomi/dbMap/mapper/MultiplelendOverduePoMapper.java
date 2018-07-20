package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.MultiplelendOverduePo;

public interface MultiplelendOverduePoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MultiplelendOverduePo record);

    int insertSelective(MultiplelendOverduePo record);

    MultiplelendOverduePo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MultiplelendOverduePo record);

    int updateByPrimaryKey(MultiplelendOverduePo record);
    
    public List<MultiplelendOverduePo> querylistByMap(Map<String, Object> params);
    
}