package com.farmer.miaosha.VO;

import com.farmer.miaosha.validation.MobileValidation;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * @program: FocusingJava
 * @description: 注册用户信息VO
 * @author: FarmerSun
 * @create: 2020-01-04 15:04
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserInfoVO {
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String name;
    /**
     * 性别
     */
    @ApiModelProperty("性别:0-男,1-女")
    private Integer gender;
    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;
    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    @MobileValidation
    private String telphone;

    /**
     * 手机验证码
     */
    @Length(max = 6, min = 6)
    @ApiModelProperty("短信验证码")
    private String mobileCode;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 第三方账号ID
     */
    @ApiModelProperty("第三方账号ID")
    private String thirdPartyId;
}
