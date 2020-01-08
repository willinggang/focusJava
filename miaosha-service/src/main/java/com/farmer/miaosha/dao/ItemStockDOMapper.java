package com.farmer.miaosha.dao;

import com.farmer.miaosha.DO.ItemStockDO;

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
     * @return
     */
    int decreaseStockByItemId(Integer itemId);

}