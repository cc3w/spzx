package com.cc.spzx.manager.mapper;

import com.cc.spzx.model.entity.order.OrderInfo;
import com.cc.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderInfoMapper {
    OrderStatistics selectOrderStatistics(String creatTime);

}
