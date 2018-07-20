package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.MobileStatusPo;

public interface MobileStatusPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(MobileStatusPo record);

    public int insertSelective(MobileStatusPo record);

    public MobileStatusPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(MobileStatusPo record);

    public int updateByPrimaryKey(MobileStatusPo record);
    
    public MobileStatusPo selectByAppNo(Map<String, Object> params);
}