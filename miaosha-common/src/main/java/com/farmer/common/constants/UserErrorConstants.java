package com.farmer.common.constants;

/**
 * @program: FocusingJava
 * @description: 用户信息处理错误常量
 * @author: FarmerSun
 * @create: 2020-01-02 16:51
 */
public interface UserErrorConstants extends BaseErrorConstants {
    /**
     * 未登录
     */
    Integer NO_AUTHENTICATION_ERROR_CODE = USER_ERROR_BASE_CODE - 1;
    String NO_AUTHENTICATION_ERROR_MSG = "未登录";

    /**
     * 账号不存在
     */
    Integer MOBILE_NO_EXITS_ERROR_CODE = USER_ERROR_BASE_CODE - 2;
    String MOBILE_NO_EXITS_ERROR_MSG = "账号不存在";

    /**
     * 注册账号已经存在
     */
    Integer REGISTER_MOBILE_EXITS_ERROR_CODE = USER_ERROR_BASE_CODE - 3;
    String REGISTER_MOBILE_EXITS_ERROR_MSG = "手机号码已存在";

    /**
     * 账号密码错误
     */
    Integer AUTHENTICATION_PASSWORD_ERROR_CODE = USER_ERROR_BASE_CODE - 4;
    String AUTHENTICATION_PASSWORD_ERROR_MSG = "账号或密码错误";


    /**
     * 账号密码错误
     */
    Integer PASSWORD_NOT_EXITS_ERROR_CODE = USER_ERROR_BASE_CODE - 5;
    String PASSWORD_NOT_EXITS_ERROR_MSG = "密码不存在请使用验证码登录";
}
