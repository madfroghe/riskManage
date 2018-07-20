package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.AntifraudPo;

public interface AntifraudPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(AntifraudPo record);

    public int insertSelective(AntifraudPo record);

    public AntifraudPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(AntifraudPo record);

    public int updateByPrimaryKey(AntifraudPo record);
    
    public int insertWithoutKey(AntifraudPo record);
    
    public AntifraudPo selectByAppNo(Map<String, Object> params);
}