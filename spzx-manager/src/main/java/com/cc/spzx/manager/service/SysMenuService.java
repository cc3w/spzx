package com.cc.spzx.manager.service;

import com.cc.spzx.model.entity.system.SysMenu;

import java.util.List;

public interface SysMenuService {
    /**
     * desc 查找所有的层级数据（菜单列表）
     * date 2023/10/21
     * @author cc
     * @return SysMenu>
     **/
    List<SysMenu> findNodes();

    /**
     * desc 保存菜单
     * date 2023/10/22
     * @author cc
     * @return
     **/

    /**
     * desc 新增菜单
     * date 2023/10/22
     * @author cc
     * @return
     **/
    void save(SysMenu sysMenu);

    /**
     * desc 修改菜单
     * date 2023/10/22
     * @author cc
     * @return
     **/
    void update(SysMenu sysMenu);

    /**
     * desc 根据用户id删除菜单
     * date 2023/10/22
     * @author cc
     * @return
     **/
    void removeById(Long id);

//    /**
//     * desc 根据角色id删除菜单
//     * date 2023/10/22
//     * @author cc
//     * @return
//     **/
//
//    void deleteByRoleId(Long roleId);

}
