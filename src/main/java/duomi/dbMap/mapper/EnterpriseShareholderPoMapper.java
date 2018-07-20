package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.EnterpriseShareholderPo;

public interface EnterpriseShareholderPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(EnterpriseShareholderPo record);

    public int insertSelective(EnterpriseShareholderPo record);

    public EnterpriseShareholderPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(EnterpriseShareholderPo record);

    public int updateByPrimaryKey(EnterpriseShareholderPo record);
    
    public List<EnterpriseShareholderPo> querylistByMap(Map<String, Object> params);
}