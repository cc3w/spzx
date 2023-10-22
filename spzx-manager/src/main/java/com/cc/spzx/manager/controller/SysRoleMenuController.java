package com.cc.spzx.manager.controller;

import com.cc.spzx.manager.service.SysRoleMenuService;
import com.cc.spzx.manager.service.SysRoleService;
import com.cc.spzx.model.dto.system.AssignMenuDto;
import com.cc.spzx.model.vo.common.Result;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/system/sysRoleMenu")
public class SysRoleMenuController {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @GetMapping("/findSysRoleMenuByRoleId/{roleId}")
    public Result findSysRoleMenuByRoleId(@PathVariable Long roleId) {
        Map<String, Object> sysRoleMenuList = sysRoleMenuService.findSysRoleMenuByRoleId(roleId);
        return Result.build(sysRoleMenuList, ResultCodeEnum.SUCCESS);
    }

    //保存角色分配菜单数据
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssignMenuDto assignMenuDto) {

        sysRoleMenuService.doAssign(assignMenuDto);

        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
