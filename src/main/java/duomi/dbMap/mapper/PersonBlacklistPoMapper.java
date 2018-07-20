package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.PersonBlacklistPo;

public interface PersonBlacklistPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(PersonBlacklistPo record);

    public int insertSelective(PersonBlacklistPo record);

    public PersonBlacklistPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(PersonBlacklistPo record);

    public int updateByPrimaryKey(PersonBlacklistPo record);
    
    public int insertWithoutKey(PersonBlacklistPo record);
    
    public PersonBlacklistPo selectByAppNo(Map<String, Object> params);
}