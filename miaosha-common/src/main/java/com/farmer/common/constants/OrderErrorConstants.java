package com.farmer.common.constants;

/**
 * @program: FocusingJava
 * @description: 订单错误常量
 * @author: FarmerSun
 * @create: 2020-01-10 14:31
 */
public interface OrderErrorConstants extends BaseErrorConstants {

    Integer ORDER_CREATE_ERROR_CODE=ORDER_ERROR_BASE_CODE-1;
    String ORDER_CREATE_ERROR_MSG="订单创建失败";
}
