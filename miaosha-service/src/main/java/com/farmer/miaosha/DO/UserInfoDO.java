package com.farmer.miaosha.DO;

import com.farmer.miaosha.service.model.UserInfoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDO {
    private Integer id;

    private String name;

    private Byte gender;

    private Integer age;

    private String telphone;

    private String registerMode;

    private String thirdPartyId;

    public UserInfoModel getUserInfoModel() {
        UserInfoModel model = new UserInfoModel();
        BeanUtils.copyProperties(this, model);
        return model;
    }

}