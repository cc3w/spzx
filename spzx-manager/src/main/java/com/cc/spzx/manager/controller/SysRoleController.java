package com.cc.spzx.manager.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.cc.spzx.manager.service.SysRoleService;
import com.cc.spzx.model.dto.system.SysRoleDto;
import com.cc.spzx.model.entity.system.SysRole;
import com.cc.spzx.model.vo.common.Result;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "角色接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;


    @DeleteMapping("/deleteById/{roleId}")
    public Result deleteById(@PathVariable("roleId") Long id) {
        sysRoleService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

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
