package com.cc.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.cc.spzx.manager.mapper.OrderStatisticsMapper;
import com.cc.spzx.manager.service.OrderInfoService;
import com.cc.spzx.model.dto.order.OrderStatisticsDto;
import com.cc.spzx.model.entity.order.OrderStatistics;
import com.cc.spzx.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    OrderStatisticsMapper orderStatisticsMapper;

    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        //查询统计结果数据
        List<OrderStatistics> orderStatisticsData =  orderStatisticsMapper.getOrderStatisticsData(orderStatisticsDto);

        //日期列表
        List<String> dataList = orderStatisticsData.stream().map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd"))
                .collect(Collectors.toList());
        
        //统计金额列表
        List<BigDecimal> amountList = orderStatisticsData.stream().map(OrderStatistics::getTotalAmount).collect(Collectors.toList());

        //封装成OrderStatisticsVo类型数据
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
        orderStatisticsVo.setDateList(dataList);
        orderStatisticsVo.setAmountList(amountList);

        return orderStatisticsVo;
    }
}
