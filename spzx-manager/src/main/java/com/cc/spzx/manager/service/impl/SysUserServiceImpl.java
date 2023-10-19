package com.cc.spzx.manager.service.impl;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.cc.spzx.common.exception.ccException;
import com.cc.spzx.manager.mapper.SysUserMapper;
import com.cc.spzx.manager.service.SysUserService;
import com.cc.spzx.model.dto.system.LoginDto;
import com.cc.spzx.model.entity.system.SysUser;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import com.cc.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    //用户登录
    @Override
    public LoginVo login(LoginDto loginDto) {

        String userName = loginDto.getUserName();

        //判断验证码
        String rds_code = redisTemplate.opsForValue().get(loginDto.getCodeKey());
        String captcha = loginDto.getCaptcha();

        //redis中key为空，或者验证码不匹配
        if(StrUtil.isEmpty(rds_code) || !StrUtil.equalsAnyIgnoreCase(rds_code, captcha)) {
            throw new ccException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        //校验成功后，删除redis中的key
        redisTemplate.delete(rds_code);

        //1.查数据库

        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(userName);

        //2.判断数据库是否有
        //3.如果没有返回失败
        if(sysUser == null) {
            throw new ccException(ResultCodeEnum.LOGIN_ERROR);
        }

        //4.如果有就判断密码是否正确
        String db_password = sysUser.getPassword();
        String in_password = loginDto.getPassword();

        in_password = DigestUtils.md5DigestAsHex(in_password.getBytes());

        //5.密码正确就保存在token
        if(!in_password.equals(db_password)) {
            throw new ccException(ResultCodeEnum.LOGIN_ERROR);
        }

        String token = UUID.randomUUID().toString().replace("-", "");

        //7.将token放到redis中
        String temp_token = "user:login:" + token;
        redisTemplate.opsForValue().set(temp_token, JSON.toJSONString(sysUser), 3, TimeUnit.DAYS);

        //返回loginvo对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(temp_token);
        loginVo.setRefresh_token("");

        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String s = redisTemplate.opsForValue().get(token);
        SysUser sysUser = JSON.parseObject(s, SysUser.class);
        return sysUser;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
}
