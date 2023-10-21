package com.cc.spzx.manager.service;

import com.cc.spzx.model.dto.system.LoginDto;
import com.cc.spzx.model.dto.system.SysUserDto;
import com.cc.spzx.model.entity.system.SysUser;
import com.cc.spzx.model.vo.system.LoginVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

public interface SysUserService {

    /**
     * 用户登录成功插入redis
     * @return
     */
    public abstract LoginVo login(LoginDto loginDto) ;


    /**
     * 获取用户数据，此方法已经没用了
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

    /**
     * desc 根据用户名模糊查询
     * date 2023/10/20
     * @author cc
     * @return SysUser>
     **/

    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);

    /**
     * desc 添加用户
     * date 2023/10/20
     * @author cc
     * @return
     **/

    void saveSysUser(SysUser sysUser);

    /**
     * desc 修改用户
     * date 2023/10/20
     * @author cc
     * @return
     **/

    void updateSysUser(SysUser sysUser);

    /**
     * desc 删除用户
     * date 2023/10/20
     * @author cc
     * @return
     **/

    void deleteSysUser(Long userId);

}
