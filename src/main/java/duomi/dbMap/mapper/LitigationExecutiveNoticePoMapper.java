package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.LitigationExecutiveNoticePo;

public interface LitigationExecutiveNoticePoMapper {

	public int insert(LitigationExecutiveNoticePo record);

	public int insertSelective(LitigationExecutiveNoticePo record);

	public LitigationExecutiveNoticePo selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(LitigationExecutiveNoticePo record);

	public int updateByPrimaryKey(LitigationExecutiveNoticePo record);

	public List<LitigationExecutiveNoticePo> querylistByMap(Map<String, Object> params);
}