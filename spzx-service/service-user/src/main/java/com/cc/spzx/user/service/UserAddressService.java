package com.cc.spzx.user.service;

import com.cc.spzx.model.entity.user.UserAddress;

import java.util.List;

public interface UserAddressService {

    /**
     * desc 通过用户id获取用户地址
     * date 2023/10/29
     * @author cc
     * @return UserAddress>
     **/
    List<UserAddress> findUserAddressList();

}
