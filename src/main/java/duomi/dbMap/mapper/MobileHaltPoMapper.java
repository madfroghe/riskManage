package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.MobileHaltPo;

public interface MobileHaltPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(MobileHaltPo record);

    public int insertSelective(MobileHaltPo record);

    public MobileHaltPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(MobileHaltPo record);

    public int updateByPrimaryKey(MobileHaltPo record);
    
    public MobileHaltPo selectByAppNo(Map<String, Object> params);
}