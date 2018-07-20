package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.EnterprisePersoncasePo;

public interface EnterprisePersoncasePoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(EnterprisePersoncasePo record);

    public int insertSelective(EnterprisePersoncasePo record);

    public EnterprisePersoncasePo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(EnterprisePersoncasePo record);

    public int updateByPrimaryKey(EnterprisePersoncasePo record);
    
    public List<EnterprisePersoncasePo> querylistByMap(Map<String, Object> params); 
}