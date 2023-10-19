package com.cc.spzx.common.exception;


import com.cc.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class ccException extends RuntimeException{

    private ResultCodeEnum resultCodeEnum;
    private int code;
    private String message;

    public ccException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }
}
