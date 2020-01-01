package com.farmer.miaosha.controller;

import com.farmer.miaosha.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
     * 打印错误信息
     *
     * @param e 异常信息
     */
    private void logError(Exception e) {
        log.info("异常错误信息:{}", ExceptionUtils.getStackTrace(e));
    }

    /**
     * @param exception 异常
     * @Description 处理参数错误异常
     * @date 2019/12/18
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected CommonResponse methodArgumentNotValid(ConstraintViolationException exception) {
        logError(exception);
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
        logError(exception);
        return CommonResponse.METHOD_PARAM_INVALID_ERROR;
    }

    /**
     * @param exception 异常
     * @Description 路由绑定错误
     * @date 2019/12/18
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    protected CommonResponse requestBindingException(ServletRequestBindingException exception) {
        logError(exception);
        return CommonResponse.SERVLET_REQUEST_BINDING_ERROR;
    }
/*
    *//**
     * 404错误
     *//*
    @ExceptionHandler(NoHandlerFoundException.class)
    protected CommonResponse requestBindingException(NoHandlerFoundException exception) {
        logError(exception);
        return CommonResponse.NO_HANDLER_FOUND_ERROR;
    }*/


    /**
     * @param exception 异常
     * @Description 请求参数不完整
     * @date 2019/12/18
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected CommonResponse missingServletRequestParameter(MissingServletRequestParameterException exception) {
        logError(exception);
        return CommonResponse.METHOD_PARAM_MISSING_ERROR;
    }

    /**
     * @param exception 异常
     * @Description 其他异常
     * @date 2019/12/19
     */
    @ExceptionHandler({HttpClientErrorException.class, IOException.class, Exception.class})
    protected CommonResponse commonException(Exception exception) {
        logError(exception);
        return CommonResponse.fail();
    }
}
