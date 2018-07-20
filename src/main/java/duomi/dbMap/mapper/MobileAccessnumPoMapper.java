package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.MobileAccessnumPo;

public interface MobileAccessnumPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(MobileAccessnumPo record);

    public int insertSelective(MobileAccessnumPo record);

    public MobileAccessnumPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(MobileAccessnumPo record);

    public int updateByPrimaryKey(MobileAccessnumPo record);
    
    public MobileAccessnumPo selectByAppNo(Map<String, Object> params);
}