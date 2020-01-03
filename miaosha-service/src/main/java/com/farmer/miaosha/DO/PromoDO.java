package com.farmer.miaosha.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoDO {
    private Integer id;

    private String promoName;

    private Date startDate;

    private Integer itemId;

    private Double promoItemPrice;

    private Date endDate;

}