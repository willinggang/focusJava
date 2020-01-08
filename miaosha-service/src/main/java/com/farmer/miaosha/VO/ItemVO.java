package com.farmer.miaosha.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: FocusingJava
 * @description: 商品信息ViewObject
 * @author: FarmerSun
 * @create: 2020-01-06 11:41
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemVO {
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
