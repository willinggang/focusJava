package com.farmer.miaosha.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStockDO {
    private Integer id;

    private Integer stock;

    private Integer itemId;
}