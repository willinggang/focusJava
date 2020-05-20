package com.farmer.product.dao;

import com.farmer.product.DO.ItemDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ItemDOMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入条目
     *
     * @param record 记录
     * @return
     */
    int insert(ItemDO record);

    /**
     * 根据产品ID获取产品详情
     *
     * @param id 产品ID
     * @return 产品详情
     */
    ItemDO selectByItemId(Integer id);

    /**
     * 更新产品信息
     *
     * @param record 产品信息
     * @return 执行结果
     */
    int updateByItemId(ItemDO record);

    /**
     * 获取所有商品信息
     *
     * @return
     */
    List<ItemDO> selectAllItems();
}