package com.cc.spzx.manager.service;

import com.cc.spzx.model.dto.system.AssignMenuDto;

import java.util.Map;

public interface SysRoleMenuService {

    /**
     * desc 根据角色的id查询出其对应的菜单id，并且需要将系统中所有的菜单数据查询出来
     * date 2023/10/22
     * @author cc
     * @return String
     **/
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    void doAssign(AssignMenuDto assignMenuDto);

}
