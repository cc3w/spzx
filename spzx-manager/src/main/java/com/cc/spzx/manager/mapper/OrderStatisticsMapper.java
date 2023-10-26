package com.cc.spzx.manager.mapper;

import com.cc.spzx.model.dto.order.OrderStatisticsDto;
import com.cc.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderStatisticsMapper {

    void insert(OrderStatistics orderStatistics);

    List<OrderStatistics> getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);

}
