package com.cc.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.lang.UUID;
import com.cc.spzx.manager.service.ValidateCodeService;
import com.cc.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ValidateCodeVo generateValidateCode() {

        //生成验证码，并得到验证码的key和图片流
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 4);
        String codeKey = circleCaptcha.getCode();
        String imageBase64 = circleCaptcha.getImageBase64();

        String key = UUID.randomUUID().toString().replace("-", "");

        //将验证码存进redis
        String tempKey = "user:login:validateCode:" + key;
        redisTemplate.opsForValue().set(tempKey, codeKey, 20, TimeUnit.SECONDS);

        //构建响应数据结果
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(tempKey);
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);

        return validateCodeVo;
    }
}
