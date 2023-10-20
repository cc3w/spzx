package com.cc.spzx.manager.service.impl;

import com.cc.spzx.manager.mapper.SysRoleMapper;
import com.cc.spzx.manager.service.SysRoleService;
import com.cc.spzx.manager.service.SysUserService;
import com.cc.spzx.model.dto.system.SysRoleDto;
import com.cc.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> findByPage(Integer pageNum, Integer pageSize, SysRoleDto sysRoleDto) {

        PageHelper.startPage(pageNum, pageSize);
        List<SysRole> list = sysRoleMapper.findByPage(sysRoleDto);
        PageInfo<SysRole> sysRolePageInfo = new PageInfo<>(list);
        return sysRolePageInfo;
    }
}
