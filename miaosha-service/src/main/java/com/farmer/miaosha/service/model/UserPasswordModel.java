package com.farmer.miaosha.service.model;

import com.farmer.miaosha.DO.UserPasswordDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @program: FocusingJava
 * @description: 服务层账号密码
 * @author: FarmerSun
 * @create: 2020-01-02 17:37
 */
@Data
public class UserPasswordModel {
    private Integer id;

    private String encrptPassword;

    private Integer userId;

    /**
     * 转换数据层实例
     */
    public UserPasswordDO getUserPasswordDO() {
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        BeanUtils.copyProperties(this, userPasswordDO);
        return userPasswordDO;
    }
}
