package duomi.dbMap.mapper;

import java.util.List;
import java.util.Map;

import duomi.dbMap.bean.MultiplelendArrearsinquiryPo;

public interface MultiplelendArrearsinquiryPoMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(MultiplelendArrearsinquiryPo record);

    public int insertSelective(MultiplelendArrearsinquiryPo record);

    public MultiplelendArrearsinquiryPo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(MultiplelendArrearsinquiryPo record);

    public int updateByPrimaryKey(MultiplelendArrearsinquiryPo record);
    
    public List<MultiplelendArrearsinquiryPo> querylistByMap(Map<String, Object> params);
    
}