package duomi.dbMap.mapper;

import duomi.dbMap.bean.EigenfactorPo;

public interface EigenfactorPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EigenfactorPo record);

    int insertSelective(EigenfactorPo record);

    EigenfactorPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EigenfactorPo record);

    int updateByPrimaryKey(EigenfactorPo record);
}