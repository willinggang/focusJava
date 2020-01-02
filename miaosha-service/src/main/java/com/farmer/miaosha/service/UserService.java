package com.farmer.miaosha.service;

import com.farmer.miaosha.VO.UserInfoVO;

/**
 * 用户信息服务
 *
 * @author Administrator
 * @date 2019年 12月27日 14:07:54
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param mobile     手机号码
     * @param password   密码
     * @param mobileCode 验证码
     * @return
     */
    UserInfoVO login(String mobile, String password, String mobileCode);

    Integer register(String mobile,String password,String mobileCode);
}
