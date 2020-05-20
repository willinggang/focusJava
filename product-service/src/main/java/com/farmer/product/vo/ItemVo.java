package com.farmer.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: focusJava
 * @description: 商品Vo
 * @author: FarmerSun
 * @create: 2020-05-18 21:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemVo {
    /**
     * 商品ID
     */
    @ApiModelProperty("商品ID")
    private Integer id;
    /**
     * 商品标题
     */
    @ApiModelProperty("商品标题")
    private String title;
    /**
     * 商品价格
     */
    @ApiModelProperty("商品价格")
    private Double price;
    /**
     * 商品描述
     */
    @ApiModelProperty("商品描述")
    private String description;
    /**
     * 商品销售数量
     */
    @ApiModelProperty("商品销售数量")
    private Integer sales;
    /**
     * 商品简介图片
     */
    @ApiModelProperty("商品简介图片")
    private String imgUrl;

    /**
     * 库存
     */
    @ApiModelProperty("库存")
    private Integer stock;
    /**
     * 秒杀活动名称
     */
    @ApiModelProperty("秒杀活动名称")
    private String promoName;
    /**
     * 秒杀开始时间
     */
    @ApiModelProperty("秒杀活动开始时间")
    private Date startDate;
    /**
     * 秒杀活动价格
     */
    @ApiModelProperty("秒杀活动价格")
    private Double promoItemPrice;
    /**
     * 秒杀活动结束时间
     */
    @ApiModelProperty("秒杀活动结束时间")
    private Date endDate;
}
