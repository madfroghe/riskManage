package duomi.dbMap.mapper;

import duomi.dbMap.bean.UsersPo;

public interface UsersPoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UsersPo record);

    int insertSelective(UsersPo record);

    UsersPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UsersPo record);

    int updateByPrimaryKey(UsersPo record);
}