package com.farmer.product.dao;

import com.farmer.product.DO.PromoDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
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