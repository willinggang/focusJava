package com.farmer.common.exception;

/**
 * @program: FocusingJava
 * @description: 自定义异常父类
 * @author: FarmerSun
 * @create: 2020-01-03 21:30
 */
public class CustomException extends RuntimeException {

    private Integer code;
    private String message;

    private CustomException(String message) {
        super(message);
    }

    public CustomException(Integer code, String message) {
        this(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
