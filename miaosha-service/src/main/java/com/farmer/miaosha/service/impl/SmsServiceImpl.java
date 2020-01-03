package com.farmer.miaosha.service.impl;

import com.farmer.common.constants.MobileCodeError;
import com.farmer.common.exception.CustomException;
import com.farmer.common.exception.MobileCodeException;
import com.farmer.miaosha.service.CaffeineCacheService;
import com.farmer.miaosha.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @program: FocusingJava
 * @description: 消息服务实现类
 * @author: FarmerSun
 * @create: 2020-01-03 16:22
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    private CaffeineCacheService cacheService;

    @Override
    public Integer sendCode(String mobile, Integer type) {
        String code = cacheService.getValue(mobile);
        if (StringUtils.isEmpty(code)) {
            code = generateMobileCode();
            cacheService.putValue(mobile, code);
            log.info("手机号码：{}生成验证码:{}", mobile, code);
        } else {
            throw new MobileCodeException(MobileCodeError.CODE_SEND_FREQUENT_ERROR_CODE,
                    MobileCodeError.CODE_SEND_FREQUENT_ERROR_MSG);
        }
        return 1;
    }

    @Override
    public boolean checkCode(String mobile, String code, Integer type) {
        String cacheCode = cacheService.getValue(mobile);
        log.info("手机号码:{}，缓存中的验证码{}，传值验证码{}", mobile, cacheCode, code);
        if (StringUtils.isNotEmpty(cacheCode) && cacheCode.equals(code)) {
            //验证成功删除验证码
            cacheService.evict(null, mobile);
            return true;
        } else {
            throw new CustomException(MobileCodeError.CODE_MISS_MATCH_ERROR_CODE, MobileCodeError.CODE_MISS_MATCH_ERROR_MSG);
        }
    }

    /**
     * 生成六位验证码
     */
    private String generateMobileCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

}
