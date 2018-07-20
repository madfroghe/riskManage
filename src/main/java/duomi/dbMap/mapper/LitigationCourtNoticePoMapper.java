package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.LitigationCourtNoticePo;

public interface LitigationCourtNoticePoMapper {
	public int deleteByPrimaryKey(Long id);

	public int insert(LitigationCourtNoticePo record);

	public int insertSelective(LitigationCourtNoticePo record);

	public LitigationCourtNoticePo selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(LitigationCourtNoticePo record);

	public int updateByPrimaryKey(LitigationCourtNoticePo record);

	public List<LitigationCourtNoticePo> querylistByMap(Map<String, Object> params);

}