package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.MobileMostcityPo;

public interface MobileMostcityPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(MobileMostcityPo record);

    public int insertSelective(MobileMostcityPo record);

    public MobileMostcityPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(MobileMostcityPo record);

    public int updateByPrimaryKey(MobileMostcityPo record);
    
    public MobileMostcityPo selectByAppNo(Map<String, Object> params);
}