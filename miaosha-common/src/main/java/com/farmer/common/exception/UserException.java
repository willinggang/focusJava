package com.farmer.common.exception;

/**
 * @program: FocusingJava
 * @description: 用户信息操作异常
 * @author: FarmerSun
 * @create: 2020-01-02 16:55
 */
public class UserException extends CustomException {
    public UserException(Integer code, String message) {
        super(code, message);
    }
}
