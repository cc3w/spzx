package com.cc.spzx.manager.controller;


import com.cc.spzx.manager.service.SysUserService;
import com.cc.spzx.manager.service.ValidateCodeService;
import com.cc.spzx.model.dto.system.LoginDto;
import com.cc.spzx.model.entity.system.SysUser;
import com.cc.spzx.model.vo.common.Result;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import com.cc.spzx.model.vo.system.LoginVo;
import com.cc.spzx.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Operation(summary = "注销用户信息")
    @GetMapping("/logout")
    public Result logout(@RequestHeader(name = "token") String token) {
        sysUserService.logout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/getUserInfo")
    public Result<SysUser> getUserInfo(@RequestHeader(name = "token") String token) {
        SysUser userInfo = sysUserService.getUserInfo(token);
        return Result.build(userInfo, ResultCodeEnum.SUCCESS);
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