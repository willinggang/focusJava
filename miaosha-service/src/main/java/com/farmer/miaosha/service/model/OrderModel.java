package com.farmer.miaosha.service.model;

import com.farmer.miaosha.DO.OrderInfoDO;
import com.farmer.miaosha.VO.OrderVO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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

    public OrderInfoDO getOrderInfoDO(){
        OrderInfoDO infoDO = new OrderInfoDO();
        BeanUtils.copyProperties(this,infoDO);
        return infoDO;
    }

    public OrderVO getOrderVO(){
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(this,orderVO);
        return orderVO;
    }
}
