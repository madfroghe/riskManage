package duomi.dbMap.mapper;

import java.util.Map;

import duomi.dbMap.bean.EducationCheckPo;

public interface EducationCheckPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(EducationCheckPo record);

    public int insertSelective(EducationCheckPo record);

    public EducationCheckPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(EducationCheckPo record);

    public int updateByPrimaryKey(EducationCheckPo record);
    
    public EducationCheckPo selectByAppNo(Map<String, Object> params);
}