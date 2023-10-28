package com.cc.spzx.user.service;

import com.cc.spzx.model.dto.h5.UserRegisterDto;

public interface UserInfoService {

    /**
     * desc 注册用户信息
     * date 2023/10/28
     * @author cc
     * @return
     **/

    void register(UserRegisterDto userRegisterDto);
}
