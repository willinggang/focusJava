package com.farmer.miaosha.controller;

import com.farmer.miaosha.common.CommonResponse;
import com.farmer.miaosha.service.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

/**
 * @program: FocusingJava
 * @description: 商品信息接口类
 * @author: FarmerSun
 * @create: 2020-01-06 11:25
 */
@Slf4j
@RestController
@Validated
@RequestMapping("item")
public class ItemController {

    @Resource
    private ItemService itemService;

    @ApiOperation("根据商品ID获取商品详情")
    @GetMapping("getItem")
    public CommonResponse getItem(@RequestParam("itemId") @ApiParam("商品ID") @Min(1) Integer itemId) {
        return CommonResponse.success(itemService.getItem(itemId));
    }

    @ApiOperation("获取所有商品数据")
    @GetMapping("getAllItems")
    public CommonResponse getAllItems() {
        return CommonResponse.success(itemService.getAllItems());
    }

}
