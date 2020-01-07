package com.farmer.miaosha.dao;

import com.farmer.miaosha.DO.OrderInfoDO;

import java.util.List;

public interface OrderInfoDOMapper {
    int deleteByPrimaryKey(String id);

    /**
     * 插入订单信息
     * @param record 订单信息
     * @@return
     */
    int insert(OrderInfoDO record);

    /**
     * 根据订单号获取订单信息
     *
     * @param id 订单号
     * @return
     */
    OrderInfoDO selectByOderId(String id);

    /**
     * 根据订单ID更新订单信息
     *
     * @param record 订单信息
     * @return
     */
    int updateByOrderId(OrderInfoDO record);

    List<OrderInfoDO> selectOrdersByUserId(Integer userId);

}