package duomi.dbMap.mapper;

import duomi.dbMap.bean.CspInterfaceStatPo;
import duomi.dbMap.bean.CspInterfaceStatPoWithBLOBs;

import java.util.List;
import java.util.Map;

public interface CspInterfaceStatPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(CspInterfaceStatPoWithBLOBs record);

    public int insertSelective(CspInterfaceStatPoWithBLOBs record);

    public CspInterfaceStatPoWithBLOBs selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(CspInterfaceStatPoWithBLOBs record);

    public int updateByPrimaryKeyWithBLOBs(CspInterfaceStatPoWithBLOBs record);

    public int updateByPrimaryKey(CspInterfaceStatPo record);
    
    public int insertWithoutKey(CspInterfaceStatPo record);
    
    public CspInterfaceStatPoWithBLOBs selectByMap(Map<String, Object> params);

    public List<CspInterfaceStatPoWithBLOBs> selectNoResultData();
}