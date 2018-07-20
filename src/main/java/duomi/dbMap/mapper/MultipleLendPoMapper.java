package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.MultipleLendPo;

public interface MultipleLendPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(MultipleLendPo record);

    public int insertSelective(MultipleLendPo record);

    public MultipleLendPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(MultipleLendPo record);

    public int updateByPrimaryKey(MultipleLendPo record);
    
    public int insertWithoutKey(MultipleLendPo record);
    
    public MultipleLendPo selectByAppNo(Map<String, Object> params);
}