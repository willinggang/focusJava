package com.farmer.miaosha.DO;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class ItemDO {
    private Integer id;

    private String title;

    private Double price;

    private String description;

    private Integer sales;

    private String imgUrl;

}