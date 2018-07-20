package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.EnterpriseManagerPo;

public interface EnterpriseManagerPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(EnterpriseManagerPo record);

    public int insertSelective(EnterpriseManagerPo record);

    public  EnterpriseManagerPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(EnterpriseManagerPo record);

    public int updateByPrimaryKey(EnterpriseManagerPo record);
    
    public List<EnterpriseManagerPo> querylistByMap(Map<String, Object> params); 
}