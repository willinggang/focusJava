package com.farmer.miaosha.DO;

import com.farmer.miaosha.service.model.UserPasswordModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordDO {
    private Integer id;

    private String encrptPassword;

    private Integer userId;

    public UserPasswordModel getUserPasswordModel() {
        UserPasswordModel model = new UserPasswordModel();
        BeanUtils.copyProperties(this, model);
        return model;
    }
}