package com.farmer.miaosha.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: focusJava
 * @description: 配置服务更新
 * @author: FarmerSun
 * @create: 2020-05-17 10:34
 */
@Slf4j
@RefreshScope
@RequestMapping("/config")
@RestController
public class ConfigRefreshController {
    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @RequestMapping("/get")
    public boolean get() {
        return useLocalCache;
    }
}
