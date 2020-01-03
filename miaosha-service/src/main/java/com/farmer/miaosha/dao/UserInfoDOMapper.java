package com.farmer.miaosha.dao;

import com.farmer.miaosha.DO.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Mapper
@Repository
public interface UserInfoDOMapper {

    /**
     * 插入用户信息
     *
     * @param record 用户信息
     * @return
     */
    int insert(UserInfoDO record);

    /**
     * 根据手机号码获取用户信息
     *
     * @param mobile 手机号码
     * @return
     */
    UserInfoDO selectByTelephone(String mobile);

    /**
     * 更新用户信息
     *
     * @param record 用户信息
     * @return
     */
    int updateById(UserInfoDO record);

    /**
     * 查询手机号码是否存在
     * @param mobile 手机号
     * @return
     */
    int selectTelephoneCount(String mobile);
}