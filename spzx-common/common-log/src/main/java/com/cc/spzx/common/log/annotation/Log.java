package com.cc.spzx.common.log.annotation;

import com.cc.spzx.common.log.enums.OperatorType;
import io.swagger.v3.oas.annotations.Operation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {  // 自定义操作日志记录注解
    // 模块名称
    public String title();

    // 操作人类别
    public OperatorType operatorType() default OperatorType.MOBILE;

    // 业务类型（0其它 1新增 2修改 3删除）
    public int businessType();
    // 是否保存请求的参数
    public boolean isSaveRequestData() default true;

    // 是否保存响应的参数
    public boolean isSaveResponseData() default true;


}
