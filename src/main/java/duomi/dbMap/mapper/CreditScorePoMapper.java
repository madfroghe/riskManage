package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.CreditScorePo;

public interface CreditScorePoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(CreditScorePo record);

    public int insertSelective(CreditScorePo record);

    public CreditScorePo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(CreditScorePo record);

    public int updateByPrimaryKey(CreditScorePo record);
    
    public CreditScorePo selectByAppNo(Map<String, Object> params);
}