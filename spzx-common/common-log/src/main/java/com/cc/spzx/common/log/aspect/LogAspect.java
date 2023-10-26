package com.cc.spzx.common.log.aspect;

import com.cc.spzx.common.log.annotation.Log;
import com.cc.spzx.common.log.service.AsyncOperLogService;
import com.cc.spzx.common.log.utils.LogUtil;
import com.cc.spzx.model.entity.system.SysOperLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private AsyncOperLogService asyncOperLogService;

    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Log sysLog) {

        // 构建前置参数
        SysOperLog sysOperLog = new SysOperLog();
        LogUtil.beforeHandleLog(sysLog , joinPoint , sysOperLog);

        Object proceed = null;
        try {
            // 执行业务方法
            proceed = joinPoint.proceed();
            //构建响应结果参数
            LogUtil.afterHandlLog(sysLog , proceed , sysOperLog , 0 , null);

        } catch (Throwable e) {
            e.printStackTrace();
            LogUtil.afterHandlLog(sysLog , proceed , sysOperLog , 1 , e.getMessage());
            throw new RuntimeException(e);
        }

        // 保存日志数据
        asyncOperLogService.saveSysOperLog(sysOperLog);

        // 返回执行结果
        return proceed;
    }

}
