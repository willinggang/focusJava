package com.farmer.miaosha.dao;

import com.farmer.miaosha.DO.OrderInfoDO;

public interface OrderInfoDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderInfoDO record);

    int insertSelective(OrderInfoDO record);

    OrderInfoDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderInfoDO record);

    int updateByPrimaryKey(OrderInfoDO record);
}