package com.farmer.miaosha.service.impl;

import com.farmer.common.constants.ItemErrorConstants;
import com.farmer.common.exception.CustomException;
import com.farmer.miaosha.DO.ItemDO;
import com.farmer.miaosha.DO.ItemStockDO;
import com.farmer.miaosha.DO.PromoDO;
import com.farmer.miaosha.VO.OrderVO;
import com.farmer.miaosha.dao.ItemDOMapper;
import com.farmer.miaosha.dao.ItemStockDOMapper;
import com.farmer.miaosha.dao.PromoDOMapper;
import com.farmer.miaosha.service.OrderService;
import com.farmer.miaosha.service.model.OrderModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.Date;

/**
 * @program: FocusingJava
 * @description: 订单服务实现类
 * @author: FarmerSun
 * @create: 2020-01-08 10:24
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private ItemDOMapper itemDOMapper;
    @Resource
    private ItemStockDOMapper itemStockDOMapper;
    @Resource
    private PromoDOMapper promoDOMapper;

    @Override
    @Transactional
    public OrderVO createOrder(Integer userId, Integer itemId, Integer amount, Integer promoId) {

        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);

        ItemDO itemDO = itemDOMapper.selectByItemId(itemId);
        /*商品不存在*/
        if (itemDO == null) {
            throw new CustomException(ItemErrorConstants.ITEM_NOT_EXITS_ERROR_CODE, ItemErrorConstants.ITEM_NOT_EXITS_ERROR_MSG);
        }
        /*更新商品销售数量*/
        itemDO.setSales(itemDO.getSales() + 1);
        int itemRet = itemDOMapper.updateByItemId(itemDO);
        if (itemRet == 0) {
            log.info("{}下单商品{}更新商品销售数量错误", userId, itemId);
        }
        /*设置订单信息*/
        orderModel.setItemId(itemDO.getId());
        orderModel.setItemPrice(itemDO.getPrice());

        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemId);
        /*库存信息不存在或者不足*/
        if (itemStockDO == null || itemStockDO.getStock() - amount < 0) {
            throw new CustomException(ItemErrorConstants.ITEM_STOCK_NO_ENOUGH_ERROR_CODE, ItemErrorConstants.ITEM_STOCK_NO_ENOUGH_ERROR_MSG);
        }
        int stockRet = itemStockDOMapper.decreaseStockByItemId(itemId, amount);
        if (stockRet == 0) {
            log.info("{}下单商品{}减库存失败", userId, itemId);
        }

        PromoDO promoDO = promoDOMapper.selectByPromoId(promoId);
        Date now = new Date();
        /*存在秒杀活动并且当前时间在秒杀活动时间内*/
        if (promoDO != null && now.after(promoDO.getStartDate()) && now.before(promoDO.getEndDate())) {
            orderModel.setPromoId(promoDO.getId());
            orderModel.setOrderPrice(promoDO.getPromoItemPrice() > 0 ? itemDO.getPrice() - promoDO.getPromoItemPrice() : itemDO.getPrice());
        }

        return null;
    }
}
