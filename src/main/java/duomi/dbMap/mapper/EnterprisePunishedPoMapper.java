package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.EnterprisePunishedPo;

public interface EnterprisePunishedPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(EnterprisePunishedPo record);

    public int insertSelective(EnterprisePunishedPo record);

    public  EnterprisePunishedPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(EnterprisePunishedPo record);

    public int updateByPrimaryKey(EnterprisePunishedPo record);
    
    public List<EnterprisePunishedPo> querylistByMap(Map<String, Object> params); 
}