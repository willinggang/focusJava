package com.farmer.miaosha.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: FocusingJava
 * @description: 订单信息
 * @author: FarmerSun
 * @create: 2020-01-07 17:46
 */
@Data
@NoArgsConstructor
public class OrderVO {
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
