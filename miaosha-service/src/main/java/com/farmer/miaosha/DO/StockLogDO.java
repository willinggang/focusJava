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
public class StockLogDO {
    private String stockLogId;

    private Integer itemId;

    private Integer amount;

    private Integer status;

}