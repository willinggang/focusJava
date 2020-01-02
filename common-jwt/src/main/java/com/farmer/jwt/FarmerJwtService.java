package com.farmer.jwt;

import com.farmer.jwt.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: FocusingJava
 * @description: Jwt公用实例
 * @author: FarmerSun
 * @create: 2020-01-02 11:16
 */
@Slf4j
@Service
public class FarmerJwtService {

    @Resource
    private JwtTokenUtil tokenUtil;

    /**
     * @param userId 用户ID
     * @return java.lang.String
     * @Description 获取Jwt生成的token
     * @date 2020/1/2
     */
    public String getJwtToken(String userId) throws RuntimeException {
        if (StringUtils.isEmpty(userId)) {
            throw new RuntimeException("用户ID不能为空");
        }
        String token = tokenUtil.generateToken(userId);
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("用户:" + userId + "生成token失败");
        }
        log.info("用户:{}生成的Token为{}", userId, token);
        return token;
    }
}
