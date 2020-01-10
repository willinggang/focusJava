package com.farmer.miaosha.dao;

import com.farmer.miaosha.DO.ItemStockDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface ItemStockDOMapper {
    /**
     * 根据ItemId获取库存信息
     *
     * @param itemId 商品ID
     * @return
     */
    ItemStockDO selectByItemId(Integer itemId);

    /**
     * 根据商品ID进行减库存操作
     *
     * @param itemId 商品ID
     * @param amount 数量
     * @return
     */
    int decreaseStockByItemId(@Param("itemId") Integer itemId, @Param("amount") Integer amount);

}