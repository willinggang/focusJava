package com.farmer.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: FocusingJava
 * @description: 添加商品详情
 * @author: FarmerSun
 * @create: 2020-05-20 15:04
 */
@Data
public class ItemAddVo implements Serializable {
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
     * 商品简介图片
     */
    @ApiModelProperty("商品简介图片")
    private String imgUrl;
    /**
     * 库存
     */
    @ApiModelProperty("库存")
    private Integer stock;
}
