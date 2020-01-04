package com.farmer.miaosha.service.impl;

import com.farmer.common.constants.MobileCodeError;
import com.farmer.common.constants.UserErrorConstants;
import com.farmer.common.exception.CustomException;
import com.farmer.miaosha.DO.UserInfoDO;
import com.farmer.miaosha.DO.UserPasswordDO;
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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public UserInfoVO login(String mobile, String password, String mobileCode) {
        /*同时传账号密码和短信验证码返回接口错误*/
        if (StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(mobileCode)) {
            throw new CustomException(CommonConstants.Response.FAILED_CODE, "接口错误");
        }
        /*密码登录*/
        if (StringUtils.isNotEmpty(password)) {
            return passwordLogin(mobile, password);
        } else {
            return mobileCodeLogin(mobile, mobileCode);
        }
    }


    @Override
    public Integer register(String mobile, String password, String mobileCode) {
        return null;
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
            throw new CustomException(MobileCodeError.CODE_TIME_OUT_ERROR_CODE, MobileCodeError.CODE_TIME_OUT_ERROR_MSG);
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
    private UserInfoVO passwordLogin(String mobile, String password) {
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
        if (password.equals(passwordModel.getEncrptPassword())) {
            return userModel.getUserInfoVO();
        } else {
            throw new CustomException(UserErrorConstants.AUTHENTICATION_PASSWORD_ERROR_CODE, UserErrorConstants.AUTHENTICATION_PASSWORD_ERROR_MSG);
        }
    }
}
