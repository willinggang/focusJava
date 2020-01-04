package com.farmer.miaosha.service.model;

import com.farmer.miaosha.DO.UserInfoDO;
import com.farmer.miaosha.VO.UserInfoVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @program: FocusingJava
 * @description: 用户数据信息
 * @author: FarmerSun
 * @create: 2020-01-03 16:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoModel {
    private Integer id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Byte gender;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 手机号码
     */
    private String telphone;
    /**
     * 注册类型
     */
    private String registerMode;
    /**
     * 第三方ID
     */
    private String thirdPartyId;

    /**转换数据层*/
    public UserInfoDO getUserInfoDO() {
        UserInfoDO infoDO = new UserInfoDO();
        BeanUtils.copyProperties(this, infoDO);
        return infoDO;
    }

    /**转换ViewObject*/
    public UserInfoVO getUserInfoVO() {
        UserInfoVO infoVO = new UserInfoVO();
        BeanUtils.copyProperties(this, infoVO);
        return infoVO;
    }
}
