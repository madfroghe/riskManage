package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.PhoneOnlineDurationPo;

public interface PhoneOnlineDurationPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(PhoneOnlineDurationPo record);

    public int insertSelective(PhoneOnlineDurationPo record);

    public PhoneOnlineDurationPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(PhoneOnlineDurationPo record);

    public int updateByPrimaryKey(PhoneOnlineDurationPo record);
    
    public PhoneOnlineDurationPo selectByAppNo(Map<String, Object> params);
}