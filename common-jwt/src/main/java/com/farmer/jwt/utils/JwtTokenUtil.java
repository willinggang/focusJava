package com.farmer.jwt.utils;

import com.auth0.jwt.JWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.awt.*;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: FocusingJava
 * @description: JWT Token 工具类
 * @author: FarmerSun
 * @create: 2019-12-31 22:00
 */
@Slf4j
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    /**
     * 秘钥
     */
    private String secret = "5JWwyak7NzsXgL9YYc0GJDBDoTu00sd60p73FCX9InLMLFM068";
    /**
     * 超时时长
     */
    private long expiration = 30L;

    Key key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());


    /**
     * @param claims
     * @return java.lang.String
     * @Description 生成token
     * @date 2019/12/31
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * @param
     * @return java.util.Date
     * @Description 生成过期时间
     * @date 2019/12/31
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * @param token
     * @return io.jsonwebtoken.Claims
     * @Description 解析token
     * @date 2019/12/31
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("解析Jws中token的Claim是错误:{}", ExceptionUtils.getStackTrace(e));
        }
        return claims;
    }

    /**
     * @param token 用户token
     * @return java.lang.String
     * @Description 从token中获取用户ID
     * @date 2019/12/31
     */
    public String getUserIdFromToken(String token) {
        String userId = null;
        try {
            Claims claims = getClaimsFromToken(token);
            userId = claims.getSubject();
            log.info("解析{}获取的UserId为{}", token, userId);
        } catch (Exception e) {
            log.error("解析token获取UserId错误:{}", ExceptionUtils.getStackTrace(e));
        }
        return userId;
    }

    /**
     * 根据用户信息生成token
     *
     * @param userId 用户ID
     */
    public String generateToken(String userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userId);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * @param token 用户JWT Token
     * @return boolean
     * @Description 判断token是否过期
     * @date 2019/12/31
     */
    private boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationFromToken(token);
        return expirationDate.before(new Date());
    }

    /**
     * @param token Jwk Token
     * @return java.util.Date
     * @Description 获取token中的过期时间
     * @date 2019/12/31
     */
    private Date getExpirationFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断token是否可以被刷新
     *
     * @param token Jwt Token
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     *
     * @param token Jwt Token
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}
