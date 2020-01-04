package com.farmer.miaosha.service;

import com.farmer.miaosha.VO.RegisterUserInfoVO;
import com.farmer.miaosha.VO.UserInfoVO;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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
    UserInfoVO login(String mobile, String password, String mobileCode) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    /**
     * 用户注册
     *
     * @param register 注册信息
     * @return
     */
    UserInfoVO register(RegisterUserInfoVO register) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
