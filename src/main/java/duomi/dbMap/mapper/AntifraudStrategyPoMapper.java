package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.AntifraudStrategyPo;

public interface AntifraudStrategyPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(AntifraudStrategyPo record);

    public int insertSelective(AntifraudStrategyPo record);

    public AntifraudStrategyPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(AntifraudStrategyPo record);

    public int updateByPrimaryKey(AntifraudStrategyPo record);
    
    public int insertWithoutKey(AntifraudStrategyPo record);
    
    public List<AntifraudStrategyPo> querylistByMap(Map<String, Object> params);
    
}