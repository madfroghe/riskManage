package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.EnterpriseRepresentativePo;

public interface EnterpriseRepresentativePoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(EnterpriseRepresentativePo record);

    public int insertSelective(EnterpriseRepresentativePo record);

    public EnterpriseRepresentativePo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(EnterpriseRepresentativePo record);

    public int updateByPrimaryKey(EnterpriseRepresentativePo record);
    
    public List<EnterpriseRepresentativePo> querylistByMap(Map<String, Object> params); 
}