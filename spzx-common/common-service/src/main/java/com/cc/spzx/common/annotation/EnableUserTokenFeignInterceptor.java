package com.cc.spzx.common.annotation;

import com.cc.spzx.common.feign.UserTokenFeignInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(value = {UserTokenFeignInterceptor.class})
public @interface EnableUserTokenFeignInterceptor {
}