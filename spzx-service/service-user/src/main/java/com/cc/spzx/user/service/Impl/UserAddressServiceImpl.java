package com.cc.spzx.user.service.Impl;

import com.cc.spzx.model.entity.user.UserAddress;

import com.cc.spzx.user.mapper.UserAddressMapper;
import com.cc.spzx.user.service.UserAddressService;
import com.cc.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> findUserAddressList() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        System.out.println("Auth中userInfo的值" + userId);
        return userAddressMapper.findByUserId(userId);
    }
}
