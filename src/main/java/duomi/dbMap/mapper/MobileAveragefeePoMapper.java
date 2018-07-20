package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.MobileAveragefeePo;

public interface MobileAveragefeePoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(MobileAveragefeePo record);

    public int insertSelective(MobileAveragefeePo record);

    public MobileAveragefeePo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(MobileAveragefeePo record);

    public int updateByPrimaryKey(MobileAveragefeePo record);
    
    public MobileAveragefeePo selectByAppNo(Map<String, Object> params);
}