package com.farmer.miaosha.common;

/**
 * 请求返回常量
 *
 * @author Administrator
 * @date 2019年 12月28日 20:01:18
 */
public interface CommonConstants {
    /**
     * 请求常量
     */
    interface Response {
        /*请求成功*/
        Integer SUCCESS_CODE = 0;
        String SUCCESS_MSG = "成功";

        /*请求失败*/
        Integer FAILED_CODE = -1;
        String FAILED_MSG = "操作失败";

        /*接口参数类型不匹配*/
        Integer METHOD_PARAM_MISS_MATCH_ERROR_CODE = -10001;
        String METHOD_PARAM_MISS_MATCH_ERROR_MSG = "参数类型不匹配";

        /*参数不合法*/
        Integer METHOD_PARAM_INVALID_ERROR_CODE = -10002;
        String METHOD_PARAM_INVALID_ERROR_MSG = "参数不合法";

        /*参数确实*/
        Integer METHOD_PARAM_MISSING_ERROR_CODE = -10003;
        String METHOD_PARAM_MISSING_ERROR_MSG = "参数确实";

        /*路由绑定错误*/
        Integer SERVLET_REQUEST_BINDING_ERROR_CODE = -10004;
        String SERVLET_REQUEST_BINDING_ERROR_MSG = "URL路由绑定错误";
        /*访问路径不存在*/
        Integer NO_HANDLER_FOUND_ERROR_CODE = -10005;
        String NO_HANDLER_FOUND_ERROR_MSG = "访问路径不存在";
    }

    /**
     * 用户错误
     */
    interface UserError {
        /*未登录*/
        Integer NO_AUTHENTICATION_ERROR_CODE = -20001;
        String NO_AUTHENTICATION_ERROR_MSG = "未登录";

        /*账号不存在*/
        Integer MOBILE_NO_EXITS_ERROR_CODE = -20002;
        String MOBILE_NO_EXITS_ERROR_MSG = "账号不存在";

        /*注册账号已经存在*/
        Integer REGISTER_MOBILE_EXITS_ERROR_CODE = -20003;
        String REGISTER_MOBILE_EXITS_ERROR_MSG = "手机号码已存在";

        /*账号密码错误*/
        Integer AUTHENTICATION_PASSWORD_ERROR_CODE = -20004;
        String AUTHENTICATION_PASSWORD_ERROR_MSG = "账号或密码错误";
    }


}
