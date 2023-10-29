package com.cc.spzx.user.service;

import com.cc.spzx.model.dto.h5.UserLoginDto;
import com.cc.spzx.model.dto.h5.UserRegisterDto;
import com.cc.spzx.model.vo.h5.UserInfoVo;

public interface UserInfoService {

    /**
     * desc 注册用户信息
     * date 2023/10/28
     * @author cc
     * @return
     **/

    void register(UserRegisterDto userRegisterDto);


    /**
     * desc 用户登录
     * date 2023/10/28
     * @author cc
     * @return String
     **/
    String login(UserLoginDto userLoginDto);

    /**
     * desc 获取登录用户信息
     * date 2023/10/28
     * @author cc
     * @return UserInfoVo
     **/

    UserInfoVo getCurrentUserInfo(String token);
}
