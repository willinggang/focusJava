package com.farmer.miaosha.service.impl;

import com.farmer.miaosha.service.SmsService;

/**
 * @program: FocusingJava
 * @description: 消息服务实现类
 * @author: FarmerSun
 * @create: 2020-01-03 16:22
 */
public class SmsServiceImpl implements SmsService {


    @Override
    public Integer sendCode(String mobile, Integer type) {

        return null;
    }

    @Override
    public boolean checkCode(String mobile, String code, Integer type) {
        return false;
    }
}
