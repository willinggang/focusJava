package com.farmer.miaosha.service.impl;

import com.farmer.common.constants.ItemErrorConstants;
import com.farmer.common.exception.CustomException;
import com.farmer.miaosha.DO.ItemDO;
import com.farmer.miaosha.VO.ItemVO;
import com.farmer.miaosha.dao.ItemDOMapper;
import com.farmer.miaosha.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: FocusingJava
 * @description: 获取商品信息服务
 * @author: FarmerSun
 * @create: 2020-01-06 15:01
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemDOMapper itemDOMapper;


    @Override
    public ItemVO getItem(Integer itemId) {
        ItemDO itemDO = itemDOMapper.selectByItemId(itemId);
        if (itemDO == null) {
            throw new CustomException(ItemErrorConstants.ITEM_NOT_EXITS_ERROR_CODE, ItemErrorConstants.ITEM_NOT_EXITS_ERROR_MSG);
        }
        return itemDO.getItemModel().getItemVO();
    }

    @Override
    public List<ItemVO> getAllItems() {
        List<ItemDO> itemDOS = itemDOMapper.selectAllItems();
        List<ItemVO> itemVOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(itemDOS)) {
            itemDOS.forEach(itemDO -> {
                ItemVO vo = new ItemVO();
                BeanUtils.copyProperties(itemDO, vo);
                itemVOList.add(vo);
            });
        }
        return itemVOList;
    }
}
