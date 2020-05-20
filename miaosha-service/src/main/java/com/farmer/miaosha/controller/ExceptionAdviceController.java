package com.farmer.miaosha.controller;

import com.farmer.common.exception.CustomException;
import com.farmer.miaosha.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

/**
 * 接口参数校验控制类
 *
 * @author Administrator
 * @date 2019年 12月27日 11:43:50
 */
@Slf4j
@ControllerAdvice
@RestController
public class ExceptionAdviceController {
    /**
     *
     *自定义异常
     * @param e 异常信息
     */
    private void logInfoException(Exception e) {
        log.info("{}", ExceptionUtils.getStackTrace(e));
    }

    /**
     *
     *自定义异常
     * @param e 异常信息
     */
    private void logErrorException(Exception e) {
        log.error("{}", ExceptionUtils.getStackTrace(e));
    }

    /**
     * @param exception 异常
     * @Description 处理参数错误异常
     * @date 2019/12/18
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected CommonResponse methodArgumentNotValid(ConstraintViolationException exception) {
        logInfoException(exception);
        String message = exception.getMessage();
        return StringUtils.isEmpty(message)?CommonResponse.METHOD_PARAM_INVALID_ERROR:CommonResponse.METHOD_PARAM_INVALID_ERROR(message);
    }

    /**
     * @param exception 异常
     * @Description 请求参数类型匹配错误
     * @date 2019/12/18
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    protected CommonResponse typeMissMatch(Exception exception) {
        logInfoException(exception);
        return CommonResponse.METHOD_PARAM_INVALID_ERROR;
    }

    /**
     * @param exception 异常
     * @Description 路由绑定错误
     * @date 2019/12/18
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    protected CommonResponse requestBindingException(ServletRequestBindingException exception) {
        logInfoException(exception);
        return CommonResponse.SERVLET_REQUEST_BINDING_ERROR;
    }

    /**
     * @param exception 异常
     * @Description 请求参数不完整
     * @date 2019/12/18
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected CommonResponse missingServletRequestParameter(MissingServletRequestParameterException exception) {
        logInfoException(exception);
        return CommonResponse.METHOD_PARAM_MISSING_ERROR;
    }

    /**
     * @param exception 异常
     * @Description 其他异常
     * @date 2019/12/19
     */
    @ExceptionHandler(CustomException.class)
    protected CommonResponse customException(CustomException exception) {
        logInfoException(exception);
        return CommonResponse.fail(exception.getCode(),exception.getMessage());
    }

    /**
     * @param exception 异常
     * @Description 其他异常
     * @date 2019/12/19
     */
    @ExceptionHandler({HttpClientErrorException.class, IOException.class, Exception.class})
    protected CommonResponse commonException(Exception exception) {
        logErrorException(exception);
        return CommonResponse.fail();
    }
}
