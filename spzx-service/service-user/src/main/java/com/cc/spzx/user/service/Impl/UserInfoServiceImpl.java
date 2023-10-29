package com.cc.spzx.user.service.Impl;

import com.alibaba.fastjson.JSON;
import com.cc.spzx.common.exception.ccException;
import com.cc.spzx.model.dto.h5.UserLoginDto;
import com.cc.spzx.model.dto.h5.UserRegisterDto;
import com.cc.spzx.model.entity.user.UserInfo;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import com.cc.spzx.model.vo.h5.UserInfoVo;
import com.cc.spzx.user.mapper.UserInfoMapper;
import com.cc.spzx.user.service.UserInfoService;
import com.sun.jdi.event.AccessWatchpointEvent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
        String redis_token = redisTemplate.opsForValue().get("user:spzx:" + token);
        if(StringUtils.isEmpty(redis_token)) {
            throw new ccException(ResultCodeEnum.LOGIN_AUTH);
        }

        UserInfo info = JSON.parseObject(redis_token, UserInfo.class);
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(info, userInfoVo);
        return userInfoVo;
    }

    //设置idea删除一行的快捷键ctrl+B
    @Override
    public String login(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();


        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ccException(ResultCodeEnum.DATA_ERROR);
        }

        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if(userInfo == null) {
            throw new ccException(ResultCodeEnum.LOGIN_ERROR);
        }

        String db_password = userInfo.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());


        if(!password.equals(db_password)) {
            throw new ccException(ResultCodeEnum.LOGIN_ERROR);
        }

        //检验是否被禁用
        if(userInfo.getStatus() == 0) {
            throw new ccException(ResultCodeEnum.ACCOUNT_STOP);
        }

        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:spzx:" + token, JSON.toJSONString(userInfo), 30, TimeUnit.DAYS);

        return token;
    }

    @Override
    public void register(UserRegisterDto userRegisterDto) {

        //得到用户输入的验证码
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        //校验参数
        if(StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(nickName) ||
                StringUtils.isEmpty(code)) {
            throw new ccException(ResultCodeEnum.DATA_ERROR);
        }

        //判断redis中是否存在验证码
        String codeValueRedis = redisTemplate.opsForValue().get("phone:code:" + username);
        if(!code.equals(codeValueRedis)) {
            throw new ccException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        //如果存在，判断用户名是否存在
        UserInfo userInfo = userInfoMapper.getByUsername(username);

        //如果不存在，将用户名插入到数据库
        if(userInfo != null) {
            throw new ccException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(userRegisterDto, info);
        info.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        info.setPhone(username);
        info.setStatus(1);
        info.setSex(0);
        info.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        userInfoMapper.save(info);

        //将redis中的验证码删掉
        redisTemplate.delete("phone:code:" + username);
    }
}
