package com.farmer.common.exception;

/**
 * @program: FocusingJava
 * @description: 手机验证码枚举类
 * @author: FarmerSun
 * @create: 2020-01-03 16:35
 */
public enum MobileCodeEnum {
    //登录
    CODE_LOGIN(1, "登录"),
    //注册
    CODE_REGISTER(2, "注册"),
    //修改密码
    CODE_MODIFY_PASSWORD(3, "修改密码");

    private Integer type;
    private String desc;

    MobileCodeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }
}
