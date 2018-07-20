package duomi.dbMap.mapper;

import duomi.dbMap.bean.CspInterfaceLogPo;

public interface CspInterfaceLogPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CspInterfaceLogPo record);

    int insertSelective(CspInterfaceLogPo record);

    CspInterfaceLogPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CspInterfaceLogPo record);

    int updateByPrimaryKeyWithBLOBs(CspInterfaceLogPo record);

    int updateByPrimaryKey(CspInterfaceLogPo record);
}