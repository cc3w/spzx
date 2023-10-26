package com.cc.spzx.common.log.service;

import com.cc.spzx.model.entity.system.SysOperLog;

public interface AsyncOperLogService {

    public abstract void saveSysOperLog(SysOperLog sysOperLog) ;
}
