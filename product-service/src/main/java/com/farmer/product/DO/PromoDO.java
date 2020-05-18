package com.farmer.product.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

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