package duomi.dbMap.mapper;

import duomi.dbMap.bean.FJWAuthorizationPo;

public interface FJWAuthorizationPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FJWAuthorizationPo record);

    int insertSelective(FJWAuthorizationPo record);

    FJWAuthorizationPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FJWAuthorizationPo record);

    int updateByPrimaryKey(FJWAuthorizationPo record);

    FJWAuthorizationPo getFJWAuthorizationPoByType(String type);

}