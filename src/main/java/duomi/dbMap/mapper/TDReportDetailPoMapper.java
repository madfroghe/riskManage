package duomi.dbMap.mapper;

import duomi.dbMap.bean.TDReportDetailPo;
import duomi.dbMap.bean.TDReportDetailPoWithBLOBs;

import java.util.List;

public interface TDReportDetailPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TDReportDetailPoWithBLOBs record);

    int insertSelective(TDReportDetailPoWithBLOBs record);

    TDReportDetailPoWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TDReportDetailPoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TDReportDetailPoWithBLOBs record);

    int updateByPrimaryKey(TDReportDetailPo record);

    List<TDReportDetailPoWithBLOBs> queryReportDetailByMobile(String mobile);
}