package com.cc.spzx.manager.task;

import cn.hutool.core.date.DateUtil;
import com.cc.spzx.manager.mapper.OrderInfoMapper;
import com.cc.spzx.manager.mapper.OrderStatisticsMapper;
import com.cc.spzx.model.entity.order.OrderInfo;
import com.cc.spzx.model.entity.order.OrderStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class OrderStatisticsTask {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics() {
        String creatTime = DateUtil.offsetDay(new Date(), -1).toString("yyyy-MM-dd");

        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(creatTime);

        if(orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics);
        }

    }
}
