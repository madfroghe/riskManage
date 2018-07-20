package duomi.dbMap.mapper;

import duomi.dbMap.bean.HouseTokeyPo;

public interface HouseTokeyPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HouseTokeyPo record);

    int insertSelective(HouseTokeyPo record);

    HouseTokeyPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HouseTokeyPo record);

    int updateByPrimaryKey(HouseTokeyPo record);
}