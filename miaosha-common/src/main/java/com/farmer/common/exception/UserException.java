package com.farmer.common.exception;

/**
 * @program: FocusingJava
 * @description: 用户信息操作异常
 * @author: FarmerSun
 * @create: 2020-01-02 16:55
 */
public class UserException extends RuntimeException {
    /**
     * 错误信息
     */
    private String message;
    /**
     * 错误编码
     */
    private Integer code;

    public UserException(String message) {
        super(message);
    }

    public UserException(Integer code, String message) {
        this(message);
        this.code = code;
        this.message = message;
    }
}
