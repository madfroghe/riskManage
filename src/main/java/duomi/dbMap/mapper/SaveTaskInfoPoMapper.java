package duomi.dbMap.mapper;

import duomi.dbMap.bean.SaveTaskInfoPo;

public interface SaveTaskInfoPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaveTaskInfoPo record);

    int insertSelective(SaveTaskInfoPo record);

    SaveTaskInfoPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SaveTaskInfoPo record);

    int updateByPrimaryKey(SaveTaskInfoPo record);
}