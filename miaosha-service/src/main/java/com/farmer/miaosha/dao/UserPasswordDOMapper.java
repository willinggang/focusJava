package com.farmer.miaosha.dao;

import com.farmer.miaosha.DO.UserPasswordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserPasswordDOMapper {

    /**
     * 注册插入账号密码
     *
     * @param record 账号密码记录
     * @return
     */
    int insert(UserPasswordDO record);

    /**
     * 根据用户ID更新密码
     *
     * @param userId   用户ID
     * @param password 密码
     */
    int updateByUserId(@Param("userId") String userId, @Param("password") String password);

    /**
     * 查询用户密码
     * @param userId 用户ID
     */
    UserPasswordDO selectByUserId(String userId);
}