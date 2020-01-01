package com.farmer.miaosha.dao;

import com.farmer.miaosha.DO.ItemDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ItemDOMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入条目
     * @param record 记录
     * @return
     */
    int insert(ItemDO record);

    int insertSelective(ItemDO record);

    ItemDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemDO record);

    int updateByPrimaryKey(ItemDO record);
}