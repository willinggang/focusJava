package com.farmer.miaosha.validation;

import com.farmer.miaosha.validation.validator.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 手机号码校验注解
 *
 * @author Administrator
 * @date 2019年 12月29日 21:24:20
 */
@Documented
@Constraint(validatedBy = MobileValidator.class)
@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface MobileValidation {
    String message() default "请输入合法的手机号码";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
