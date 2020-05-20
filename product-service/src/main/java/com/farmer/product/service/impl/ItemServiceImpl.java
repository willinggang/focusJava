package com.farmer.product.service.impl;

import com.farmer.common.constants.ItemErrorConstants;
import com.farmer.common.exception.CustomException;
import com.farmer.common.response.CommonResponse;
import com.farmer.common.utils.BeanUtils;
import com.farmer.product.DO.ItemDO;
import com.farmer.product.DO.ItemStockDO;
import com.farmer.product.DO.PromoDO;
import com.farmer.product.config.ItemConstants;
import com.farmer.product.dao.ItemDOMapper;
import com.farmer.product.dao.ItemStockDOMapper;
import com.farmer.product.dao.PromoDOMapper;
import com.farmer.product.service.ItemService;
import com.farmer.product.service.model.ItemModel;
import com.farmer.product.vo.ItemAddVo;
import com.farmer.product.vo.ItemShowDetailVo;
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
    public ItemShowDetailVo getItem(Integer itemId) {
        ItemDO itemDO = itemDOMapper.selectByItemId(itemId);
        if (itemDO == null) {
            throw new CustomException(ItemErrorConstants.ITEM_NOT_EXITS_ERROR_CODE, ItemErrorConstants.ITEM_NOT_EXITS_ERROR_MSG);
        }
        ItemModel itemModel = BeanUtils.copy(itemDO, ItemModel.class);
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
        return BeanUtils.copy(itemModel, ItemShowDetailVo.class);
    }

    @Override
    public List<ItemShowDetailVo> getAllItems() {
        List<ItemDO> itemDOS = itemDOMapper.selectAllItems();
        List<ItemShowDetailVo> itemVOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(itemDOS)) {
            itemDOS.forEach(itemDO -> {
                ItemShowDetailVo vo = new ItemShowDetailVo();
                itemVOList.add(BeanUtils.copy(itemDO, ItemShowDetailVo.class));
            });
        }
        return itemVOList;
    }

    @Override
    public Integer decreaseItemStock(Integer itemId, Integer num) {
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemId);
        if (itemStockDO != null && itemStockDO.getStock() >= num) {
            int ret = itemStockDOMapper.decreaseStockByItemId(itemId, num);
            if (ret > 0) {
                return ItemConstants.ITEM_STOCK_DECREASE_SUCCESS;
            }
        } else if (itemStockDO != null && itemStockDO.getStock() < num) {
            throw new CustomException(ItemErrorConstants.ITEM_STOCK_NO_ENOUGH_ERROR_CODE, ItemErrorConstants.ITEM_STOCK_NO_ENOUGH_ERROR_MSG);
        }
        return ItemConstants.ITEM_STOCK_DECREASE_FAILED;
    }

    @Override
    public CommonResponse addItem(ItemAddVo vo) {
        ItemDO itemDO = BeanUtils.copy(vo, ItemDO.class);
        if (itemDO == null) {
            throw new CustomException(ItemErrorConstants.ITEM_ADD_ERROR_CODE, ItemErrorConstants.ITEM_ADD_ERROR_MSG);
        }
        /*添加商品信息*/
        itemDOMapper.insert(itemDO);
        if (itemDO.getId() == null || itemDO.getId() <= 0) {
            throw new CustomException(ItemErrorConstants.ITEM_ADD_ERROR_CODE, ItemErrorConstants.ITEM_ADD_ERROR_MSG);
        }
        ItemStockDO itemStockDO = ItemStockDO.builder()
                .itemId(itemDO.getId())
                .stock(vo.getStock())
                .build();
        /*添加商品库存*/
        itemStockDOMapper.addItemStock(itemStockDO);
        if (itemStockDO.getId() > 0) {
            return CommonResponse.success();
        }
        return CommonResponse.fail();
    }


}
