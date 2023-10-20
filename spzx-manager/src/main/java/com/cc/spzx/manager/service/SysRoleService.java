package com.cc.spzx.manager.service;

import com.cc.spzx.model.dto.system.SysRoleDto;
import com.cc.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

public interface SysRoleService {

    /**
     * desc 根据角色名模糊查询
     * date 2023/10/20
     * @author cc
     * @return SysRole>
     **/

    PageInfo<SysRole> findByPage(Integer pageNum, Integer pageSize, SysRoleDto sysRoleDto);

}
