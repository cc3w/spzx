package com.cc.spzx.common.exception;

import com.cc.spzx.model.vo.common.Result;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    //表明出现Exception异常的时候执行
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Result error() {
//        return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
//    }

    @ExceptionHandler(ccException.class)
    @ResponseBody
    public Result error(ccException e) {

        return Result.build(null, e.getResultCodeEnum());
    }
}
