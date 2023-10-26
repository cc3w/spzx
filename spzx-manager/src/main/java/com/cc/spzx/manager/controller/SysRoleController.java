package com.cc.spzx.manager.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.cc.spzx.common.log.annotation.Log;
import com.cc.spzx.manager.service.SysRoleService;
import com.cc.spzx.manager.service.SysUserService;
import com.cc.spzx.model.dto.system.AssignRoleDto;
import com.cc.spzx.model.dto.system.SysRoleDto;
import com.cc.spzx.model.entity.system.SysRole;
import com.cc.spzx.model.vo.common.Result;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Tag(name = "角色接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "查询所有角色（包括选中和所有的）")
    @GetMapping("/findAllRoles/{userId}")
    public Result<Map<String, Object>> findAllRoles(@PathVariable("userId") Long userId) {
        HashMap<String, Object> map = sysRoleService.findAllRoles(userId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "根据角色id进行删除")
    @DeleteMapping("/deleteById/{roleId}")
    public Result deleteById(@PathVariable("roleId") Long id) {
        sysRoleService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改角色")
    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Log(title = "角色添加",businessType = 0) //添加Log注解，设置属性
    @Operation(summary = "添加角色")
    @PostMapping("/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "根据角色名模糊查询")
    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysRole>> findByPage(@PathVariable ("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize,
                             @RequestBody SysRoleDto sysRoleDto) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(pageNum, pageSize, sysRoleDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
}
