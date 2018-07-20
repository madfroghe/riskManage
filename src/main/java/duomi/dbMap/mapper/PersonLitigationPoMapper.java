package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.PersonLitigationPo;

public interface PersonLitigationPoMapper {
	public int deleteByPrimaryKey(Long id);

	public int insert(PersonLitigationPo record);

	public int insertSelective(PersonLitigationPo record);

	public PersonLitigationPo selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(PersonLitigationPo record);

	public int updateByPrimaryKey(PersonLitigationPo record);

	public int insertWithoutKey(PersonLitigationPo record);

	public List<PersonLitigationPo> selectListByAppNo(Map<String, Object> params);

	public List<PersonLitigationPo> querylistByMap(Map<String, Object> params);
}