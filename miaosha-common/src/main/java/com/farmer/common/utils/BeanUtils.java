package com.farmer.common.utils;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.MappingException;

/**
 * @program: FocusingJava
 * @description: Bean拷贝工具类
 * @author: FarmerSun
 * @create: 2020-05-18 10:34
 */
public class BeanUtils {
    private static DozerBeanMapper dozer = (DozerBeanMapper) DozerBeanMapperBuilder.buildDefault();

    public static void copy(Object sourceObj, Object targetObj) {
        dozer.map(sourceObj, targetObj);
    }

    public static  <T> T copy(Object source, Class<T> targetClass) throws MappingException {
        return dozer.map(source, targetClass);
    }
}
