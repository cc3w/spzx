package com.cc.spzx.manager.mapper;

import com.cc.spzx.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysOperLogMapper {

    void insert(SysOperLog sysOperLog);

}
