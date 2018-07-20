package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.BankcardFourPo;

public interface BankcardFourPoMapper {
    public int deleteByPrimaryKey(Long id);

    public int insert(BankcardFourPo record);

    public int insertSelective(BankcardFourPo record);

    public BankcardFourPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(BankcardFourPo record);

    public int updateByPrimaryKey(BankcardFourPo record);
    
    public BankcardFourPo selectByAppNo(Map<String, Object> params);
}