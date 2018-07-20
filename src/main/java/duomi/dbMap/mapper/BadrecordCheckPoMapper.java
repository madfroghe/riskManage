package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.BadrecordCheckPo;

public interface BadrecordCheckPoMapper {
	public  int deleteByPrimaryKey(Long id);

    public int insert(BadrecordCheckPo record);

    public int insertSelective(BadrecordCheckPo record);

    public BadrecordCheckPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(BadrecordCheckPo record);

    public int updateByPrimaryKey(BadrecordCheckPo record);
    
    public BadrecordCheckPo selectByAppNo(Map<String, Object> params);
}