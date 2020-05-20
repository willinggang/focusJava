package com.farmer.miaosha.controller;

import com.farmer.common.exception.MobileCodeEnum;
import com.farmer.common.response.CommonResponse;
import com.farmer.miaosha.service.SmsService;
import com.farmer.miaosha.validation.MobileValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: FocusingJava
 * @description: 短信服务接口类
 * @author: FarmerSun
 * @create: 2020-01-02 16:25
 */
@Slf4j
@Api(tags = "短信服务")
@Validated
@RestController
@RequestMapping("sms")
public class SmsController {

    @Resource
    private SmsService smsService;

    @ApiOperation("发送验证码")
    @PostMapping("code/send")
    public CommonResponse sendMobileCode(@RequestParam("mobile") @ApiParam("手机号码") @MobileValidation String mobile) {
        return smsService.sendCode(mobile, MobileCodeEnum.CODE_LOGIN.getType())>0?CommonResponse.success("发送成功"):CommonResponse.fail("发送失败");
    }

    @ApiOperation("验证手机验证码")
    @PostMapping("code/check")
    public CommonResponse checkCode(@RequestParam("mobile")@ApiParam("手机号码")@MobileValidation String mobile,
                                    @RequestParam("code")@ApiParam("手机验证码")@Length(min = 6, max = 6, message = "请输入六位手机验证码") String mobileCode){
        return smsService.checkCode(mobile,mobileCode,MobileCodeEnum.CODE_LOGIN.getType())?CommonResponse.success("验证通过"):CommonResponse.fail("验证码错误");
    }
}
