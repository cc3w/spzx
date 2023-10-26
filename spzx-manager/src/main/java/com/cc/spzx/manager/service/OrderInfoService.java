package com.cc.spzx.manager.service;

import com.cc.spzx.model.dto.order.OrderStatisticsDto;
import com.cc.spzx.model.vo.order.OrderStatisticsVo;

public interface OrderInfoService {
    /**
     * desc 统计每天订单数据
     * date 2023/10/26
     * @author cc
     * @return OrderStatisticsVo
     **/

    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);

}
