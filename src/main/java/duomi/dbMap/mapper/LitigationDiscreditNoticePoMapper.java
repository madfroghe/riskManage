package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.LitigationDiscreditNoticePo;

public interface LitigationDiscreditNoticePoMapper {
	public int deleteByPrimaryKey(Long id);

	public int insert(LitigationDiscreditNoticePo record);

	public int insertSelective(LitigationDiscreditNoticePo record);

	public LitigationDiscreditNoticePo selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(LitigationDiscreditNoticePo record);

	public int updateByPrimaryKey(LitigationDiscreditNoticePo record);

	public List<LitigationDiscreditNoticePo> querylistByMap(Map<String, Object> params);

}