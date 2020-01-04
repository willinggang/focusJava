package com.farmer.miaosha.service.impl;

import com.farmer.common.constants.MobileCodeError;
import com.farmer.common.constants.UserErrorConstants;
import com.farmer.common.exception.CustomException;
import com.farmer.miaosha.DO.UserInfoDO;
import com.farmer.miaosha.DO.UserPasswordDO;
import com.farmer.miaosha.VO.RegisterUserInfoVO;
import com.farmer.miaosha.VO.UserInfoVO;
import com.farmer.miaosha.common.CommonConstants;
import com.farmer.miaosha.common.CommonResponse;
import com.farmer.miaosha.dao.UserInfoDOMapper;
import com.farmer.miaosha.dao.UserPasswordDOMapper;
import com.farmer.miaosha.service.CaffeineCacheService;
import com.farmer.miaosha.service.UserService;
import com.farmer.miaosha.service.model.UserInfoModel;
import com.farmer.miaosha.service.model.UserPasswordModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 用户信息服务实现类
 *
 * @author Administrator
 * @date 2019年 12月27日 14:08:24
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private CaffeineCacheService cacheService;
    @Resource
    private UserPasswordDOMapper userPasswordDao;
    @Resource
    private UserInfoDOMapper userInfoDao;

    @Override
    public UserInfoVO login(String mobile, String password, String mobileCode) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        UserInfoVO userInfoVO = null;
        /*同时传账号密码和短信验证码返回接口错误*/
        if (StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(mobileCode)) {
            throw new CustomException(CommonConstants.Response.FAILED_CODE, "接口错误");
        }
        /*密码登录*/
        if (StringUtils.isNotEmpty(password)) {
            userInfoVO = passwordLogin(mobile, password);
        } else {
            userInfoVO = mobileCodeLogin(mobile, mobileCode);
        }
        userInfoVO.setToken(TokenGeneratorService.generateValue());
        return userInfoVO;
    }

    @Transactional
    @Override
    public UserInfoVO register(RegisterUserInfoVO register) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String telphone = register.getTelphone();
        Integer telephoneCount = userInfoDao.selectTelephoneCount(telphone);
        /*手机号码已经存在不能注册*/
        if (telephoneCount > 0) {
            throw new CustomException(UserErrorConstants.REGISTER_MOBILE_EXITS_ERROR_CODE, UserErrorConstants.REGISTER_MOBILE_EXITS_ERROR_MSG);
        }
        String mobileCode = cacheService.getValue(telphone);
        /*短信验证码不存在*/
        if (StringUtils.isEmpty(mobileCode)) {
            throw new CustomException(MobileCodeError.CODE_NOT_EXITS_ERROR_CODE, MobileCodeError.CODE_NOT_EXITS_ERROR_MSG);
        }
        String registerMobileCode = register.getMobileCode();
        /*短信验证码不存在*/
        if (!mobileCode.equals(registerMobileCode)) {
            throw new CustomException(MobileCodeError.CODE_MISS_MATCH_ERROR_CODE, MobileCodeError.CODE_MISS_MATCH_ERROR_MSG);
        }
        UserInfoModel userInfoModel = UserInfoModel.builder()
                .name(register.getName())
                .gender(new Byte(String.valueOf(register.getGender().intValue())))
                .age(register.getAge())
                .telphone(register.getTelphone())
                .registerMode("byphone")
                .build();
        UserInfoDO infoDO = userInfoModel.getUserInfoDO();
        Integer generateUserId = userInfoDao.insert(infoDO);
        /*插入用户数据失败*/
        if (generateUserId == 0 || infoDO.getId() == null || infoDO.getId() == 0) {
            throw new CustomException(UserErrorConstants.REGISTER_ERROR_CODE, UserErrorConstants.REGISTER_ERROR_MSG);
        }
        UserPasswordModel userPasswordModel = UserPasswordModel.builder()
                .userId(infoDO.getId())
                .encrptPassword(encodeByMd5(register.getPassword()))
                .build();
        int insertPasswordRet = userPasswordDao.insert(userPasswordModel.getUserPasswordDO());
        /*插入密码失败*/
        if (insertPasswordRet <= 0) {
            throw new CustomException(UserErrorConstants.REGISTER_ERROR_CODE, UserErrorConstants.REGISTER_ERROR_MSG);
        }
        UserInfoVO userInfoVO = userInfoModel.getUserInfoVO();
        userInfoVO.setToken(TokenGeneratorService.generateValue());
        return userInfoVO;
    }


    /**
     * @param mobile 手机号码
     * @param code   短信验证码
     * @return com.farmer.miaosha.VO.UserInfoVO
     * @Description 手机号码短信验证码登录
     * @date 2020/1/4
     */
    private UserInfoVO mobileCodeLogin(String mobile, String code) {
        UserInfoDO userInfoDO = userInfoDao.selectByTelephone(mobile);
        if (userInfoDO == null) {
            throw new CustomException(UserErrorConstants.MOBILE_NO_EXITS_ERROR_CODE, UserErrorConstants.MOBILE_NO_EXITS_ERROR_MSG);
        }
        String mobileCode = cacheService.getValue(mobile);
        /*验证码不存在失效*/
        if (StringUtils.isEmpty(mobileCode)) {
            throw new CustomException(MobileCodeError.CODE_NOT_EXITS_ERROR_CODE, MobileCodeError.CODE_NOT_EXITS_ERROR_MSG);
            /*验证码不匹配*/
        } else if (!mobileCode.equals(code)) {
            throw new CustomException(MobileCodeError.CODE_MISS_MATCH_ERROR_CODE, MobileCodeError.CODE_MISS_MATCH_ERROR_MSG);
        } else {
            UserInfoModel userModel = userInfoDO.getUserInfoModel();
            return userModel.getUserInfoVO();
        }
    }

    /**
     * @param mobile   手机号码
     * @param password 密码
     * @return com.farmer.miaosha.VO.UserInfoVO
     * @Description 账号密码登录
     * @date 2020/1/4
     */
    private UserInfoVO passwordLogin(String mobile, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserInfoDO userInfoDO = userInfoDao.selectByTelephone(mobile);
        if (userInfoDO == null) {
            throw new CustomException(UserErrorConstants.MOBILE_NO_EXITS_ERROR_CODE, UserErrorConstants.MOBILE_NO_EXITS_ERROR_MSG);
        }
        UserInfoModel userModel = userInfoDO.getUserInfoModel();
        UserPasswordDO passwordDO = userPasswordDao.selectByUserId(userModel.getId());
        /*密码不存在*/
        if (passwordDO == null) {
            throw new CustomException(UserErrorConstants.PASSWORD_NOT_EXITS_ERROR_CODE, UserErrorConstants.PASSWORD_NOT_EXITS_ERROR_MSG);
        }
        UserPasswordModel passwordModel = passwordDO.getUserPasswordModel();
        if (encodeByMd5(password).equals(passwordModel.getEncrptPassword())) {
            return userModel.getUserInfoVO();
        } else {
            throw new CustomException(UserErrorConstants.AUTHENTICATION_PASSWORD_ERROR_CODE, UserErrorConstants.AUTHENTICATION_PASSWORD_ERROR_MSG);
        }
    }

    /**
     * 密码加密
     *
     * @param password 原始密码
     */
    private String encodeByMd5(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密字符串
        String newstr = base64en.encode(md5.digest(password.getBytes("utf-8")));
        return newstr;
    }
}
