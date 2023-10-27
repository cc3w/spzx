package com.cc.spzx.user.service;

public interface SmsService {

    /**
     * desc 发送验证码
     * date 2023/10/28
     * @author cc
     * @return
     **/

    void sendValidateCode(String phone);

}
