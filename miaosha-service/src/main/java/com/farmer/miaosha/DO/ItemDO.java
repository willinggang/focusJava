package com.farmer.miaosha.DO;

import com.farmer.miaosha.service.model.ItemModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDO {
    private Integer id;

    private String title;

    private Double price;

    private String description;

    private Integer sales;

    private String imgUrl;

    public ItemModel getItemModel() {
        ItemModel model = new ItemModel();
        BeanUtils.copyProperties(this, model);
        return model;
    }

}