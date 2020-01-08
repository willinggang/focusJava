package com.farmer.miaosha.service.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: FocusingJava
 * @description: 订单Model
 * @author: FarmerSun
 * @create: 2020-01-08 10:27
 */
@Data
@NoArgsConstructor
public class OrderModel {
    private String id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 商品ID
     */
    private Integer itemId;
    /**
     * 商品原始价格
     */
    private Double itemPrice;
    /**
     * 数量
     */
    private Integer amount;
    /**
     * 下单价格
     */
    private Double orderPrice;
    /**
     * 秒杀活动ID
     */
    private Integer promoId;
}
