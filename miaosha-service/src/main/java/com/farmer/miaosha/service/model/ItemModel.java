package com.farmer.miaosha.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @program: FocusingJava
 * @description: 商品Model
 * @author: FarmerSun
 * @create: 2020-01-06 11:45
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemModel {
    private Integer id;

    private String title;

    private Double price;

    private String description;

    private Integer sales;

    private String imgUrl;

    /**
     * 库存
     */
    private Integer stock;
    /**
     * 秒杀活动名称
     */
    private String promoName;
    /**
     * 秒杀开始时间
     */
    private Date startDate;
    /**
     * 秒杀活动价格
     */
    private Double promoItemPrice;
    /**
     * 秒杀活动结束时间
     */
    private Date endDate;

    /**
     * 转换VO
     */
    public ItemVO getItemVO() {
        ItemVO vo = new ItemVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }

}
