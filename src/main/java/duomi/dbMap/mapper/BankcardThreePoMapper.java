package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.BankcardThreePo;

public interface BankcardThreePoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(BankcardThreePo record);

    public int insertSelective(BankcardThreePo record);

    public BankcardThreePo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(BankcardThreePo record);

    public int updateByPrimaryKey(BankcardThreePo record);
    
    public int insertWithoutKey(BankcardThreePo record);
    
    public BankcardThreePo selectByAppNo(Map<String, Object> params);
}