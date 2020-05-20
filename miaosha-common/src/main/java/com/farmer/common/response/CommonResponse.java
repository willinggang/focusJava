package com.farmer.common.response;


/**
 * 请求响应类
 *
 * @author Administrator
 * @date 2019年 12月28日 19:50:28
 */

public class CommonResponse<T> {
    /**
     * 响应吗
     */
    private Integer code;

    /**
     * 返回消息内容
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public CommonResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功
     */
    public static CommonResponse success() {
        return new CommonResponse(CommonConstants.Response.SUCCESS_CODE, CommonConstants.Response.SUCCESS_MSG);
    }

    /**
     * 成功
     *
     * @param msg 消息内容
     */
    public static CommonResponse success(String msg) {
        return new CommonResponse(CommonConstants.Response.SUCCESS_CODE, msg);
    }

    /**
     * 成功
     *
     * @param data 数据
     */
    public static CommonResponse success(Object data) {
        return new CommonResponse(CommonConstants.Response.SUCCESS_CODE, CommonConstants.Response.SUCCESS_MSG, data);
    }

    /**
     * 成功
     *
     * @param msg  消息内容
     * @param data 数据
     */
    public static CommonResponse success(String msg, Object data) {
        return new CommonResponse(CommonConstants.Response.SUCCESS_CODE, msg, data);
    }

    /**
     * 失败
     */
    public static CommonResponse fail() {
        return new CommonResponse(CommonConstants.Response.FAILED_CODE, CommonConstants.Response.FAILED_MSG);
    }

    /**
     * 失败
     *
     * @param msg 消息内容
     */
    public static CommonResponse fail(String msg) {
        return new CommonResponse(CommonConstants.Response.FAILED_CODE, msg);
    }

    /**
     * 失败
     *
     * @param code 失败编码
     * @param msg  消息内容
     */
    public static CommonResponse fail(Integer code, String msg) {
        return new CommonResponse(code, msg);
    }

    /**
     * 参数类型不匹配
     */
    public static CommonResponse METHOD_PARAM_MISS_MATCH_ERROR = new CommonResponse(CommonConstants.Response.METHOD_PARAM_MISS_MATCH_ERROR_CODE, CommonConstants.Response.METHOD_PARAM_MISS_MATCH_ERROR_MSG);

    /**
     * 参数不合法
     */
    public static CommonResponse METHOD_PARAM_INVALID_ERROR = new CommonResponse(CommonConstants.Response.METHOD_PARAM_INVALID_ERROR_CODE, CommonConstants.Response.METHOD_PARAM_INVALID_ERROR_MSG);

    /**
     * 参数不合法
     *
     * @param msg 消息内容
     */
    public static CommonResponse METHOD_PARAM_INVALID_ERROR(String msg) {
        return new CommonResponse(CommonConstants.Response.METHOD_PARAM_INVALID_ERROR_CODE, msg);
    }

    /**
     * 参数缺失
     */
    public static CommonResponse METHOD_PARAM_MISSING_ERROR = new CommonResponse(CommonConstants.Response.METHOD_PARAM_MISSING_ERROR_CODE, CommonConstants.Response.METHOD_PARAM_MISSING_ERROR_MSG);
    /**
     * 路由绑定错误
     */
    public static CommonResponse SERVLET_REQUEST_BINDING_ERROR = new CommonResponse(CommonConstants.Response.SERVLET_REQUEST_BINDING_ERROR_CODE, CommonConstants.Response.SERVLET_REQUEST_BINDING_ERROR_MSG);
    /**
     * 404错误
     */
    public static CommonResponse NO_HANDLER_FOUND_ERROR = new CommonResponse(CommonConstants.Response.NO_HANDLER_FOUND_ERROR_CODE, CommonConstants.Response.NO_HANDLER_FOUND_ERROR_MSG);

}
