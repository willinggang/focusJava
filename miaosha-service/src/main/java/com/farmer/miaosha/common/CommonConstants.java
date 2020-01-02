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
        Integer METHOD_PARAM_MISS_MATCH_ERROR_CODE = -1001;
        String METHOD_PARAM_MISS_MATCH_ERROR_MSG = "参数类型不匹配";

        /*参数不合法*/
        Integer METHOD_PARAM_INVALID_ERROR_CODE = -1002;
        String METHOD_PARAM_INVALID_ERROR_MSG = "参数不合法";

        /*参数确实*/
        Integer METHOD_PARAM_MISSING_ERROR_CODE = -1003;
        String METHOD_PARAM_MISSING_ERROR_MSG = "参数确实";

        /*路由绑定错误*/
        Integer SERVLET_REQUEST_BINDING_ERROR_CODE = -1004;
        String SERVLET_REQUEST_BINDING_ERROR_MSG = "URL路由绑定错误";
        /*访问路径不存在*/
        Integer NO_HANDLER_FOUND_ERROR_CODE = -1005;
        String NO_HANDLER_FOUND_ERROR_MSG = "访问路径不存在";
    }

}
