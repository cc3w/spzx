package com.cc.spzx.order.service.Impl;

import com.cc.spzx.feign.cart.CartFeignClient;
import com.cc.spzx.model.entity.h5.CartInfo;
import com.cc.spzx.model.entity.order.OrderItem;
import com.cc.spzx.model.vo.h5.TradeVo;
import com.cc.spzx.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.SimpleAssociationHandler;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderInfoImpl implements OrderInfoService {

    @Autowired
    private CartFeignClient cartFeignClient;

    @Override
    public TradeVo getTrade() {

        List<CartInfo> cartInfoList = cartFeignClient.getAllCkecked();

        List<OrderItem> orderItemsList = new ArrayList<>();

        for(CartInfo cartInfo : cartInfoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItemsList.add(orderItem);
        }

        BigDecimal totalAmount = new BigDecimal(0);
        for(CartInfo cartInfo : cartInfoList) {
            totalAmount = totalAmount.add(cartInfo.getCartPrice().multiply(new BigDecimal(cartInfo.getSkuNum())));
        }

        TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(totalAmount);
        tradeVo.setOrderItemList(orderItemsList);

        return tradeVo;
    }
}
