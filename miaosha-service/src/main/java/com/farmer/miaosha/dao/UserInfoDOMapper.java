package com.farmer.miaosha.dao;

import com.farmer.miaosha.DO.UserInfoDO;

public interface UserInfoDOMapper {

    /**
     * 插入用户信息
     *
     * @param record 用户信息
     */
    int insert(UserInfoDO record);

    /**
     * 根据手机号码获取用户信息
     *
     * @param mobile 手机号码
     */
    UserInfoDO selectByTelephone(String mobile);

    /**
     * 更新用户信息
     *
     * @param record 用户信息
     */
    int updateById(UserInfoDO record);

    /**
     * 查询手机号码是否存在
     */
    int selectTelephoneCount(UserInfoDO record);
}