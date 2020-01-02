package com.farmer.miaosha.dao;

import com.farmer.miaosha.DO.SequenceInfoDO;

public interface SequenceInfoDOMapper {
    int deleteByPrimaryKey(String name);

    int insert(SequenceInfoDO record);

    int insertSelective(SequenceInfoDO record);

    SequenceInfoDO selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(SequenceInfoDO record);

    int updateByPrimaryKey(SequenceInfoDO record);
}