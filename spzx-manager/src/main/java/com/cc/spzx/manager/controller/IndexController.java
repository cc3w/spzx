package com.cc.spzx.manager.controller;


import com.cc.spzx.manager.service.SysMenuService;
import com.cc.spzx.manager.service.SysUserService;
import com.cc.spzx.manager.service.ValidateCodeService;
import com.cc.spzx.model.dto.system.LoginDto;
import com.cc.spzx.model.entity.system.SysUser;
import com.cc.spzx.model.vo.common.Result;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import com.cc.spzx.model.vo.system.LoginVo;
import com.cc.spzx.model.vo.system.SysMenuVo;
import com.cc.spzx.model.vo.system.ValidateCodeVo;
import com.cc.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private SysMenuService sysMenuService;

    @Operation(summary = "用户菜单")
    @GetMapping("/menus")
    public Result menus() {
        List<SysMenuVo> list = sysMenuService.findMenus();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "注销用户信息")
    @GetMapping("/logout")
    public Result logout(@RequestHeader(name = "token") String token) {
        sysUserService.logout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

      //两种方式，第一种从HttpServletRequest中取出请求头的token，request.getHeader("token");第二种如下
//    @Operation(summary = "获取用户信息")
//    @GetMapping("/getUserInfo")
//    public Result<SysUser> getUserInfo(@RequestHeader(name = "token") String token) {
//        SysUser userInfo = sysUserService.getUserInfo(token);
//        return Result.build(userInfo, ResultCodeEnum.SUCCESS);
//    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/getUserInfo")
    public Result<SysUser> getUserInfo() {
        return Result.build(AuthContextUtil.get(), ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "验证码")
    @GetMapping("/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "登录的方法")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

}