package com.farmer.miaosha.service;

/**
 * @program: FocusingJava
 * @description: 短信服务
 * @author: FarmerSun
 * @create: 2020-01-02 16:23
 */
public interface SmsService {

    /**
     * 发送验证码
     *
     * @param mobile 手机号码
     * @param type   短信类型
     * @return
     */
    Integer sendCode(String mobile, Integer type);

    /**
     * 校验验证码是否存在
     *
     * @param mobile 手机号码
     * @param code   验证码
     * @param type   验证码类型
     * @return
     */
    boolean checkCode(String mobile, String code, Integer type);
}
