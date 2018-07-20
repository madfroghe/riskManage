package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.CensusRegisterPo;

public interface CensusRegisterPoMapper {
	public int deleteByPrimaryKey(Long id);

	public int insert(CensusRegisterPo record);

	public int insertSelective(CensusRegisterPo record);

	public CensusRegisterPo selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(CensusRegisterPo record);

	public int updateByPrimaryKey(CensusRegisterPo record);

	public CensusRegisterPo selectByAppNo(Map<String, Object> params);
}