package com.farmer.product.service.impl;

import com.farmer.common.constants.ItemErrorConstants;
import com.farmer.common.exception.CustomException;
import com.farmer.common.utils.BeanUtils;
import com.farmer.product.DO.ItemDO;
import com.farmer.product.DO.ItemStockDO;
import com.farmer.product.DO.PromoDO;
import com.farmer.product.dao.ItemDOMapper;
import com.farmer.product.dao.ItemStockDOMapper;
import com.farmer.product.dao.PromoDOMapper;
import com.farmer.product.service.ItemService;
import com.farmer.product.service.model.ItemModel;
import com.farmer.product.vo.ItemVo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: focusJava
 * @description: 商品服务实现类
 * @author: FarmerSun
 * @create: 2020-05-18 21:07
 */
@Slf4j
@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    private ItemDOMapper itemDOMapper;

    @Resource
    private ItemStockDOMapper itemStockDOMapper;

    @Resource
    private PromoDOMapper promoDOMapper;

    @Override
    public ItemVo getItem(Integer itemId) {
        ItemDO itemDO = itemDOMapper.selectByItemId(itemId);
        if (itemDO == null) {
            throw new CustomException(ItemErrorConstants.ITEM_NOT_EXITS_ERROR_CODE, ItemErrorConstants.ITEM_NOT_EXITS_ERROR_MSG);
        }
        ItemModel itemModel= BeanUtils.copy(itemDO,ItemModel.class);
        /*获取库存信息*/
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemId);
        if (itemStockDO == null || itemStockDO.getStock() <= 0) {
            itemModel.setStock(0);
        } else {
            itemModel.setStock(itemStockDO.getStock());
        }
        /*获取秒杀活动信息*/
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);
        if (promoDO != null) {
            itemModel.setPromoName(promoDO.getPromoName());
            itemModel.setPromoItemPrice(promoDO.getPromoItemPrice());
            itemModel.setStartDate(promoDO.getStartDate());
            itemModel.setEndDate(promoDO.getEndDate());
        }
        return BeanUtils.copy(itemModel,ItemVo.class);
    }

    @Override
    public List<ItemVo> getAllItems() {
        List<ItemDO> itemDOS = itemDOMapper.selectAllItems();
        List<ItemVo> itemVOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(itemDOS)) {
            itemDOS.forEach(itemDO -> {
                ItemVo vo = new ItemVo();
                itemVOList.add(BeanUtils.copy(itemDO,ItemVo.class));
            });
        }
        return itemVOList;
    }
}
