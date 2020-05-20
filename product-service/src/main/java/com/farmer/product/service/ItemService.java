package com.farmer.product.service;

import com.farmer.product.vo.ItemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: focusJava
 * @description: 服务
 * @author: FarmerSun
 * @create: 2020-05-18 20:40
 */

public interface  ItemService {
    /**
     * 根据商品id获取商品详情
     *
     * @param itemId 商品ID
     * @return
     */
    ItemVo getItem(Integer itemId);

    /**
     * 获取所有商品信息
     *
     * @return
     */
    List<ItemVo> getAllItems();
}
