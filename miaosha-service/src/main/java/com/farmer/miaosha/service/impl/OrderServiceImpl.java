package com.farmer.miaosha.service.impl;

import com.farmer.common.constants.ItemErrorConstants;
import com.farmer.common.constants.OrderErrorConstants;
import com.farmer.common.exception.CustomException;
import com.farmer.miaosha.DO.PromoDO;
import com.farmer.miaosha.DO.SequenceInfoDO;
import com.farmer.miaosha.VO.OrderVO;
import com.farmer.miaosha.dao.*;
import com.farmer.miaosha.service.OrderService;
import com.farmer.miaosha.service.model.OrderModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Resource
    private SequenceInfoDOMapper sequenceInfoDOMapper;
    @Resource
    private OrderInfoDOMapper orderInfoDOMapper;

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
        } else {
            orderModel.setOrderPrice(itemDO.getPrice());
        }

        String orderNo = generateOrderNo();
        orderModel.setId(orderNo);
        int orderInsertRet = orderInfoDOMapper.insert(orderModel.getOrderInfoDO());
        if (orderInsertRet == 0) {
            throw new CustomException(OrderErrorConstants.ORDER_CREATE_ERROR_CODE, OrderErrorConstants.ORDER_CREATE_ERROR_MSG);
        }
        return orderModel.getOrderVO();
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateOrderNo() {
        //订单号有16位
        StringBuilder stringBuilder = new StringBuilder();
        //前8位为时间信息，年月日
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        stringBuilder.append(nowDate);

        //中间6位为自增序列
        //获取当前sequence
        int sequence = 0;
        SequenceInfoDO sequenceDO = sequenceInfoDOMapper.getSequenceByName("order_info");
        sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceInfoDOMapper.updateByPrimaryKeySelective(sequenceDO);
        String sequenceStr = String.valueOf(sequence);
        for (int i = 0; i < 6 - sequenceStr.length(); i++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);
        //最后2位为分库分表位,暂时写死
        stringBuilder.append("00");
        return stringBuilder.toString();
    }
}
