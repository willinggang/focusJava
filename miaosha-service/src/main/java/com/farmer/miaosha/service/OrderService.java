package com.farmer.miaosha.service;

import com.farmer.miaosha.VO.OrderVO;

/**
 * @program: FocusingJava
 * @description: 订单服务
 * @author: FarmerSun
 * @create: 2020-01-07 17:45
 */
public interface OrderService {
    /**
     * 创建订单
     * @param userId 用户ID
     * @param itemId 商品ID
     */
    OrderVO createOrder(Integer userId, Integer itemId);
}
