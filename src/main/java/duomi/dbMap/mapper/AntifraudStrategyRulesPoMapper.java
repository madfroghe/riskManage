package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.AntifraudStrategyRulesPo;

public interface AntifraudStrategyRulesPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AntifraudStrategyRulesPo record);

    int insertSelective(AntifraudStrategyRulesPo record);

    AntifraudStrategyRulesPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AntifraudStrategyRulesPo record);

    int updateByPrimaryKey(AntifraudStrategyRulesPo record);
    
    public List<AntifraudStrategyRulesPo> querylistByMap(Map<String, Object> params);
    
}