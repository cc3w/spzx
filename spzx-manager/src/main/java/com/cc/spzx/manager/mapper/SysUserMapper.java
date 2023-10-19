package com.cc.spzx.manager.mapper;

import com.cc.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {

    public SysUser selectUserInfoByUserName(String userName);
}
