package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.EnterprisePunishbreakPo;
import duomi.dbMap.bean.EnterprisePunishbreakPoWithBLOBs;

public interface EnterprisePunishbreakPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(EnterprisePunishbreakPoWithBLOBs record);

    public int insertSelective(EnterprisePunishbreakPoWithBLOBs record);

    public EnterprisePunishbreakPoWithBLOBs selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(EnterprisePunishbreakPoWithBLOBs record);

    public int updateByPrimaryKeyWithBLOBs(EnterprisePunishbreakPoWithBLOBs record);

    public int updateByPrimaryKey(EnterprisePunishbreakPo record);
    
    public List<EnterprisePunishbreakPoWithBLOBs> querylistByMap(Map<String, Object> params); 
}