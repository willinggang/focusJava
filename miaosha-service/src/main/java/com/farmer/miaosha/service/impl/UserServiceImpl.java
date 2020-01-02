package com.farmer.miaosha.service.impl;

import com.farmer.miaosha.VO.UserInfoVO;
import com.farmer.miaosha.dao.UserPasswordDOMapper;
import com.farmer.miaosha.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
    private UserPasswordDOMapper userPasswordDao;

    @Override
    public UserInfoVO login(String mobile, String password, String mobileCode) {

        return null;
    }

    @Override
    public Integer register(String mobile, String password, String mobileCode) {
        return null;
    }
}
