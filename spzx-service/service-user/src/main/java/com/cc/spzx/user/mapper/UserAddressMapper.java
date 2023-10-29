package com.cc.spzx.user.mapper;

import com.cc.spzx.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAddressMapper {


    List<UserAddress> findByUserId(Long userId);

}
