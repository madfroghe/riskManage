package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.CostDetail;
import duomi.dbMap.bean.MobileDetailRecordPo;
import duomi.dbMap.bean.MobileDetailRecordWithCountPo;
import duomi.dbMap.bean.MoveDetail;

public interface MobileDetailRecordPoMapper {
	public int deleteByPrimaryKey(Long id);

	public int insert(MobileDetailRecordPo record);

	public int insertSelective(MobileDetailRecordPo record);

	public MobileDetailRecordPo selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(MobileDetailRecordPo record);

	public int updateByPrimaryKey(MobileDetailRecordPo record);

	public List<MobileDetailRecordPo> querylistByMap(Map<String, Object> params);

	public MobileDetailRecordPo queryLatelyTaskByMap(Map<String, Object> params);

	public MobileDetailRecordPo queryLatelyTaskInfoByMap(Map<String, Object> params);

	public List<MobileDetailRecordWithCountPo> queryListByMobile(String mobile);

	public List<MoveDetail> queryMoveDetailListByMobile(String mobile);

	public List<CostDetail> queryMonthCostDetailByMobile(String mobile);

}