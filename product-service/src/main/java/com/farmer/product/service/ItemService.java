package com.farmer.product.service;

import com.farmer.common.response.CommonResponse;
import com.farmer.product.vo.ItemAddVo;
import com.farmer.product.vo.ItemShowDetailVo;

import java.util.List;

/**
 * @program: focusJava
 * @description: 服务
 * @author: FarmerSun
 * @create: 2020-05-18 20:40
 */

public interface ItemService {
    /**
     * 根据商品id获取商品详情
     *
     * @param itemId 商品ID
     * @return
     */
    ItemShowDetailVo getItem(Integer itemId);

    /**
     * 获取所有商品信息
     *
     * @return
     */
    List<ItemShowDetailVo> getAllItems();

    /**
     * 减商品库存
     *
     * @param itemId 商品id
     * @param num    产品数量
     * @return -1-库存不足,0-失败，1-成功
     */
    Integer decreaseItemStock(Integer itemId, Integer num);

    /**
     * 添加商品
     *
     * @param vo 商品详情
     */
    CommonResponse addItem(ItemAddVo vo);
}
