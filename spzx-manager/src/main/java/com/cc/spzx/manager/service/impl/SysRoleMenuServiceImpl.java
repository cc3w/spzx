package com.cc.spzx.manager.service.impl;


import com.cc.spzx.manager.mapper.SysRoleMenuMapper;
import com.cc.spzx.manager.service.SysMenuService;
import com.cc.spzx.manager.service.SysRoleMenuService;
import com.cc.spzx.model.dto.system.AssignMenuDto;
import com.cc.spzx.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public void doAssign(AssignMenuDto assignMenuDto) {
        sysRoleMenuMapper.deleteByRoleId(assignMenuDto.getRoleId());

        sysRoleMenuMapper.doAssign(assignMenuDto);
    }

    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        // 查询所有菜单数据
        List<SysMenu> nodes = sysMenuService.findNodes();

        //查询当前角色的菜单数据
        List<Long> roleMenus = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);

        Map<String, Object> map = new HashMap<>();
        map.put("sysMenuList", nodes);
        map.put("roleMenuIds", roleMenus);

        return map;
    }
}
