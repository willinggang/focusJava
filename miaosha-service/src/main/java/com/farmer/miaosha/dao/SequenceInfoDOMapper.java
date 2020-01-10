package com.farmer.miaosha.dao;

import com.farmer.miaosha.DO.SequenceInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SequenceInfoDOMapper {

    SequenceInfoDO getSequenceByName(String name);

    int updateByPrimaryKeySelective(SequenceInfoDO record);

}