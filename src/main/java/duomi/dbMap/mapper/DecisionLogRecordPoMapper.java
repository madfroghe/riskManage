package duomi.dbMap.mapper;

import duomi.dbMap.bean.DecisionLogRecordPo;
import duomi.dbMap.bean.DecisionLogRecordPoWithBLOBs;

public interface DecisionLogRecordPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DecisionLogRecordPoWithBLOBs record);

    int insertSelective(DecisionLogRecordPoWithBLOBs record);

    DecisionLogRecordPoWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DecisionLogRecordPoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DecisionLogRecordPoWithBLOBs record);

    int updateByPrimaryKey(DecisionLogRecordPo record);
}