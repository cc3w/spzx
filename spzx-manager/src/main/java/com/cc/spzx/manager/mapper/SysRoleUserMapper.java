package com.cc.spzx.manager.mapper;

import com.cc.spzx.model.dto.system.AssignRoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleUserMapper {

    void deleteByUserId(long userId);

    void doAssign(@Param("userId") Long userId, @Param("roleId") Long roleId);

    List<Long> findSysUserRoleByUserId(Long userId);
}
