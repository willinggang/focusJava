package com.farmer.miaosha.exception;

/**
 * 用户信息异常
 *
 * @author Administrator
 * @date 2019年 12月28日 21:16:28
 */
public class UserException extends RuntimeException {
    /**
     * 错误代码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;

    public UserException(String message) {
        super(message);
    }

    public UserException(Integer code, String message) {
        this(message);
        this.code = code;
    }


}
