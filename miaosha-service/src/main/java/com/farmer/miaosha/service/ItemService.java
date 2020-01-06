package com.farmer.miaosha.service;

import com.farmer.miaosha.VO.ItemVO;

import java.util.List;

/**
 * @program: FocusingJava
 * @description: 商品服务
 * @author: FarmerSun
 * @create: 2020-01-06 14:51
 */
public interface ItemService {

    /**
     * 根据商品id获取商品详情
     *
     * @param itemId 商品ID
     * @return
     */
    ItemVO getItem(Integer itemId);

    /**
     * 获取所有商品信息
     *
     * @return
     */
    List<ItemVO> getAllItems();
}
