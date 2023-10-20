package com.cc.spzx.manager.service;

import com.cc.spzx.model.vo.system.ValidateCodeVo;

public interface ValidateCodeService {

    /**
     * desc 获取验证码
     * date 2023/10/20
     * @author cc
     * @return ValidateCodeVo
     **/

    public ValidateCodeVo generateValidateCode();
}
