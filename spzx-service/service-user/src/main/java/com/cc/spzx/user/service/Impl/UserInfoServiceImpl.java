package com.cc.spzx.user.service.Impl;

import com.cc.spzx.common.exception.ccException;
import com.cc.spzx.model.dto.h5.UserRegisterDto;
import com.cc.spzx.model.entity.user.UserInfo;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import com.cc.spzx.user.mapper.UserInfoMapper;
import com.cc.spzx.user.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

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
        UserInfo userInfo = userInfoMapper.getUerName(username);

        //如果不存在，将用户名插入到数据库
        if(userInfo != null) {
            throw new ccException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(userRegisterDto, info);
        info.setPhone(username);
        info.setStatus(1);
        info.setSex(0);
        info.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        userInfoMapper.save(info);

        //将redis中的验证码删掉
        redisTemplate.delete("phone:code:" + username);
    }
}
