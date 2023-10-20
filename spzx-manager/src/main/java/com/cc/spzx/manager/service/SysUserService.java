package com.cc.spzx.manager.service;

import com.cc.spzx.model.dto.system.LoginDto;
import com.cc.spzx.model.entity.system.SysUser;
import com.cc.spzx.model.vo.system.LoginVo;
import org.springframework.stereotype.Service;

public interface SysUserService {

    /**
     * 根据用户名查询用户数据
     * @return
     */
    public abstract LoginVo login(LoginDto loginDto) ;


    /**
     * 获取用户数据
     * @return
     */
    public SysUser getUserInfo(String token);


    /**
     * desc 注销用户，删除redis中的key
     * date 2023/10/20
     * @author cc
     * @return
     **/

    public void logout(String token);
}
