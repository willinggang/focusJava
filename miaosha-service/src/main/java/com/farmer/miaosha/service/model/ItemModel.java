package com.farmer.miaosha.service.model;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.farmer.miaosha.DO.ItemDO;
import com.farmer.miaosha.VO.ItemVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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
     * 转换VO
     */
    public ItemVO getItemVO() {
        ItemVO vo = new ItemVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }

    /**
     * 转换DO
     */
    public ItemDO getItemDO() {
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(this, itemDO);
        return itemDO;
    }
}
