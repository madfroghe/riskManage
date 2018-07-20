package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.PersonBlacklistDetailPo;

public interface PersonBlacklistDetailPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(PersonBlacklistDetailPo record);

    public int insertSelective(PersonBlacklistDetailPo record);

    public PersonBlacklistDetailPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(PersonBlacklistDetailPo record);

    public int updateByPrimaryKey(PersonBlacklistDetailPo record);
    
    public List<PersonBlacklistDetailPo> querylistByMap(Map<String, Object> params);
    
}