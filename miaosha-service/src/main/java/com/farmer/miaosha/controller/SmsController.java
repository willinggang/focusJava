package com.farmer.miaosha.controller;

import com.farmer.miaosha.common.CommonResponse;
import com.farmer.miaosha.service.SmsService;
import com.farmer.miaosha.validation.MobileValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
        return smsService.sendCode(mobile,0)>0?CommonResponse.success("发送成功"):CommonResponse.fail("发送失败");
    }
}
