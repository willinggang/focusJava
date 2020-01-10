package com.farmer.miaosha.dao;

import com.farmer.miaosha.DO.SequenceInfoDO;

public interface SequenceInfoDOMapper {

    SequenceInfoDO getSequenceByName(String name);

    int updateByPrimaryKeySelective(SequenceInfoDO record);

}