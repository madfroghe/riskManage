package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.MobileCheckPo;

public interface MobileCheckPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(MobileCheckPo record);

    public int insertSelective(MobileCheckPo record);

    public MobileCheckPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(MobileCheckPo record);

    public int updateByPrimaryKey(MobileCheckPo record);
    
    public MobileCheckPo selectByAppNo(Map<String, Object> params);
}