package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.IdcardCheckPo;

public interface IdcardCheckPoMapper {
    public int deleteByPrimaryKey(Long id);

    public int insert(IdcardCheckPo record);

    public int insertSelective(IdcardCheckPo record);

    public IdcardCheckPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(IdcardCheckPo record);

    public int updateByPrimaryKey(IdcardCheckPo record);
    
    public IdcardCheckPo selectByAppNo(Map<String, Object> params);
}