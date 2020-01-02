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
     */
    Integer sendCode(String mobile, Integer type);
}
