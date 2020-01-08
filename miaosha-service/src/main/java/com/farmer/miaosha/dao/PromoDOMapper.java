package com.farmer.miaosha.dao;

import com.farmer.miaosha.DO.PromoDO;

public interface PromoDOMapper {

    /**
     * 根据秒杀活动ID获取秒杀活动信息
     *
     * @param promoId 秒杀活动ID
     * @return
     */
    PromoDO selectByPromoId(Integer promoId);

    /**
     * 根据商品ID获取秒杀活动
     *
     * @param itemId 商品ID
     * @return
     */
    PromoDO selectByItemId(Integer itemId);

}