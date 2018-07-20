package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.LitigationJudgmentDocPo;

public interface LitigationJudgmentDocPoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(LitigationJudgmentDocPo record);

	int insertSelective(LitigationJudgmentDocPo record);

	LitigationJudgmentDocPo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(LitigationJudgmentDocPo record);

	int updateByPrimaryKey(LitigationJudgmentDocPo record);

	public List<LitigationJudgmentDocPo> querylistByMap(Map<String, Object> params);
}