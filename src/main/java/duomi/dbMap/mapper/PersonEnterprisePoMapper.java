package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.PersonEnterprisePo;

public interface PersonEnterprisePoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(PersonEnterprisePo record);

    public int insertSelective(PersonEnterprisePo record);

    public PersonEnterprisePo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(PersonEnterprisePo record);

    public int updateByPrimaryKey(PersonEnterprisePo record);
    
    public int insertWithoutKey(PersonEnterprisePo record);
    
    public PersonEnterprisePo selectByAppNo(Map<String, Object> params);
}