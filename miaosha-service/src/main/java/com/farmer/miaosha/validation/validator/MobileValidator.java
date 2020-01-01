package com.farmer.miaosha.validation.validator;

import com.farmer.miaosha.validation.MobileValidation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号码注解实现类
 *
 * @author Administrator
 * @date 2019年 12月29日 21:26:38
 */

@Component
public class MobileValidator implements ConstraintValidator<MobileValidation, String> {
    private static final String MOBILE_REGX = "/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isNotEmpty(mobile)) {

            return Pattern.matches(MOBILE_REGX, mobile);
        }
        return false;
    }
}
