package com.farmer.miaosha.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息VO
 *
 * @author Administrator
 * @date 2019年 12月29日 20:06:31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {

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
}
