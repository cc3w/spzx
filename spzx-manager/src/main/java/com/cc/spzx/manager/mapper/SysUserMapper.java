package com.cc.spzx.manager.mapper;

import com.cc.spzx.model.dto.system.SysUserDto;
import com.cc.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {

    public SysUser selectUserInfoByUserName(String userName);

    List<SysUser> findByPage(SysUserDto sysUserDto);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    //当只有一个参数时，userId会隐式的映射到id
    void deleteSysUser(Long userId);

}
