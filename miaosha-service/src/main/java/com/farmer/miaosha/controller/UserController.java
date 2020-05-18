package com.farmer.miaosha.controller;

import com.farmer.miaosha.VO.RegisterUserInfoVO;
import com.farmer.miaosha.VO.UserInfoVO;
import com.farmer.miaosha.common.CommonResponse;
import com.farmer.miaosha.service.UserService;
import com.farmer.miaosha.validation.MobileValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 用户数据控制类
 *
 * @author Administrator
 * @date 2019年 12月29日 19:57:35
 */
@Api(tags = "用户信息")
@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;
    @ApiOperation("用户登录")
    @PostMapping("login")
    @ResponseBody
    public CommonResponse<UserInfoVO> login(@RequestParam(value = "mobile", required = true) @ApiParam("账号")  @MobileValidation String mobile,
                                            @RequestParam(value = "password", required = false) @ApiParam("密码") @Length(min = 6, max = 6, message = "请输入六位密码") String password,
                                            @RequestParam(value = "mobileCode", required = false) @ApiParam("手机验证码") @Length(min = 6, max = 6, message = "请输入六位手机验证码") String mobileCode) {

        try {
            UserInfoVO infoVO = userService.login(mobile, password, mobileCode);
            return CommonResponse.success(infoVO);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error("登录接口错误:{}", ExceptionUtils.getStackTrace(e));
        }
        return CommonResponse.fail("系统错误登录失败");
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    public CommonResponse register(@RequestBody @Valid RegisterUserInfoVO register){
        try {
            UserInfoVO infoVO = userService.register(register);
            return CommonResponse.success(infoVO);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            log.error("用户注册错误:{}",ExceptionUtils.getStackTrace(e));
        }
        return CommonResponse.fail("系统错误注册失败");
    }
}
