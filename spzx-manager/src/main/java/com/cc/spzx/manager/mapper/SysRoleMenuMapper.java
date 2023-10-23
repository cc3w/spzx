package com.cc.spzx.manager.mapper;

import com.cc.spzx.model.dto.system.AssignMenuDto;
import com.cc.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper {

    List<Long> findSysRoleMenuByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);

    void doAssign(AssignMenuDto assignMenuDto);

    void updateSysRoleMenuIsHalf(Long menuId);

}
