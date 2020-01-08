package com.farmer.common.constants;

/**
 * @program: FocusingJava
 * @description: 商品错误常量
 * @author: FarmerSun
 * @create: 2020-01-06 15:06
 */
public interface ItemErrorConstants extends BaseErrorConstants {

    Integer ITEM_NOT_EXITS_ERROR_CODE = ITEM_ERROR_BASE_CODE - 1;
    String ITEM_NOT_EXITS_ERROR_MSG = "商品不存在";

    Integer ITEM_STOCK_NO_ENOUGH_ERROR_CODE = ITEM_ERROR_BASE_CODE - 2;
    String ITEM_STOCK_NO_ENOUGH_ERROR_MSG = "商品库存不足";

}
