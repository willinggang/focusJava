package com.farmer.common.exception;

/**
 * @program: FocusingJava
 * @description: 验证码异常
 * @author: FarmerSun
 * @create: 2020-01-03 21:05
 */
public class MobileCodeException extends CustomException {

    public MobileCodeException(Integer code, String message) {
        super(code,message);
    }
}
