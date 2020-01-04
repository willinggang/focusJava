package com.farmer.common.constants;

/**
 * @program: FocusingJava
 * @description: 验证码错误
 * @author: FarmerSun
 * @create: 2020-01-03 21:06
 */
public interface MobileCodeError extends BaseErrorConstants {

    /**
     * 验证码发送失败
     */
    Integer CODE_SEND_ERROR_CODE = MOBILE_CODE_BASE_CODE - 1;
    String CODE_SEND_ERROR_MSG = "发送验证码失败";
    /**
     * 验证码错误
     */
    Integer CODE_MISS_MATCH_ERROR_CODE = MOBILE_CODE_BASE_CODE - 2;
    String CODE_MISS_MATCH_ERROR_MSG = "验证码错误";

    /**
     * 验证码当日发送次数超出最大发送数量
     */
    Integer CODE_SEND_MAX_COUNT_ERROR_CODE = MOBILE_CODE_BASE_CODE - 3;
    String CODE_SEND_MAX_COUNT_ERROR_MSG = "验证码已达当日最大次数";

    /**
     * 验证码发送频繁
     */
    Integer CODE_SEND_FREQUENT_ERROR_CODE = MOBILE_CODE_BASE_CODE - 4;
    String CODE_SEND_FREQUENT_ERROR_MSG = "验证码发送过于频繁，请稍后再试";

    /**
     * 验证码失效
     */
    Integer CODE_TIME_OUT_ERROR_CODE = MOBILE_CODE_BASE_CODE - 5;
    String CODE_TIME_OUT_ERROR_MSG = "验证码失效";

}
