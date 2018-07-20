package duomi.dbMap.mapper;

import duomi.dbMap.bean.CallDetailPo;
import duomi.dbMap.bean.CallDetailPoWithBLOBs;

public interface CallDetailPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CallDetailPoWithBLOBs record);

    int insertSelective(CallDetailPoWithBLOBs record);

    CallDetailPoWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CallDetailPoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CallDetailPoWithBLOBs record);

    int updateByPrimaryKey(CallDetailPo record);
}