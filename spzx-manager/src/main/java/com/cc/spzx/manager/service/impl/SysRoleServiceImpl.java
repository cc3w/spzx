package com.cc.spzx.manager.service.impl;

import com.cc.spzx.manager.mapper.SysRoleMapper;
import com.cc.spzx.manager.mapper.SysRoleUserMapper;
import com.cc.spzx.manager.service.SysRoleService;
import com.cc.spzx.model.dto.system.SysRoleDto;
import com.cc.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public PageInfo<SysRole> findByPage(Integer pageNum, Integer pageSize, SysRoleDto sysRoleDto) {

        PageHelper.startPage(pageNum, pageSize);
        List<SysRole> list = sysRoleMapper.findByPage(sysRoleDto);
        PageInfo<SysRole> sysRolePageInfo = new PageInfo<>(list);
        return sysRolePageInfo;
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.saveSysRole(sysRole);
    }

    @Override
    public void deleteById(Long id) {
        sysRoleMapper.deleteById(id);
    }


    @Override
    public HashMap<String, Object> findAllRoles(Long userId) {
        List<SysRole> list = sysRoleMapper.findAllRoles();

        List<Long> roleId = sysRoleUserMapper.findSysUserRoleByUserId(userId);

        HashMap<String, Object> map = new HashMap<>();
        map.put("allRolesList", list);
        map.put("sysUserRoles", roleId);
        return map;
    }
}
