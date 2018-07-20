package duomi.dbMap.mapper;

import duomi.dbMap.bean.CheckMobileForApp;

import java.util.List;
import java.util.Map;

public interface CheckMobileForAppMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CheckMobileForApp record);

    int insertSelective(CheckMobileForApp record);

    CheckMobileForApp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CheckMobileForApp record);

    int updateByPrimaryKeyWithBLOBs(CheckMobileForApp record);

    int updateByPrimaryKey(CheckMobileForApp record);

    List<CheckMobileForApp> querylistByMap(Map<String,String> map);


}