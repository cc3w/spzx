package com.cc.spzx.user.mapper;

import com.cc.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    UserInfo getUerName(String username);

    void save(UserInfo info);

}
