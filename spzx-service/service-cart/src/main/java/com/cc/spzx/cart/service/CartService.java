package com.cc.spzx.cart.service;

import com.cc.spzx.model.entity.h5.CartInfo;

import java.util.List;

public interface CartService {

    /**
     * desc 添加购物车
     * date 2023/10/29
     * @author cc
     * @return
     **/
    void addToCart(Long skuId, Integer skuNum);

    //购物车列表
    List<CartInfo> getCartList();

    /**
     * desc 删除购物车的商品
     * date 2023/10/29
     * @author cc
     * @return
     **/

    void deleteCart(Long skuId);

    /**
     * desc 更新购物车商品的选中状态
     * date 2023/10/29
     * @author cc
     * @return
     **/

    void checkCart(Long skuId, Integer isChecked);

    /**
     * desc 更新购物车商品选中状态
     * date 2023/10/29
     * @author cc
     * @return
     **/

    void allCheckCart(Integer isChecked);

    /**
     * desc 清空购物车
     * date 2023/10/29
     * @author cc
     * @return
     **/

    void clearCart();

    /**
     * desc 选中的购物车
     * date 2023/10/29
     * @author cc
     * @return CartInfo>
     **/

    List<CartInfo> getAllCkecked();

}
